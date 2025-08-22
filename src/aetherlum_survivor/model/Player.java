package aetherlum_survivor.model;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.EntityLogicalData;

public class Player extends Entity {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private double startX = 0;
    private double startY = 0;
    private double baseWidth = Constants.TILE_SIZE;
    private double baseHeight = Constants.TILE_SIZE;


    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Player() {
        this.createAndSetEntityGraphicalData(startY, startX, baseWidth, baseHeight);

        this.setType(EntityData.PLAYER_TYPE);
        this.setSpeed(EntityData.PLAYER_SPEED);
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
        
        EntityLogicalData playerELD = getEntityGraphicalData();
        double currentX = playerELD.getCoordX();
        double currentY = playerELD.getCoordY();
        double newX;
        double newY;
        if (deltaX == 0 && deltaY == 0) { //if keyboard inputs nullify each other
            return;
        } else if (deltaX != 0 && deltaY != 0) { //diagonal movement - needs normalization
            double normalizationFactor = 0.707; //circa 1/(sqrt(2))
            newX = currentX + deltaX * getSpeed() * normalizationFactor;
            newY = currentY + deltaY * getSpeed() * normalizationFactor;
        } else { //normal increment
            newX = currentX + deltaX * getSpeed();
            newY = currentY + deltaY * getSpeed();
        }
        playerELD.setCoordX(newX);
        playerELD.setCoordY(newY);
        
        System.out.println(">> ("+newX+", "+newY+")");
    }
    //---------------------------------------------------------------
}
