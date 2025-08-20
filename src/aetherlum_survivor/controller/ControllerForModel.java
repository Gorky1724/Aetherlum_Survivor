package aetherlum_survivor.controller;

import aetherlum_survivor.to_delete.GameLoop;

public class ControllerForModel implements InterfaceControllerForModel{

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static ControllerForModel instance = null;

    private GameLoop gameLoop;

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public void startGameLoop() {
        //TODO

        System.out.println(">> GameLoop Running");
    }

    @Override
    public void stopGameLoop() {
        //TODO
    }

    @Override
    public  void pauseGameLoop() {
        //TODO
    }
    
    @Override
    public  void resumeGameLoop() {
        //TODO
    }

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC METHODS

    //* singleton pattern
    public static InterfaceControllerForModel getInstance() {
        if(instance == null) {
            instance = new ControllerForModel();
        }
        return instance;

    }

	//---------------------------------------------------------------
    
}
