package aetherlum_survivor.model;

import aetherlum_survivor.util.Constants;

public class Player extends Entity {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private double startX = 0;
    private double startY = 0;
    private double baseWidth = Constants.TILE_SIZE;
    private double baseHeight = Constants.TILE_SIZE;

    //game stats

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Player() {
        this.setCoordX(this.startX);
        this.setCoordY(this.startY);;
        this.setWidth(baseWidth);
        this.setHeight(baseHeight);;
    }
    
}
