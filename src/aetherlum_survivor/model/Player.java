package aetherlum_survivor.model;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityTypes;

public class Player extends Entity {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private double startX = 0;
    private double startY = 0;
    private double baseWidth = Constants.TILE_SIZE;
    private double baseHeight = Constants.TILE_SIZE;

    //game stats
    private int type = EntityTypes.PLAYER;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Player() {
        this.createAndSetEntityGraphicalData(startY, startX, baseWidth, baseHeight);

        this.setType(this.type);
    }
    
}
