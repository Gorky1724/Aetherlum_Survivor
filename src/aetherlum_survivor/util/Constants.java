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

    //! GAME LOOP RELATED
    public static final int DESIRED_FPS = 60;
    public static final int TIMER_REPEAT_DELAY = (int) (1000 / DESIRED_FPS); //milliseconds: circa 16.67 --(int)-> 16 ms
    
    //basic cadence that regulates some other cadences - in this way everything is proportional to this one = ca. clocks in a second with error
    public static final int BASE_CADENCE = (int) (1000 / TIMER_REPEAT_DELAY); 

    //! ENEMIES SPAWN AND DESPAWN RADIUS
    public static final double SPAWN_RADIUS = Math.max(SCREEN_WIDTH, SCREEN_HEIGHT) * 1.5;
    public static final double DESPAWN_RADIUS = Math.max(SCREEN_WIDTH, SCREEN_HEIGHT) * 1.8; 
    public static final int SPAWN_DESPAWN_CADENCE = 1 * BASE_CADENCE;

    //! ENEMIES MOVEMENT
    public static final double SOVRAPPOSITION_LIMIT_VALUE = 0.8;
    public static final double DISTANCE_POSITIVE_NOT_INFINITESIMAL = 0.0001;

    //! VISUALIZATION
    public static final boolean FLIPPED = true;
    public static final boolean NOT_FLIPPED = false;

    //---------------------------------------------------------------

}
