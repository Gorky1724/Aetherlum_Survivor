package aetherlum_survivor.model;

public class Model implements InterfaceModel {

    //---------------------------------------------------------------
	//! PRIVATE STATIC ATTRIBUTES

    private static Model instance = null;



    //---------------------------------------------------------------
	//! STATIC METHODS
	public static InterfaceModel getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}
    //---------------------------------------------------------------

    
}
