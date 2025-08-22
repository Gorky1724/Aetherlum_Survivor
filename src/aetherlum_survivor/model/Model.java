package aetherlum_survivor.model;

import java.util.ArrayList;
import java.util.List;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.controller.KeyHandler;
import aetherlum_survivor.model.loop.GameLoop;
import aetherlum_survivor.util.EntityLogicalData;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static Model instance = null;
	private GameLoop gameLoop;

	//game entities
	private Player player;
	private Projectiles projectileHandler;
	private List<Projectiles> projectiles;
	private Enemies enemyHandler;
	private List<Enemies> enemies;
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
		System.out.println(">> GameLoop Started");

		this.gameLoop.start();

		//single creation of player instance at the beginning of the game
		this.player = new Player();

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

		
	}

	// EXPOSES ENTITIES LOGICAL DATA___________________
    private List<EntityLogicalData> convertToListELD(List<? extends Entity> enList) {
		//wildcard: everything that extends Entity

        List<EntityLogicalData> graphicalDataList = new ArrayList<>();

        // iterates on element of enList
        for (Entity entity : enList) {
            // Per ogni nemico, crea un oggetto EntityGraphicalData
            EntityLogicalData data = entity.getEntityGraphicalData();
            // Aggiungi l'oggetto alla lista
            graphicalDataList.add(data);
        }

        // Restituisci la lista completa
        return graphicalDataList;
    }

    public EntityLogicalData getPlayerELD() {
		return this.player.getEntityGraphicalData();
	}

    public List<EntityLogicalData> getEnemiesELD() {
		return convertToListELD(this.enemies);
	}

    public List<EntityLogicalData> getProjectilesELD() {
		return convertToListELD(this.projectiles);
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
