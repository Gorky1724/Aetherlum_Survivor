package aetherlum_survivor.model;

import aetherlum_survivor.util.EntityLogicalData;

public  class Entity{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    protected EntityLogicalData eld;

    //game stats
    protected int type;
    protected double speed;
    protected double maxHitPoints;
    protected double currentHP;
    protected double damage;
    protected double damageResistance;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Entity(int type) {
        this.type = type;
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    // POSITION AND COLLISION_____________________________
    protected EntityLogicalData createEntityGraphicalData(double coordX, double coordY, double width, double height) {
        return new EntityLogicalData(coordX, coordY, width, height);
    }

    protected EntityLogicalData getEntityGraphicalData() {
        return this.eld;
    }

    protected void setEntityGraphicalData(EntityLogicalData newELD) {
        this.eld = newELD;
    }

    protected void createAndSetEntityGraphicalData(double coordX, double coordY, double width, double height) {
        this.eld = new EntityLogicalData(coordX, coordY, width, height);
    }

    // DIRECT DATA VARIATION FROM GAME EVENT_____________________________
    public void takeDamage(double dmgTaken) {
        this.currentHP = this.currentHP - (dmgTaken - dmgTaken*this.damageResistance);
    }
    public void heal(double healTaken) {
        this.currentHP = Math.min(this.currentHP + healTaken, this.maxHitPoints);
    }
    public boolean isAlive() {
        return (currentHP > 0);
    }

    //---------------------------------------------------------------

    
}
