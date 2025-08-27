package aetherlum_survivor.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.LevelUpData;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public class Player extends Entity {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private double startX = 0;
    private double startY = 0;
    private double baseWidth = EntityData.PLAYER_WIDTH;
    private double baseHeight = EntityData.PLAYER_HEIGHT;

    //exp
    private double currentExp = 0;
    private int level = 1; 
    private int maxLevel = EntityData.MAX_LEVEL;
    private double xpBar;

    //level up options
    private Map<Integer, LevelUpOptions> availableLevelUps;
    private Map<Integer, LevelUpOptions> availableProjUnlock;
    private int numProjOwned;

    //projectiles
    private int fireRate;
    private List<Long[]> availableProjectiles; //long needed for System.currentTimeMillis()



    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Player() {
        super(EntityData.PLAYER_TYPE);

        this.speed = EntityData.PLAYER_SPD;
        this.maxHitPoints = EntityData.PLAYER_MAX_HP;
        this.currentHP = this.maxHitPoints;
        this.damage = EntityData.PLAYER_DMG;
        this.damageResistance = EntityData.PLAYER_DMG_RST;
        this.createAndSetEntityLogicalData(startY, startX, baseWidth, baseHeight);

        this.setActive(); //set status - not actually needed

        //xp
        this.xpBar = EntityData.XP_BAR;

        //firing projectiles
        this.fireRate = EntityData.PLAYER_FIRE_RATE;
        long lastShot = System.currentTimeMillis(); //saves last time the projectile of TYPE has been shot
        availableProjectiles = new ArrayList<>();
        Long[] type_lastShot = {(long) EntityData.BASE_PROJ_TYPE, lastShot};
        this.availableProjectiles.add(type_lastShot); //TODO - incremented via levelup or boosts
        this.numProjOwned = 1;
        /*TEST if all types of projectile spawns
        Long[] piercing = {(long) EntityData.PIERCING_PROJ_TYPE, lastShot};
        Long[] fast = {(long) EntityData.FAST_PROJ_TYPE, lastShot};
        this.availableProjectiles.add(piercing);
        this.availableProjectiles.add(fast);
        */

        // available level-ups
        this.availableLevelUps = new HashMap<>(LevelUpData.LEVEL_UP_OPTIONS);
        this.availableProjUnlock = new HashMap<>(LevelUpData.UNLOCK_PROJ_OPTIONS);

    }

    //---------------------------------------------------------------
	//! PUBLIC METHODS - getter
    public int getFireRate() {
        return this.fireRate;
    }
    public List<Long[]> getAvailableProjectiles() {
        return this.availableProjectiles;
    }

    public double getCurrentXp() {
        return this.currentExp;
    }
    public double getXpBar() {
        return this.xpBar;
    }
    public int getLevel() {
        return this.level;
    }
    
    //! PUBLIC METHODS - update
    //move
    protected void movePlayer(boolean pressedUpKey, boolean pressedRightKey, boolean pressedDownKey, boolean pressedLeftKey) {
        
        int deltaX = 0, deltaY = 0;
        
        if (pressedUpKey)
            deltaY -= 1;
        if (pressedDownKey)
            deltaY += 1;
        if (pressedRightKey)
            deltaX += 1;
        if (pressedLeftKey)
            deltaX -= 1;
        
        EntityLogicalData playerELD = this.eld;
        double currentX = playerELD.getCoordX();
        double currentY = playerELD.getCoordY();
        double newX;
        double newY;
        if (deltaX == 0 && deltaY == 0) { //if keyboard inputs nullify each other
            return;
        } else if (deltaX != 0 && deltaY != 0) { //diagonal movement - needs normalization
            double normalizationFactor = 0.707; //circa 1/(sqrt(2))
            newX = currentX + deltaX * this.speed * normalizationFactor;
            newY = currentY + deltaY * this.speed * normalizationFactor;
        } else { //normal increment
            newX = currentX + deltaX * this.speed;
            newY = currentY + deltaY * this.speed;
        }
        playerELD.setCoordX(newX);
        playerELD.setCoordY(newY);
        
        //System.out.println("#> ("+newX+", "+newY+")");
    }

    //collision
    @Override
    protected void onCollision(Entity ent) {
        if(Enemies.class.isInstance(ent)) { //only if is passed an enemy
            this.takeDamage(ent.getDamage());
            System.out.println("#> currenthp: " + this.currentHP);
            if(!this.isAlive()) {
                Model.getInstance().setGameOver();
            }
        }

        if(Collectibles.class.isInstance(ent)) { //only if is passed a collectible
            //TODO
        }
    }

    //exp methods
    protected void addExp(double xpGained) {
        if(this.level < maxLevel) {
            this.currentExp += xpGained;
            if(this.currentExp >= this.xpBar){
                levelUp();
            }
        }
        //System.out.println("#> Player exp: "+ this.currentExp);
    }
    protected void levelUp() {
        this.level++;
        this.currentExp -= this.xpBar;
        this.xpBar = this.xpBar*(this.level/2);
        //System.out.println("#> Player lvl: "+ this.level);
        
        //randomizes available upgrades
        Map<Integer, LevelUpOptions> randomLvlUp = new HashMap<>();
        // if level = multiple_of_LVL_PRJ_UNLOCK_INTERVAL
        if (this.level == LevelUpData.LVL_PRJ_UNLOCK_INTERVAL * this.numProjOwned) {
            int projCode = 100 + this.numProjOwned;
            randomLvlUp.put(projCode, availableProjUnlock.get(projCode));
        }
        // randomly selects power ups based on remaining slots
        List<Integer> allCodes = new ArrayList<>(availableLevelUps.keySet());
        Collections.shuffle(allCodes);
        int optionsToAdd = LevelUpData.NUM_OPTIONS - randomLvlUp.size();
        for (int i = 0; i < optionsToAdd; i++) {
            int key = allCodes.get(i);
            randomLvlUp.put(key, availableLevelUps.get(key));
        }

        Controller.getInstance().handleLevelUp(randomLvlUp);
    }
    //---------------------------------------------------------------
}
