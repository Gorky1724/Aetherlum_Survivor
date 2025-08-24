package aetherlum_survivor.model;

import java.util.ArrayList;
import java.util.List;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.controller.KeyHandler;

import aetherlum_survivor.model.loop.GameLoop;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.ScenarioData;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static Model instance = null;
	private GameLoop gameLoop;

	//selected scenario
	private ScenarioData scenarioData;

	//game entities
	private Player player;

	private Projectiles projectileHandler; //to use class Projectiles methods to update the List
	private List<Projectiles> projectiles;

	private Collectibles collectibleHandler; //to use class Projectiles methods to update the List
	private List<Collectibles> collectibles;

	private Enemies enemyHandler; //to use class Enemies methods to update the List
	private List<Enemies> enemies;
	private int max_enemies_number;

	// i want the spawn/respawn to not happen every timer tick
	private long lastEntitiesSpawnDespawn = 0; //starting value
	private int cadence = Constants.SPAWN_DESPAWN_CADENCE; //every cadence-time entities are spawned/despawned
    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PUBLIC METHODS

	// HANDLE GAMELOOP_____________________________
	@Override
	public void startGameLoop() {
		
		this.gameLoop = new GameLoop(e -> {
			//System.out.println("<<< Running >>>");
			update();
			Controller.getInstance().requestViewUpdate();
		});

		this.gameLoop.start();
		System.out.println(">> GameLoop Started");


		//single creation of player instance at the beginning of the game
		this.player = new Player();

		//pre allocates list for the classes of entities that continually spawns and de-spawns
		this.enemyHandler = new Enemies(EntityData.NULL_VALUE);
		this.max_enemies_number = scenarioData.getEnemiesMaxNum();
		this.enemies = new ArrayList<>(this.max_enemies_number);
		for (int i = 0; i < this.max_enemies_number; i++) {
			Enemies en = new Enemies(EntityData.NULL_VALUE);
			en.setInactive();
			en.createAndSetEntityLogicalData(EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE, EntityData.NULL_VALUE);
			enemies.add(en);
		}

	}

	@Override
	public void pauseGameLoop() {
		this.gameLoop.pause();
	}

	@Override
	public void resumeGameLoop() {
		this.gameLoop.resume();
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

		//despawns and spawns entities every cadence
		long currentTime = System.currentTimeMillis();
		if (currentTime - this.lastEntitiesSpawnDespawn >= this.cadence) {
			//despawns enemies too distant and spawns new enemies
			this.enemies = this.enemyHandler.despawn(this.enemies, this.player.getEntityLogicalData());
			this.enemies = this.enemyHandler.spawn(this.enemies, this.scenarioData, this.player.getEntityLogicalData());

			//TODO
			//despawns collectibles too distant and spawns new consubamles

			//spawns projectiles


			this.lastEntitiesSpawnDespawn = System.currentTimeMillis();;
		}

		//TODO
		//moves entities

		//check collision

	}

	@Override
	public void selectedScenario(int selected_scenario_num) {
		scenarioData = new ScenarioData(selected_scenario_num);
	}

	// EXPOSES ENTITIES LOGICAL DATA___________________
    private List<EntityLogicalData> convertToListELD(List<? extends Entity> enList) {
		//wildcard: everything that extends Entity

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

    //---------------------------------------------------------------
	//! STATIC METHODS
	public static InterfaceModel getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}
    //---------------------------------------------------------------

}
