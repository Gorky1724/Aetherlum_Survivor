package aetherlum_survivor.controller;

import javax.swing.SwingUtilities;

import aetherlum_survivor.model.Model;
import aetherlum_survivor.view.View;

public class Controller implements InterfaceController {

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static Controller instance = null;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    private Controller() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public void startApplication() {
        //multithreading
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() {
                View.getInstance().openGameFrame(); //per avere un unico frame su cui inserire i panel 
                openStartPanel();
            }
        });
    }

    //PANEL MANAGEMENT_____________________________
    @Override
    public void openStartPanel() {
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() {
                View.getInstance().openStartPanel();
            }
        });
    }

    @Override
    public void openSettingsPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.getInstance().openSettingsPanel();
            }
        });
    }

    @Override
    public void openGamePanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.getInstance().openGamePanel();
            }
        });
    }

    
    // HANLDE GAMELOOP_____________________________

    @Override
    public void startGameLoop() {
        Model.getInstance().startGameLoop();
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

    //UPDATES and REQUESTS_____________________________
    @Override
    public void requestViewUpdate() {
        View.getInstance().update();
    }


    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC METHODS

    //* singleton pattern
    public static InterfaceController getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;

    }

	//---------------------------------------------------------------
    
}
