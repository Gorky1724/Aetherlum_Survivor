package aetherlum_survivor.model;

import java.util.List;
import java.util.Random;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityData.EntityStats;
import aetherlum_survivor.util.ScenarioData;
import aetherlum_survivor.util.Constants;

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
        double min = 1;
        double max = (double) (maxEnemies - currentlyActive) / 4;
        int toSpawn = 1;
        if (max > min) {
            toSpawn = (int) ((Math.random() * (max - min)) + min);
        }

        // in a random position
        int spawned = 0;
        while(spawned < toSpawn) {
            
            //finds first inactive
            for (Enemies en : enemies) {
                if(spawned >= toSpawn) {
                    break;
                }

                if(!en.isActive()) {
                    //select random type
                    int t = random.nextInt(allowedEnemiesType.length);
                    int enemyType = allowedEnemiesType[t];
                    en.setType(enemyType);
                    //and random position
                    EntityLogicalData eld = en.getEntityLogicalData();
                    double[] spawnPosition = generateSpawnPosition(playerELD.getCoordX(), playerELD.getCoordY());
                    
                    eld.setCoordX(spawnPosition[0]);
                    eld.setCoordY(spawnPosition[1]);
                    System.out.println("#> Enemies Spawned in coord: "+spawnPosition[0]+" "+spawnPosition[1]);

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
    public void moveTowardsPlayer(EntityLogicalData playerELD, List<Enemies> enemies) {

        // moves towards player
        double directionX = playerELD.getCoordX() - this.eld.getCoordX();
        double directionY = playerELD.getCoordY() - this.eld.getCoordY();
        //normalize
        double distEnPlayer = Math.sqrt(directionX * directionX + directionY * directionY);
        directionX = directionX / distEnPlayer;
        directionY = directionY / distEnPlayer;

        // small "repulsion" caused by other enemies -- to avoid complete sovrapposition
        double repelX = 0;
        double repelY = 0;

        for (Enemies other : enemies) {
            if (other == this) continue; //skips the enemy currently moving

            double distX = this.eld.getCoordX() - other.eld.getCoordX();
            double distY = this.eld.getCoordY() - other.eld.getCoordY();
            double dist = Math.sqrt(distX * distX + distY * distY);

            // minimun distance before repulsion - proportionally based on their dimension
            double minDistanceX = ((this.getEntityLogicalData().getWidth() + other.getEntityLogicalData().getWidth()) * Constants.SOVRAPPOSITION_LIMIT_VALUE) / 2.0;
            double minDistanceY = ((this.getEntityLogicalData().getHeight() * 0.8 + other.getEntityLogicalData().getHeight()) * Constants.SOVRAPPOSITION_LIMIT_VALUE) / 2.0;
            double minDistance = Math.max(minDistanceX, minDistanceY);

            if (dist < minDistance && dist > 0.0001) {
                // repulsive force proportional to their vicinance - with normalization
                double force = (minDistance - dist) / minDistance; //max for dist->0, min for dist->minDistance
                repelX += (distX / dist) * force;
                repelY += (distY / dist) * force;
                //System.out.println("rep: " + repelX+" "+repelY);
            }
        }

        // combining movements of normalized values
        double finalX = directionX + repelX;
        double finalY = directionY + repelY;
    
        double angle = Math.atan2(finalY, finalX);

        // set final movement
        this.eld.setCoordX(this.eld.getCoordX() + Math.cos(angle) * this.speed);
        this.eld.setCoordY(this.eld.getCoordY() + Math.sin(angle) * this.speed);
    }

    //collision
    @Override
    protected void onCollision(Entity ent) {
        if(Projectiles.class.isInstance(ent)) { //only if is passed a projectile
            Projectiles prj = (Projectiles) (ent); //converts to be able to use projectiles methods
            this.takeDamage(prj.getDamage()*prj.getDamageModifier());
            //System.out.println("#> Enemy currenthp: " + this.currentHP);
            if(!this.isAlive()) {
                this.death();

                //extracs projectile owner (Player) and removes it hp
                Player pl = (Player) prj.getOwner();
                pl.addExp(this.expGiven);
                //System.out.println("#> added xp amount: " + this.expGiven);
            }
        }
    }
}