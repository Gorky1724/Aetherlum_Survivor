package aetherlum_survivor.controller;

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

    public void startApplication() {
        View.getInstance().openGameFrame(); //creates unical frame for the whole application
        this.openStartPanel();
    }

    public void openStartPanel() {
        View.getInstance().openStartPanel();

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
