package aetherlum_survivor.util;

public class EntityLogicalData {

    // active/inactive - not used by Player but by the other classes
    protected boolean status; //false: inactive - true: active
    
    //logical coordinates
    private double coordX;
    private double coordY;
    private double width;
    private double height;

    //visualization
    private String spritePath;

    public EntityLogicalData(double coordX, double coordY, double width, double height) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;
    }

    public EntityLogicalData(boolean status, double coordX, double coordY, double width, double height, String spritePath) {
        this.status = status;
        
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;

        this.spritePath = spritePath;
    }

    // STATUS_____________________________
    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(boolean newStatus) {
        this.status = newStatus;
    }
    public boolean isActive() {
        return this.status;
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

    // VISUALIZATION_____________________________
    public String getSpritePath() {
        return this.spritePath;
    }

    public void setSpritePath(String newSpritePath) {
        this.spritePath = newSpritePath;
    }

    //---------------------------------------------------------------
}
