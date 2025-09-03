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
        public static final String LEVELUP_PANEL_BCKG = BACKGROUNDS + "levelup_panel_bckg.jpg";
        public static final String SCENARIO_PANEL_BCKG = BACKGROUNDS + "scenario_panel_bckg.jpg";
        public static final String PAUSE_PANEL_BCKG = BACKGROUNDS + "pause_panel_bckg.jpg";
        public static final String GAMEOVER_PANEL_BCKG = BACKGROUNDS + "gameover_panel_bckg.jpg";
        public static final String SETTINGS_PANEL_BCKG = BACKGROUNDS + "settings_panel_bckg.jpg";


        //* SCENARIOS
        private static final String SCENARIOS = IMAGES_BASE + "scenarios/";
        public static final String SCEN_1 = SCENARIOS + "scenario1.png";
        public static final String SCEN_2 = SCENARIOS + "scenario2.png";
        public static final String SCEN_3 = SCENARIOS + "scenario3.png";

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
        public static final String HEALTH_TAKEN = COLLECTIBLES + "health_taken.png";
        public static final String EXP_GLOBE = COLLECTIBLES + "exp_globe.png";
        public static final String EXP_TAKEN = COLLECTIBLES + "exp_taken.png";

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
        private static final String MUSIC = AUDIO_BASE + "music/";
        public static final String START_PANEL_MUSIC = MUSIC + "start_panel_music.wav";
        public static final String LEVELUP_PANEL_MUSIC = MUSIC + "levelup_panel_music.wav";
        public static final String SCENARIO_PANEL_MUSIC = MUSIC + "scenario_panel_music.wav";
        public static final String PAUSE_PANEL_MUSIC = MUSIC + "pause_panel_music.wav";
        public static final String GAMEOVER_PANEL_MUSIC = MUSIC + "gameover_panel_music.wav";
        public static final String SETTINGS_PANEL_MUSIC = MUSIC + "settings_panel_music.wav";
        public static final String GAME_PANEL_MUSIC = MUSIC + "game_panel_music.wav";

        //* SFX
        private static final String SFX = AUDIO_BASE + "sfx/";
        public static final String BASEPROJ_SHOT_SFX = SFX + "baseprj_shot_sfx.wav";
        public static final String FASTPROJ_SHOT_SFX = SFX + "fastprj_shot_sfx.wav";
        public static final String PIERCINGPROJ_SHOT_SFX = SFX + "piercingprj_shot_sfx.wav";
        public static final String COLLECTIBLE_TAKEN_SFX = SFX + "collectible_taken_sfx.wav";
        public static final String PANEL_CHANGE_SFX = SFX + "panel_change_sfx.wav";
        public static final String LEVEL_UP_SFX = SFX + "level_up_sfx.wav";
        
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