package aetherlum_survivor.util;

public class EntityGraphicalData {

    private double coordX;
    private double coordY;
    private double width;
    private double height;

    public EntityGraphicalData(double coordX, double coordY, double width, double height) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;
    }
    
    // POSITION AND COLLISION_____________________________
    public double getCoordX() {
        return this.coordX;
    }

    public double getCoordY() {
        return this.coordY;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setCoordX(double newX) {
        this.coordX = newX;
    }

    public void setCoordY(double newY) {
        this.coordY = newY;
    }

    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    public void setHeight(double newHeight) {
        this.height = newHeight;
    }

    //---------------------------------------------------------------
}
