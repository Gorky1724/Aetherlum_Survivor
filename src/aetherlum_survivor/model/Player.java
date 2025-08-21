package aetherlum_survivor.model;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;

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
            deltaX += 1;
        if (pressedRightKey)
            deltaY += 1;
        if (pressedLeftKey)
            deltaY -= 1;
        
        //if keyboard inputs nullify each other
        if (deltaX == 0 && deltaY == 0) {
            return;
        } else if (deltaX != 0 && deltaY != 0) { //diagonal movement - needs normalization
            double normalizationFactor = 0.707; //circa 1/(sqrt(2))
            this.player.se

        }
    }
    //---------------------------------------------------------------
}
