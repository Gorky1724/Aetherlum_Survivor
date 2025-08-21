package aetherlum_survivor.model;

import aetherlum_survivor.util.EntityLogicalData;

public  class Entity{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private EntityLogicalData ELD;

    //game stats
    private int type;
    private double speed;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Entity() {
        //do nothing
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    // POSITION AND COLLISION_____________________________
    protected EntityLogicalData createEntityGraphicalData(double coordX, double coordY, double width, double height) {
        return new EntityLogicalData(coordX, coordY, width, height);
    }

    protected EntityLogicalData getEntityGraphicalData() {
        return this.ELD;
    }

    protected void setEntityGraphicalData(EntityLogicalData newELD) {
        this.ELD = newELD;
    }

    protected void createAndSetEntityGraphicalData(double coordX, double coordY, double width, double height) {
        this.ELD = new EntityLogicalData(coordX, coordY, width, height);
    }

    // GAME STATS_____________________________
    protected int getType() {
        return this.type;
    }
    protected void setType(int newType) {
        this.type = newType;
    }

    protected double getSpeed() {
        return this.speed;
    }
    protected void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    //---------------------------------------------------------------

    
}
