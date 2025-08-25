package aetherlum_survivor.model;

import java.util.Iterator;
import java.util.List;

import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityData.EntityStats;
import aetherlum_survivor.util.EntityLogicalData;

public class Projectiles extends Entity{

    //! PRIVATE ATTRIBUTES
    private double rateModifier; //never used but assigned
    private double damageModifier;

    public Projectiles(int type) {
        super(type);
    }

    //! INSTANCE METHODS - utilities
    public EntityLogicalData setValuesDependingOnEnemyType(int type, EntityLogicalData eld, double playerDamage) {

        
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
        this.damage = playerDamage; 
        //System.out.println(">>> Entity data of type " + type + " set");


        return eld;

    }

    //! INSTANCE METHODS - update
    //spawn
    public List<Projectiles> shoot(List<Projectiles> projectiles, Player player, long currentTime) {

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
                        eld.setCoordX(player.getEntityLogicalData().getCoordX());
                        eld.setCoordY(player.getEntityLogicalData().getCoordY());

                        //based on enemy type
                        eld = prj.setValuesDependingOnEnemyType(projType, eld, player.getDamage());
                        prj.setEntityLogicalData(eld);
                        prj.setActive();
                        break;
                    }
                }
            }
        }

        return projectiles;
    }

    //move
    //TODO

    //collision
    @Override
    protected void onCollision(Entity ent) {
        if(Enemies.class.isInstance(ent)) { //only if is passed an enemy
            this.takeDamage(ent.getDamage());
            System.out.println("#> Projectile currenthp: " + this.currentHP);
            if(!this.isAlive()) {
                this.death();
            }
        }
    }

}
