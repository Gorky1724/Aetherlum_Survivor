package aetherlum_survivor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.controller.KeyHandler;

import aetherlum_survivor.model.loop.GameLoop;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.ScenarioData;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static Model instance = null;
	private GameLoop gameLoop;
	
	//saves number of clock cycles that are passed
	private long clockCycle = 0;

	//selected scenario
	private ScenarioData scenarioData;

	//game entities
	private Player player;

	private Projectiles projectileHandler; //to use class Projectiles methods to update the List
	private List<Projectiles> projectiles;
	private int max_projectiles_number = EntityData.MAX_PROJECTILES_SPAWN;

	private Collectibles collectibleHandler; //to use class Projectiles methods to update the List
	private List<Collectibles> collectibles;
	private int max_collectibles_number = EntityData.MAX_COLLECTIBLES_SPAWN;

	private Enemies enemyHandler; //to use class Enemies methods to update the List
	private List<Enemies> enemies;
	private int max_enemies_number;

	// i want the spawn/respawn to not happen every timer tick but every cadence-time
	private long lastEntitiesSpawnDespawn = this.getClockCyle(); //starting value
	private int spawnDespawnCadence = Constants.SPAWN_DESPAWN_CADENCE; //to be sure that it's spawned even if a frame gets skipped

	//ingame timer - only instance with currentTimeMillis() for more precision
	private int secondsPassed;
	private long lastUpdatedSeconds = this.getClockCyle();
    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PUBLIC METHODS

	// HANDLE GAMELOOP_____________________________
	@Override
	public void startGameLoop() {
		
		this.gameLoop = new GameLoop(e -> { //implements GameLoop ActionListener 'update'
			//System.out.println("<<< Running >>>");

			clockCycle++;
			handleTimePassed();

			update();

			Controller.getInstance().requestViewUpdate();
		});

		this.gameLoop.start();
		//System.out.println(">> GameLoop Started");


		//single creation of player instance at the beginning of the game
		this.player = new Player();

		//pre allocates list for the classes of entities that continually spawns and de-spawns
		this.enemyHandler = new Enemies(EntityData.NULL_VALUE);
		this.max_enemies_number = scenarioData.getEnemiesMaxNum();
		this.enemies = new ArrayList<>(this.max_enemies_number);
		for (int i = 0; i < this.max_enemies_number; i++) {
			Enemies en = new Enemies(EntityData.NULL_VALUE);
			en.createAndSetEntityLogicalData(EntityData.INACTIVE_STATUS, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE);
			this.enemies.add(en);
		}

		this.projectileHandler = new Projectiles(EntityData.NULL_VALUE);
		this.max_projectiles_number = EntityData.MAX_PROJECTILES_SPAWN;
		this.projectiles = new ArrayList<>(this.max_projectiles_number);
		for(int i = 0; i < this.max_projectiles_number; i++) {
			Projectiles prj = new Projectiles(EntityData.NULL_VALUE);
			prj.createAndSetEntityLogicalData(EntityData.INACTIVE_STATUS, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE);
			this.projectiles.add(prj);
		}

		this.collectibleHandler = new Collectibles(EntityData.NULL_VALUE);
		this.max_collectibles_number = EntityData.MAX_COLLECTIBLES_SPAWN;
		this.collectibles = new ArrayList<>(this.max_collectibles_number);
		for(int i = 0; i < this.max_collectibles_number; i++) {
			Collectibles clt = new Collectibles(EntityData.NULL_VALUE);
			clt.createAndSetEntityLogicalData(EntityData.INACTIVE_STATUS, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE);
			this.collectibles.add(clt);
		}

	}

	@Override
	public void stopGameLoop() {
		this.gameLoop.pause();
	}

	@Override
	public void resumeGameLoop() {
		this.gameLoop.resume();
	}

	@Override
	public void setGameOver() {
		this.stopGameLoop();
		Controller.getInstance().handleGameOver();
	}

	@Override
	public long getClockCyle() { //used to calibrate in-game event based on time (spawn/despawn, shoot,)
		return this.clockCycle;
	}

	// INGAME TIMER_____________________________
	private void handleTimePassed() {
		long ct = System.currentTimeMillis();
		if(ct - this.lastUpdatedSeconds >= 1000) {
			this.lastUpdatedSeconds = ct;
			this.secondsPassed++;
		}
	}

	@Override
	public int getTimePassed() {
		return this.secondsPassed;
	}

	// UPDATE_____________________________
	@Override
	public void update() {

		// if key input: moves player 
		this.player.movePlayer(KeyHandler.getInstance().getUpPressed(),
								KeyHandler.getInstance().getRightPressed(),
								KeyHandler.getInstance().getDownPressed(),
								KeyHandler.getInstance().getLeftPressed()
		);

		//despawns if too distant and spawns entities every cadence
		long currentCycle = this.getClockCyle();
		if ((currentCycle - this.lastEntitiesSpawnDespawn) >= this.spawnDespawnCadence) {
			this.enemies = this.enemyHandler.despawn(this.enemies, this.player.getEntityLogicalData());
			this.enemies = this.enemyHandler.spawn(this.enemies, this.scenarioData, this.player.getEntityLogicalData());

			this.collectibles = this.collectibleHandler.despawn(this.collectibles, this.player.getEntityLogicalData());
			this.collectibles = this.collectibleHandler.spawn(this.collectibles, this.player.getEntityLogicalData());

			this.projectiles = this.projectileHandler.despawn(this.projectiles, this.player.getEntityLogicalData());

			this.lastEntitiesSpawnDespawn = this.getClockCyle();
		}
		//projectiles spawn depends from playerFireRate*projectileRateModifier
		currentCycle = this.getClockCyle();
		this.projectiles = this.projectileHandler.shoot(this.projectiles, this.player, currentCycle, this.enemies);

		//moves entities
		for(Enemies en : this.enemies) { //enemies
			en.moveTowardsPlayer(this.player.getEntityLogicalData(), this.enemies);
		}
		for(Projectiles prj : this.projectiles) {
			prj.advance();
		}

		//check collision
		this.checkCollision();
	}

	@Override
	public void selectedScenario(int selected_scenario_num) {
		this.scenarioData = new ScenarioData(selected_scenario_num);
	}

	@Override
	public void checkCollision() {
		//player - enemies
		for(Enemies en: this.enemies) {
			if(en.isActive() && en.getBoundingBox().intersects(this.player.getBoundingBox())) {
				this.player.onCollision(en);
			}
		}

		//player - collectibles
		for(Collectibles clt : this.collectibles) {
			if(clt.isActive() && clt.getBoundingBox().intersects(this.player.getBoundingBox())) {
				this.player.onCollision(clt);
				clt.onCollision(this.player);
			}
		}

		//enemies - projectiles
		for(Enemies en: this.enemies) {
			if(en.isActive()) {
				for(Projectiles prj : this.projectiles) {
					if(prj.isActive() && prj.getBoundingBox().intersects(en.getBoundingBox())) {
						prj.onCollision(en);
						en.onCollision(prj);
					}
				}
			}
		}
	}

	public void upgradePlayer(Map<Integer, LevelUpOptions> powerUpData) {
		this.player.upgrade(powerUpData);
	}

	// EXPOSES DATA___________________
    private List<EntityLogicalData> convertToListELD(List<? extends Entity> enList) { //wildcard: everything that extends Entity
		
        List<EntityLogicalData> graphicalDataList = new ArrayList<>();
        for (Entity entity : enList) { //extrapolates eld of every enemy and add to list
            EntityLogicalData data = entity.getEntityLogicalData();
            graphicalDataList.add(data);
        }
        return graphicalDataList;
    }
	@Override
    public EntityLogicalData getPlayerELD() {
		return this.player.getEntityLogicalData();
	}
	@Override
    public List<EntityLogicalData> getEnemiesELD() {
		return convertToListELD(this.enemies);
	}
	@Override
    public List<EntityLogicalData> getProjectilesELD() {
		return convertToListELD(this.projectiles);
	}
	@Override
	public List<EntityLogicalData> getCollectiblesELD() {
		return convertToListELD(this.collectibles);
	}

	@Override
	public double[] getPlayerInfo() {
		double [] bar_level_hp = {this.player.getCurrentXp(), this.player.getXpBar(), (double) this.player.getLevel(),
									this.player.getCurrentHP(), this.player.getMaxHP()
								};
		return bar_level_hp;
	}

    //---------------------------------------------------------------
	//! STATIC METHODS
	public static InterfaceModel getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}
    //---------------------------------------------------------------

}
