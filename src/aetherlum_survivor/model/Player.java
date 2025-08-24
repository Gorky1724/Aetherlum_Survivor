package aetherlum_survivor.model;

import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityLogicalData;

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
    private double xpBar;


    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Player() {
        super(EntityData.PLAYER_TYPE);

        this.setActive(); //set status - not actually needed

        this.speed = EntityData.PLAYER_SPD;
        this.maxHitPoints = EntityData.PLAYER_MAX_HP;
        this.currentHP = this.maxHitPoints;
        this.damage = EntityData.PLAYER_DMG;
        this.damageResistance = EntityData.PLAYER_DMG_RST;
        this.createAndSetEntityLogicalData(startY, startX, baseWidth, baseHeight);

        //player-only
        this.xpBar = EntityData.XP_BAR;
    }

    //---------------------------------------------------------------
	//! PUBLIC METHODS
    public void movePlayer(boolean pressedUpKey, boolean pressedRightKey, boolean pressedDownKey, boolean pressedLeftKey) {
        
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

    //exp methods
    public void addExp(double xpGained) {
        if(this.level <= EntityData.MAX_LEVEL) {
            this.currentExp += xpGained;
            if(this.currentExp >= this.xpBar){
                levelUp();
            }
        }
    }
    public void levelUp() {
        this.level++;
        this.currentExp -= this.xpBar;
        this.xpBar = this.xpBar*(this.level/2);
        //TODO - open levelupPanel with selections of which stat to boost
    }
    //---------------------------------------------------------------
}
