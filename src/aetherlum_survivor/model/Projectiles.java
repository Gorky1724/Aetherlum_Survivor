package aetherlum_survivor.model;

import java.util.Iterator;
import java.util.List;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityData.EntityStats;
import aetherlum_survivor.util.EntityLogicalData;

public class Projectiles extends Entity{

    //! PRIVATE ATTRIBUTES
    private double rateModifier; //never used but assigned
    private double damageModifier;

    private double angleOfMovement; //moves in the direction given when fired

    private Entity owner; //direct reference to who shot it

    public Projectiles(int type) {
        super(type);
    }

    //! INSTANCE METHODS - utilities
    public EntityLogicalData setValuesDependingOnProjectileType(int type, EntityLogicalData eld, Player player) {

        
        EntityStats stats = EntityData.STATS.get(type);
        if (stats == null) {
            System.out.println("!!!>> NULL ENEMY TYPE NUMBER - This should never print out");
        }
        
        eld.setWidth(stats.width);
        eld.setHeight(stats.height);
        eld.setSpritePath(stats.spritePath);
        this.speed = stats.speed;
        this.maxHitPoints = stats.maxHP;
        this.currentHP = this.maxHitPoints;
        this.damage = stats.damage;
        this.damageResistance = stats.damageResistance;
    
        this.rateModifier = stats.proj_rate_mod;
        this.damageModifier = stats.proj_dmg_mod;
        this.damage = player.getDamage(); 
        this.owner = player;
        //System.out.println(">>> Entity data of type " + type + " set");

        return eld;
    }

    public Entity getOwner() {
        return this.owner;
    }

    //! INSTANCE METHODS - update
    //spawn
    public List<Projectiles> shoot(List<Projectiles> projectiles, Player player, long currentTime, List<Enemies> enemies) {

        int currentlyActive = countActive(projectiles);
        int maxProjectiles = EntityData.MAX_PROJECTILES_SPAWN;

        //if no other projectiles are allowed to spawn
        if (currentlyActive >= maxProjectiles) {
            return projectiles;
        }

        List<Long[]> availableProjectiles = player.getAvailableProjectiles();

        Iterator<Long[]> it = availableProjectiles.iterator();
        while(it.hasNext()) { //checks every projectile type
            Long[] projData = it.next();
            int projType = (int) ((long) projData[0]); //from Long to long and then to int -- no precision lost
            long lastShot = projData[1];

            //extracs type fire rate
            EntityStats stats = EntityData.STATS.get(projType);
            double prj_rateMod = stats.proj_rate_mod;
            if(lastShot + (player.getFireRate()*prj_rateMod) <= currentTime) { //if can shoot
                for(Projectiles prj: projectiles) {
                    if(!prj.isActive()) {
                        projData[1] = currentTime; //updates type_lastShot
                        EntityLogicalData eld = prj.getEntityLogicalData();
                        //based on enemy type
                        eld = prj.setValuesDependingOnProjectileType(projType, eld, player);
                        
                        //makes proj_center coincide with player_center
                        double spawnX = player.getEntityLogicalData().getCoordX() + player.getEntityLogicalData().getWidth()/2.0  - eld.getWidth()/2.0;
                        double spawnY = player.getEntityLogicalData().getCoordY() + player.getEntityLogicalData().getHeight()/2.0 - eld.getHeight()/2.0;
                        eld.setCoordX(spawnX);
                        eld.setCoordY(spawnY);

                        prj.setEntityLogicalData(eld);

                        //for the direction
                        prj.findAndSetAngleOfMovement(enemies);

                        prj.setActive();
                        break;
                    }
                }
            }
        }

        return projectiles;
    }

    //move
    public void findAndSetAngleOfMovement(List<Enemies> enemies) {
        double[] minDistanceEnData = {Constants.DESPAWN_RADIUS+1, 0, 0}; //distance, coordX, coordY
        for(Enemies en : enemies) {
            if(en.isActive()) {
                double enX = en.getEntityLogicalData().getCoordX();
                double enY = en.getEntityLogicalData().getCoordY();
                double dist = calculateDistance(enX, enY, this.eld.getCoordX(), this.eld.getCoordY());
                if(dist <= minDistanceEnData[0]) {
                    minDistanceEnData[0] = dist;
                    minDistanceEnData[1] = enX;
                    minDistanceEnData[2] = enY; 
                }
            }
        }

        //if no close enemy has been found, the shot will direct towards the (0,0) of the logical coord axis

        this.angleOfMovement = Math.atan2(minDistanceEnData[2] - this.eld.getCoordY(), minDistanceEnData[1] - this.eld.getCoordX());
    }
    public void advance() {
        this.eld.setCoordX(this.eld.getCoordX() + Math.cos(this.angleOfMovement) * this.speed);
        this.eld.setCoordY(this.eld.getCoordY() + Math.sin(this.angleOfMovement) * this.speed);
    }

    //collision
    @Override
    protected void onCollision(Entity ent) {
        if(Enemies.class.isInstance(ent)) { //only if is passed an enemy
            this.takeDamage(ent.getDamage());
            //System.out.println("#> Projectile currenthp: " + this.currentHP);
            if(!this.isAlive()) {
                this.death();
            }
        }
    }

}
