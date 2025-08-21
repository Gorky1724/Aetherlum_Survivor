package aetherlum_survivor.controller;

import javax.swing.SwingUtilities;

import aetherlum_survivor.model.Model;
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


    //UPDATES and REQUESTS_____________________________
    @Override
    public void startGameLoop() {
        Model.getInstance().startGameLoop();
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
