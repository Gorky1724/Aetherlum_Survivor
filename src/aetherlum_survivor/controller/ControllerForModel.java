package aetherlum_survivor.controller;

public class ControllerForModel implements InterfaceControllerForModel{

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static ControllerForModel instance = null;

    private GameLoop gameLoop;

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public synchronized void startGameLoop() {
        /*SYNCHRONIZED prevents thread interference and memory consistency errors
        if morethreads need to read or write the same variables */
        
        if (this.gameLoop != null && this.gameLoop.isRunning()) { //gameLoop is already started
            return;
        }

        if (this.gameLoop != null && !this.gameLoop.isRunning()) { //gameLoop exists but is not running
            gameLoop.stopGame();
        }

        this.gameLoop = new GameLoop();
        this.gameLoop.run(); //the gameLoop starts

        System.out.println(">> GameLoop Running");
    }

    public synchronized void stopGameLoop() {
        if (this.gameLoop != null && this.gameLoop.isRunning()) {
            this.gameLoop.stopGame();
        }
    }
    
    public synchronized void pauseGameLoop() {
        if (this.gameLoop != null) {
            this.gameLoop.pauseGame();
        }
    }
    
    public synchronized void resumeGameLoop() {
        if (this.gameLoop != null) {
            this.gameLoop.resumeGame();
        }
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
