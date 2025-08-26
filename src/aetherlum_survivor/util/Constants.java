package aetherlum_survivor.util;

public class Constants {

    //---------------------------------------------------------------
	//! GRAPHICAL GAMEPANEL DATA

    public static final int originalTileSize = 16; //16x16 pixel tile
    public static final int rescale = 2;

    public static final int TILE_SIZE = originalTileSize * rescale; //48x48

    public static final int maxScreenColumns = 24;
    public static final int maxScreenRows = 18;

    public static final int SCREEN_WIDTH = TILE_SIZE * maxScreenColumns; 
    public static final int SCREEN_HEIGHT = TILE_SIZE * maxScreenRows;

    //! ENEMIES SPAWN AND DESPAWN RADIUS
    public static final double SPAWN_RADIUS = Math.max(SCREEN_WIDTH, SCREEN_HEIGHT) * 1.5;
    public static final double DESPAWN_RADIUS = Math.max(SCREEN_WIDTH, SCREEN_HEIGHT) * 1.8; 

    //! ENEMIES MOVEMENT
    public static final int MOVING_CADENCE = 500; //ms - entities doesen't move every tick as the player does
    public static final double SOVRAPPOSITION_LIMIT_VALUE = 0.8;

    //! GAME LOOP TIMER RELATED
    public static final int DESIRED_FPS = 60;
    public static final int TIMER_REPEAT_DELAY = 1000 / DESIRED_FPS; //milliseconds: circa 16.67 --(int)-> 16 ms

    public static final int SPAWN_DESPAWN_CADENCE = 1000; //ms

    //---------------------------------------------------------------

}
