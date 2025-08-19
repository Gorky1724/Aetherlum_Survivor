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

    @Override
    public void openStartPanel() {
        View.getInstance().openStartPanel();
    }

    @Override
    public void closeStartPanel() {
        View.getInstance().closeStartPanel();
    }

    @Override
    public void openSettingsPanel() {
        View.getInstance().openSettingsPanel();
    }

    @Override
    public void closeSettingsPanel() {
        View.getInstance().closeSettingsPanel();
    }

    @Override
    public void openGamePanel() {
        View.getInstance().openGamePanel();
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
