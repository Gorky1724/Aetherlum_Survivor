package aetherlum_survivor.model;

import java.util.ArrayList;
import java.util.List;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.model.loop.GameLoop;
import aetherlum_survivor.util.EntityGraphicalData;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static Model instance = null;
	private GameLoop gameLoop;

	//game entities
	private Player player;
	private List<Projectiles> projectiles;
	private List<Enemies> enemies;
    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

	// HANDLE GAMELOOP_____________________________
	@Override
	public void startGameLoop() {
		
		this.gameLoop = new GameLoop(e -> {
			System.out.println("<<< Running >>>");
			update();
			Controller.getInstance().requestViewUpdate();
		});
		System.out.println(">> GameLoop Started");

		this.gameLoop.start();
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
		//TODO
	}

	// EXPOSES ENTITIES___________________
    private List<EntityGraphicalData> convertToListEGD(List<? extends Entity> enList) {
		//wildcard: everything that extends Entity

        List<EntityGraphicalData> graphicalDataList = new ArrayList<>();

        // iterates on element of enList
        for (Entity entity : enList) {
            // Per ogni nemico, crea un oggetto EntityGraphicalData
            EntityGraphicalData data = entity.getEntityGraphicalData();
            // Aggiungi l'oggetto alla lista
            graphicalDataList.add(data);
        }

        // Restituisci la lista completa
        return graphicalDataList;
    }

    public EntityGraphicalData getPlayerEGD() {
		return this.player.getEntityGraphicalData();
	}

    public List<EntityGraphicalData> getEnemiesEGD() {
		return convertToListEGD(this.enemies);
	}

    public List<EntityGraphicalData> getProjectilesEGD() {
		return convertToListEGD(this.projectiles);
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
