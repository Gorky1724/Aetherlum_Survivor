package aetherlum_survivor.model;

public  class Entity{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    //position and collision
    private double coordX;
    private double coordY;
    private double width;
    private double height;

    //game stats

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    public Entity() {
        //do nothing
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    protected double getCoordX() {
        return this.coordX;
    }

    protected double getCoordY() {
        return this.coordY;
    }

    protected double getWidth() {
        return this.width;
    }

    protected double getHeight() {
        return this.height;
    }

    protected void setCoordX(double newX) {
        this.coordX = newX;
    }

    protected void setCoordY(double newY) {
        this.coordY = newY;
    }

    protected void setWidth(double newWidth) {
        this.width = newWidth;
    }

    protected void setHeight(double newHeight) {
        this.height = newHeight;
    }

    //---------------------------------------------------------------

    
}
