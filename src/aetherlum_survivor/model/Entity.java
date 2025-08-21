package aetherlum_survivor.model;

import aetherlum_survivor.util.EntityGraphicalData;

public  class Entity{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private EntityGraphicalData egd;

    //game stats
    private int type;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Entity() {
        //do nothing
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    // POSITION AND COLLISION_____________________________
    protected EntityGraphicalData createEntityGraphicalData(double coordX, double coordY, double width, double height) {
        return new EntityGraphicalData(coordX, coordY, width, height);
    }

    protected EntityGraphicalData getEntityGraphicalData() {
        return this.egd;
    }

    protected void setEntityGraphicalData(EntityGraphicalData newEGD) {
        this.egd = newEGD;
    }

    protected void createAndSetEntityGraphicalData(double coordX, double coordY, double width, double height) {
        this.egd = new EntityGraphicalData(coordX, coordY, width, height);
    }

    // GAME STATS_____________________________
    protected int setType() {
        return this.type;
    }
    protected void setType(int newType) {
        this.type = newType;
    }

    //---------------------------------------------------------------

    
}
