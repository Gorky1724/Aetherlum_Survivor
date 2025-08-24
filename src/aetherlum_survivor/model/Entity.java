package aetherlum_survivor.model;

import aetherlum_survivor.util.EntityLogicalData;

import java.awt.geom.Rectangle2D;

public  class Entity{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    protected EntityLogicalData eld;

    // active/inactive - not used by Player but by the other classes
    protected boolean status; //false: inactive - true: active

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

    // POSITION_____________________________
    protected EntityLogicalData createEntityLogicalData(double coordX, double coordY, double width, double height) {
        return new EntityLogicalData(coordX, coordY, width, height);
    }

    protected EntityLogicalData getEntityLogicalData() {
        return this.eld;
    }

    protected void setEntityLogicalData(EntityLogicalData newELD) {
        this.eld = newELD;
    }

    protected void createAndSetEntityLogicalData(double coordX, double coordY, double width, double height) {
        this.eld = new EntityLogicalData(coordX, coordY, width, height);
    }

    // COLLISION_____________________________
    protected Rectangle2D getBoundingBox() {
        return new Rectangle2D.Double(this.eld.getCoordX(), this.eld.getCoordY(), this.eld.getWidth(), this.eld.getHeight());
    }

    protected void onCollision(Entity otherEntity) {
        //will be implemented by each subtype
    }

    // SETTER_____________________________
    protected void setType(int newType) {
        this.type = newType;
    }

    protected boolean isActive() {
        return this.status;
    }
    protected void setActive() {
        this.status = true;
    }
    protected void setInactive() {
        this.status = false;
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
