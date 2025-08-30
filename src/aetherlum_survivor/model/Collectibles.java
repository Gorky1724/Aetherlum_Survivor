package aetherlum_survivor.model;

import java.util.List;

import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityData.EntityStats;
import aetherlum_survivor.util.EntityLogicalData;

public class Collectibles extends Entity {

    //! PRIVATE ATTRIBUTES
    private double collectibleValue;

    //! CONSTRUCTOR
    public Collectibles(int type) {
        super(type);
    }

    //! INSTANCE METHODS - utilities
    private EntityLogicalData setValuesDependingOnCollectibleType(int type, EntityLogicalData eld) {

        EntityStats stats = EntityData.STATS.get(type);
        if (stats == null) {
            System.out.println("!!!>> NULL COLLECTIBLE TYPE NUMBER - This should never print out");
        }

        eld.setWidth(stats.width); eld.setHeight(stats.height); 
        this.speed = stats.speed;
        this.maxHitPoints = stats.maxHP; this.currentHP = this.maxHitPoints;
        this.damage = stats.damage; this.damageResistance = stats.damageResistance;

        this.collectibleValue = stats.collectibleValue;

        eld.setType(type);
        eld.setDirection(EntityData.RIGHT);
        eld.setCondition(EntityData.IDLE);
        eld.setStartingClockOfCondition(Model.getInstance().getClockCyle());

        return eld;
    }

    public void applyEffect(Player player) {
        switch (type) {
            case EntityData.XP_GLOBE_TYPE:
                player.addExp(this.collectibleValue);
                break;
            case EntityData.HP_GLOBE_TYPE:
                player.heal(this.collectibleValue);
                break;
        }
    }
    

    //! INSTANCE METHODS - update
    //spawn
    public List<Collectibles> spawn(List<Collectibles> collectibles, EntityLogicalData playerELD) {
        int currentlyActive = countActive(collectibles);
        int maxCollectibles = EntityData.MAX_COLLECTIBLES_SPAWN;
        if(currentlyActive >= maxCollectibles) { //if no other enemies are allowed to spawn
            return collectibles;
        }

        int[] availableCollectiblesTypes = EntityData.AVAILABLE_COLLECTIBLES_TYPES;

        //spawns random number of collectibles
        double min = 1;
        double max = (double) (maxCollectibles - currentlyActive);
        int toSpawn = 1;
        if (max > min) {
            toSpawn = (int) ((Math.random() * (max - min)) + min);
        }

        int spawned = 0;
        while(spawned < toSpawn) {

            for(Collectibles clt : collectibles) {
                if(spawned >= toSpawn) {
                    break;
                }
                
                //find inactive
                if(!clt.isActive()) {
                    //random type
                    int t = random.nextInt(availableCollectiblesTypes.length);
                    int cltType = availableCollectiblesTypes[t];
                    clt.setType(cltType);
                    //random location
                    EntityLogicalData eld = clt.getEntityLogicalData();
                    double[] spawnPosition = generateSpawnPosition(playerELD.getCoordX(), playerELD.getCoordY());
                    eld.setCoordX(spawnPosition[0]);
                    eld.setCoordY(spawnPosition[1]);

                    //based on type
                    eld = clt.setValuesDependingOnCollectibleType(cltType, eld);
                    clt.setEntityLogicalData(eld);
                    clt.setActive();
                    spawned++;
                }


            }

        }
        return collectibles;
    }

    //collision
    @Override
    public void onCollision(Entity ent) {
        if(Player.class.isInstance(ent)) {
            this.takeDamage(ent.getDamage());
            if(!this.isAlive()) {
                this.death();
            }
        }
    }

}
