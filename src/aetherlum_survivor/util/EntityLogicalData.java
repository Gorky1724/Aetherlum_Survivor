package aetherlum_survivor.util;

public class EntityLogicalData {

    // active/inactive - not used by Player but by the other classes
    protected boolean status; //false: inactive - true: active
    
    //logical coordinates
    private double coordX;
    private double coordY;
    private double width;
    private double height;

    //type and condition data
    private int type;
    private int direction;
    private int condition;
    private long startingClockOfCondition;

    public EntityLogicalData(double coordX, double coordY, double width, double height) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;
    }

    public EntityLogicalData(boolean status, double coordX, double coordY, double width, double height, int type) {
        this.status = status;
        
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;

        this.type = type;
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

    // TYPE AND CONDITION_____________________________
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getDirection() {
        return this.direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCondition() {
        return this.condition;
    }
    public void setCondition(int condition) {
        this.condition = condition;
    }

    public long getStartingClockOfCondition() {
        return this.startingClockOfCondition;
    }
    public void setStartingClockOfCondition(long startingClockOfCondition) {
        this.startingClockOfCondition = startingClockOfCondition;
    }

    //---------------------------------------------------------------
}
