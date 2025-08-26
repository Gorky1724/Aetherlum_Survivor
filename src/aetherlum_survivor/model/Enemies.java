package aetherlum_survivor.model;

import java.util.List;
import java.util.Random;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityData.EntityStats;
import aetherlum_survivor.util.ScenarioData;

public class Enemies extends Entity {

    //! ATTRIBUTES
    private double expGiven;

    //! CONSTRUCTOR
    public Enemies(int type) {
        super(type);
    }

    //! PUBLIC METHODS - utilities
    public EntityLogicalData setValuesDependingOnEnemyType(int type, EntityLogicalData eld) {

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
        
        this.expGiven = stats.expGiven;
        //System.out.println(">>> Entity data of type " + type + " set");
        
        return eld;
    }

    //! PUBLIC METHODS - update
    //spawn
    public List<Enemies> spawn(List<Enemies> enemies, ScenarioData sd, EntityLogicalData playerELD) {

        int currentlyActive = countActive(enemies);
        int maxEnemies = sd.getEnemiesMaxNum();

        //if no other enemies are allowed to spawn
        if (currentlyActive >= maxEnemies) {
            return enemies;
        }

        int[] allowedEnemiesType = sd.getEnemiesTypesAvaliable();

        //spawns random number of enemies
        Random rand = new Random();
        double min = 1;
        double max = (double) (maxEnemies - currentlyActive) / 4;
        int toSpawn = 1;
        if (max > min) {
            toSpawn = (int) ((Math.random() * (max - min)) + min);
        }

        int spawned = 0;
        while(spawned < toSpawn) {
            //select random type
            int t = rand.nextInt(allowedEnemiesType.length);
            int enemyType = allowedEnemiesType[t];

            double[] spawnPosition = generateSpawnPosition(playerELD.getCoordX(), playerELD.getCoordY());
            
            //finds first inactive
            for (Enemies en : enemies) {
                if(spawned >= toSpawn) {
                    break;
                }

                if(!en.isActive()) {
                    en.setType(enemyType);
                    EntityLogicalData eld = en.getEntityLogicalData();
                    eld.setCoordX(spawnPosition[0]);
                    eld.setCoordY(spawnPosition[1]);

                    //based on enemy type
                    eld = en.setValuesDependingOnEnemyType(enemyType, eld);
                    en.setEntityLogicalData(eld);
                    en.setActive();
                    spawned++;
                }
            }
        }
        //System.out.println("#> Enemies Spawned");
        return enemies;
    }

    //move
    public void moveTowardsPlayer(EntityLogicalData playerELD) {
        double angle = Math.atan2(playerELD.getCoordY() - this.eld.getCoordY(), playerELD.getCoordX() - this.eld.getCoordX());
        
        this.eld.setCoordX(this.eld.getCoordX() + Math.cos(angle) * this.speed);
        this.eld.setCoordY(this.eld.getCoordY() + Math.sin(angle) * this.speed);
    }

    //collision
    @Override
    protected void onCollision(Entity ent) {
        if(Projectiles.class.isInstance(ent)) { //only if is passed an enemy
            this.takeDamage(ent.getDamage());
            //System.out.println("#> Enemy currenthp: " + this.currentHP);
            if(!this.isAlive()) {
                this.death();

                //extracs projectile owner (Player) and removes it hp
                Projectiles prj = (Projectiles) (ent);
                Player pl = (Player) prj.getOwner();
                pl.addExp(this.expGiven);
                //System.out.println("#> added xp amount: " + this.expGiven);
            }
        }
    }
}