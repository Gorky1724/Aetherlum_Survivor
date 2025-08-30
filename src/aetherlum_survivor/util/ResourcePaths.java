package aetherlum_survivor.util;

import java.io.InputStream;

public class ResourcePaths {

    //! BASE DIRECTORIES
    private static final String RESOURCES_BASE = "/resources/";
    private static final String IMAGES_BASE = RESOURCES_BASE + "images/";
    private static final String AUDIO_BASE = RESOURCES_BASE + "audio/";

    //---------------------------------------------------------------------------------------------------------
    //! IMAGES
    public static class Images { //inner class for more subdivision
        //* BACKGROUND
        private static final String BACKGROUNDS = IMAGES_BASE + "backgrounds/";

        public static final String START_PANEL_BCKG = BACKGROUNDS + "start_panel_bckg.jpg";


        //* SCENARIOS
        private static final String SCENARIOS = IMAGES_BASE + "scenarios/";

        //* SPRITES
        private static final String SPRITES = IMAGES_BASE + "sprites/";

        //player
        private static final String PLAYER = SPRITES + "player/";
        public static final String PLAYER_IDLE = PLAYER + "Idle.png";
        public static final String PLAYER_WALKING = PLAYER + "Run.png";
        public static final String PLAYER_DYING = PLAYER + "Dead.png";

        //collectibles
        private static final String COLLECTIBLES = SPRITES + "collectibles/";
        public static final String HEALTH_GLOBE = COLLECTIBLES + "health_globe.png";
        public static final String EXP_GLOBE = COLLECTIBLES + "exp_globe.png";

        //projectiles
        private static final String PROJECTILES = SPRITES + "projectiles/";
        public static final String BASE_PROJECTILE = PROJECTILES + "base.png";
        public static final String FAST_PROJECTILE = PROJECTILES + "fast.png";
        public static final String PIERCING_PROJECTILE = PROJECTILES + "piercing.png";

        //enemies
        private static final String ENEMIES = SPRITES + "enemies/";
        public static final String BASE_ENEMY = ENEMIES + "base.png";
        public static final String FAST_ENEMY = ENEMIES + "fast.png";
        public static final String STATIC_ENEMY = ENEMIES + "static.png";
        public static final String TANK_ENEMY = ENEMIES + "tank.png";

    }

    //---------------------------------------------------------------------------------------------------------
    //! AUDIO
    public static class Audio {
        //* MUSIC
        private static final String MUSIC_BASE = AUDIO_BASE + "music/";

        //* SFX
        private static final String SFX_BASE = AUDIO_BASE + "sfx/";
        
    }

    //---------------------------------------------------------------------------------------------------------
    //! UTILITY
    public static boolean resourceExists(String resourcePath) {
        return ResourcePaths.class.getResource(resourcePath) != null;
    }

    public static InputStream getResourceAsStream(String resourcePath) {
        InputStream is = ResourcePaths.class.getResourceAsStream(resourcePath); //chechks in source folder
        if (is == null) {
            throw new IllegalArgumentException("!!!> Resource not found: " + resourcePath);
        }
        return is;
    }
}