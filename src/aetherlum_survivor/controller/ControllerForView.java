package aetherlum_survivor.controller;

import javax.swing.SwingUtilities;

import aetherlum_survivor.view.View;

public class ControllerForView implements InterfaceControllerForView{

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static ControllerForView instance = null;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    private ControllerForView() {
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
                ControllerForView.getInstance().openStartPanel();
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
    public void closeStartPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.getInstance().closeStartPanel();
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
    public void closeSettingsPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.getInstance().closeSettingsPanel();
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

    @Override
    public void closeGamePanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.getInstance().closeGamePanel();
            }
        });
    }

    //UPDATES and REQUESTS_____________________________
    @Override
    public void requestStartGameLoop() {
        ControllerForModel.getInstance().startGameLoop();
    }

    @Override
    public void requestViewUpdate() {
        //TODO
    }


    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC METHODS

    //* singleton pattern
    public static InterfaceControllerForView getInstance() {
        if(instance == null) {
            instance = new ControllerForView();
        }
        return instance;

    }

	//---------------------------------------------------------------
    
}
