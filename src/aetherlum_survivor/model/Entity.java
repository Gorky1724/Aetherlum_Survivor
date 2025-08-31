package aetherlum_survivor.model;

import aetherlum_survivor.controller.Controller;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityLogicalData;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;

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

    //for randomness in metods
    protected Random random = new Random();

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Entity(int type) {
        this.type = type;
    }

    //---------------------------------------------------------------
	//! INSTANCE METHODS

    // utilities
    protected double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    protected int countActive(List<? extends Entity> entities) {
        int count = 0;
        for (Entity ent : entities) {
            if(ent.isActive())  {
                count++;
            }
        }
        return count;
    }

    protected double[] generateSpawnPosition(double playerX, double playerY) {

        //casual angle
        double angle = random.nextDouble() * 2*Math.PI;
        //casual distance in between spawn/despawn/radiuses
        double distance = Constants.SPAWN_RADIUS + random.nextDouble() *(Constants.DESPAWN_RADIUS - Constants.SPAWN_RADIUS);
        
        // Calculate position relativly to playr
        double spawnX = playerX + distance * Math.cos(angle);
        double spawnY = playerY + distance * Math.sin(angle);
        
        return new double[] {spawnX, spawnY};
    }

    // despawn entities
    public <T extends Entity> List<T> despawn(List<T> entities, EntityLogicalData playerELD) {

        for(T ent : entities) {
            if(ent.isActive()) {
                double distance = calculateDistance(ent.getEntityLogicalData().getCoordX(),
                                                    ent.getEntityLogicalData().getCoordY(),
                                                    playerELD.getCoordX(), playerELD.getCoordY()
                );
                
                if (distance >= Constants.DESPAWN_RADIUS) {
                    ent.setInactive();
                }
            } 
        }
        //System.out.println("#> Enemies Despawned");
        return entities;
    }

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

    //status + visualization
    protected EntityLogicalData createEntityLogicalData(boolean status, double coordX, double coordY, double width, double height, int type) {
        return new EntityLogicalData(status, coordX, coordY, width, height, type);
    }

    protected void createAndSetEntityLogicalData(boolean status, double coordX, double coordY, double width, double height, int type) {
        this.eld = new EntityLogicalData(status, coordX, coordY, width, height, type);
    }

    // COLLISION_____________________________
    protected Rectangle2D getBoundingBox() {
        return new Rectangle2D.Double(this.eld.getCoordX(), this.eld.getCoordY(), this.eld.getWidth(), this.eld.getHeight());
    }

    protected void onCollision(Entity otherEntity) {
        //will be implemented by each subtype
    }

    // GETTER_____________________________
    public double getDamage() {
        return this.damage;
    }

    // SETTER_____________________________
    protected void setType(int newType) {
        this.type = newType;
    }

    protected boolean isActive() {
        return this.eld.getStatus();
    }
    protected void setActive() {
        this.eld.setStatus(true);
    }
    protected void setInactive() {
        this.eld.setStatus(false);
    }

    protected void setCondition(int newCondition) {
        if(this.eld.getCondition() != newCondition) {
            this.eld.setCondition(newCondition);
            this.eld.setStartingClockOfCondition(Model.getInstance().getClockCyle());
        }
    }

    // DIRECT DATA VARIATION FROM GAME EVENT_____________________________
    public void takeDamage(double dmgTaken) {
        this.currentHP = this.currentHP - (dmgTaken - dmgTaken*this.damageResistance);
    }
    public void heal(double healTaken) {
        this.currentHP = Math.min(this.currentHP + healTaken, this.maxHitPoints);
    }
    public boolean isAlive() {
        return (currentHP >= 1);
    }
    public void death() {
        this.setInactive();
        if(this.eld.getCondition() != EntityData.DYING) {
            this.eld.setCondition(EntityData.DYING);
            this.eld.setStartingClockOfCondition(Model.getInstance().getClockCyle());
        }
        Controller.getInstance().updateDeathAnimationList(this.eld);
    }

    //---------------------------------------------------------------

    
}
