package aetherlum_survivor.controller;

import javax.swing.SwingUtilities;

import java.util.List;

import aetherlum_survivor.model.Model;
import aetherlum_survivor.view.View;
import aetherlum_survivor.util.EntityLogicalData;

public class Controller implements InterfaceController{

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static Controller instance = null;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    private Controller() {
        //default
        View.getInstance().attachKeyListenerToGamePanel();
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public void startApplication() {
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
    public void pauseGameLoop() {
        Model.getInstance().pauseGameLoop();
    }
    
    @Override
    public  void resumeGameLoop() {
        Model.getInstance().resumeGameLoop();
    }

    //UPDATES and REQUESTS_____________________________
    @Override
    public void requestViewUpdate() {
        View.getInstance().update();
    }

    // EXPOSES DATA_____________________________
    @Override
    public EntityLogicalData getPlayerELD() {
        return Model.getInstance().getPlayerELD();
    }

    @Override
    public List<EntityLogicalData> getEnemiesELD() {
        return Model.getInstance().getEnemiesELD();
    }

    @Override
    public List<EntityLogicalData> getProjectilesELD() {
        return Model.getInstance().getProjectilesELD();
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
