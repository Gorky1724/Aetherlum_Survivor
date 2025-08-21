package aetherlum_survivor.model;

import aetherlum_survivor.controller.ControllerForModel;
import aetherlum_survivor.model.loop.GameLoop;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static Model instance = null;
	private GameLoop gameLoop;
    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

	@Override
	public void startGameLoop() {
		
		this.gameLoop = new GameLoop(e -> {
		update();
		ControllerForModel.getInstance().requestViewUpdate();
		});
		System.out.println(">> GameLoop Running");
	}

	@Override
	public void update() {
		//TODO
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
