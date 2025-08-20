package aetherlum_survivor.util;

public class Constants {

    //---------------------------------------------------------------
	//! GRAPHICAL GAMEPANEL DATA

    public static final int originalTileSize = 16; //16x16 pixel tile
    public static final int rescale = 3;

    public static final int TILE_SIZE = originalTileSize * rescale; //48x48

    public static final int maxScreenColumns = 16;
    public static final int maxScreenRows = 12;

    public static final int SCREEN_WIDTH = TILE_SIZE * maxScreenColumns; 
    public static final int SCREEN_HEIGHT = TILE_SIZE * maxScreenRows;
    
    /*
    //* DA ELIMINARE
    //some tiles are not shown to the player - they're where enemies spawns
    public static final int hiddenScreenColumns = 4;
    public static final int hiddenScreenRows = 4;

    public static final int shownScreenColumns = maxScreenColumns - hiddenScreenColumns;
    public static final int shownScreenRows = maxScreenRows - hiddenScreenRows;

    public static final int SHOWN_SCREEN_WIDTH = tileSize * shownScreenColumns; 
    public static final int SHOWN_SCREEN_HEIGHT = tileSize * shownScreenRows;
    */

    //---------------------------------------------------------------
    
}
