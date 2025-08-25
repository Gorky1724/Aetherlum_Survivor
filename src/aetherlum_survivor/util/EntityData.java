package aetherlum_survivor.util;

import java.util.Map;

public class EntityData {

    //! GENERIC VALUE
    public static final boolean INACTIVE_STATUS = false;
    public static final int NULL_VALUE = -1;
    public static final int NOT_USEFUL_VALUE = 0;

    //! INNER CLASS structur for having compact data
    public static class EntityStats {
        //common data
        public final int width, height;
        public final double speed, maxHP, damage, damageResistance;
        public final String spritePath;

        //enemies data
        public final double expGiven;

        //projectiles data
        public final double proj_rate_mod, proj_dmg_mod;
        
        //enemies data constructor
        public EntityStats(int width, int height, double speed, double maxHP, double damage, double damageResistance, String spritePath, double expGiven) {
            this.width = width; this.height = height;
            this.speed = speed; this.maxHP = maxHP; this.damage = damage; this.damageResistance = damageResistance;
            this.spritePath = spritePath;

            //enemies data
            this.expGiven = expGiven;

            //not used data
            this.proj_rate_mod = NOT_USEFUL_VALUE;
            this.proj_dmg_mod = NOT_USEFUL_VALUE;
        }

        //projectile data constructor
        public EntityStats(int width, int height, double speed, double maxHP, double damage, double damageResistance, String spritePath, double proj_rate_mod, double proj_dmg_mod) {
            this.width = width; this.height = height;
            this.speed = speed; this.maxHP = maxHP; this.damage = damage; this.damageResistance = damageResistance;
            this.spritePath = spritePath;

            //projectiles data
            this.proj_rate_mod = proj_rate_mod;
            this.proj_dmg_mod = proj_dmg_mod;

            //not used data
            this.expGiven = NOT_USEFUL_VALUE;
        }

    }

    //! PLAYER FIRENDLY-------------------------------------------------------------------------------------
    public static final int PLAYER_TYPE = 0;
    public static final int PLAYER_WIDTH = Constants.TILE_SIZE;
    public static final int PLAYER_HEIGHT = Constants.TILE_SIZE;
    public static final double PLAYER_SPD = 10;
    public static final double PLAYER_MAX_HP = 1000;
    public static final double PLAYER_DMG = 10;
    public static final double PLAYER_DMG_RST = 0; //0 <= dmg_rst <= 1 -- 1 = undamagable
    public static final String PLAYER_SPRITE_PATH = "WHITE";

    public static final double XP_BAR = 100;
    public static final int MAX_LEVEL = 25;

    public static final int PLAYER_FIRE_RATE = 1000; //ms

    //! COLLECTIBLES -------------------------------------------------------------------------------------
    public static final int[] COLLECTIBLES_TYPE_RANGE = {10,100}; //included
    public static final int MAX_COLLECTIBLES_SPAWN = 10;

    public static final int EXP_GLOBE_TYPE = 10;
    public static final int EXP_GLOBE_EXP_GIVEN = 50;
    public static final int EXP_GLOBE_WIDTH = Constants.TILE_SIZE;
    public static final int EXP_GLOBE_HEIGHT = Constants.TILE_SIZE;
    public static final String EXP_GLOBE_SPRITE_PATH = "BLUE";

    public static final int HEALTH_GLOBE_TYPE = 20;
    public static final int HEALTH_GLOBE_HEALTH_GIVEN = 25;
    public static final int HEALTH_GLOBE_WIDTH = Constants.TILE_SIZE;
    public static final int HEALTH_GLOBE_HEIGHT = Constants.TILE_SIZE;
    public static final String HEALTH_GLOBE_SPRITE_PATH = "RED";
    
    //! PROJECTILES -------------------------------------------------------------------------------------
    public static final int[] PROJECTILES_TYPE_RANGE = {400,700};
    public static final int MAX_PROJECTILES_SPAWN = 15;

    public static final int BASE_PROJ_TYPE = 400;
    public static final int BASE_PROJ_WIDTH = (int) (Constants.TILE_SIZE*0.5);
    public static final int BASE_PROJ_HEIGHT = (int) (Constants.TILE_SIZE*0.5);
    public static final double BASE_PROJ_RATE_MOD = 1; //doesen't alter fire rate
    public static final double BASE_PROJ_DMG_MOD = 1;
    public static final double BASE_PROJ_SPD = PLAYER_SPD*2; //initial value based on initial player spd value
    public static final double BASE_PROJ_MAX_HP = 1; //destroyed when hits first enemy
    public static final String BASE_PROJ_SPRITE_PATH = "CYAN";
    //dmg is based on player damage value

    //pierces enemies by not being destroyed by first impact bcause it has more hp
    public static final int PIERCING_PROJ_TYPE = 410;
    public static final int PIERCING_PROJ_WIDTH = (int) (Constants.TILE_SIZE*0.3);
    public static final int PIERCING_PROJ_HEIGHT = (int) (Constants.TILE_SIZE*0.3);
    public static final double PIERCING_PROJ_RATE_MOD = 1.5; //slower fire rate
    public static final double PIERCING_PROJ_DMG_MOD = 0.8; //deals less dmg
    public static final double PIERCING_PROJ_SPD = PLAYER_SPD*1.4; 
    public static final double PIERCING_PROJ_MAX_HP = PLAYER_MAX_HP*0.5; //based on player base hp value
    public static final String PIERCING_PROJ_SPRITE_PATH = "RED";

    public static final int FAST_PROJ_TYPE = 420;
    public static final int FAST_PROJ_WIDTH = (int) (Constants.TILE_SIZE*0.2);
    public static final int FAST_PROJ_HEIGHT = (int) (Constants.TILE_SIZE*0.2);
    public static final double FAST_PROJ_RATE_MOD = 0.5; //slower fire rate
    public static final double FAST_PROJ_DMG_MOD = 0.6; //deals less dmg
    public static final double FAST_PROJ_SPD = PLAYER_SPD*2.5; 
    public static final double FAST_PROJ_MAX_HP = 1;
    public static final String FAST_PROJ_SPRITE_PATH = "PINK";

    //! ENEMIES -------------------------------------------------------------------------------------
    public static final int[] ENEMIES_TYPE_RANGE = {1000,1200};

    public static final int BASE_ENEMY_TYPE = 1000;
    public static final int BASE_ENEMY_WIDTH = Constants.TILE_SIZE;
    public static final int BASE_ENEMY_HEIGHT = Constants.TILE_SIZE;
    public static final double BASE_ENEMY_SPD = 6;
    public static final double BASE_ENEMY_MAX_HP = 30;
    public static final double BASE_ENEMY_DMG = 10;
    public static final double BASE_ENEMY_DMG_RST = 0;
    public static final int BASE_ENEMY_EXP_GIVEN = 50;
    public static final String BASE_ENEMY_SPRITE_PATH = "MAGENTA";

    public static final int FAST_ENEMY_TYPE = 1001;
    public static final int FAST_ENEMY_WIDTH = (int) (Constants.TILE_SIZE*0.7);
    public static final int FAST_ENEMY_HEIGHT = (int) (Constants.TILE_SIZE*0.7);
    public static final double FAST_ENEMY_SPD = 8;
    public static final double FAST_ENEMY_MAX_HP = 20;
    public static final double FAST_ENEMY_DMG = 10;
    public static final double FAST_ENEMY_DMG_RST = 0;
    public static final int FAST_ENEMY_EXP_GIVEN = 100;
    public static final String FAST_ENEMY_SPRITE_PATH = "GRAY";

    public static final int TANK_ENEMY_TYPE = 1002;
    public static final int TANK_ENEMY_WIDTH = (int) (Constants.TILE_SIZE*1.5);
    public static final int TANK_ENEMY_HEIGHT = (int) (Constants.TILE_SIZE*1.5);
    public static final double TANK_ENEMY_SPD = 4;
    public static final double TANK_ENEMY_MAX_HP = 50;
    public static final double TANK_ENEMY_DMG = 7;
    public static final double TANK_ENEMY_DMG_RST = 0.2;
    public static final int TANK_ENEMY_EXP_GIVEN = 100;
    public static final String TANK_ENEMY_SPRITE_PATH = "GREEN";

    public static final int STATIC_ENEMY_TYPE = 1010;
    public static final int STATIC_ENEMY_WIDTH = (int) (Constants.TILE_SIZE*3);
    public static final int STATIC_ENEMY_HEIGHT = (int) (Constants.TILE_SIZE*3);
    public static final double STATIC_ENEMY_SPD = 0;
    public static final double STATIC_ENEMY_MAX_HP = 30;
    public static final double STATIC_ENEMY_DMG = 15;
    public static final double STATIC_ENEMY_DMG_RST = 0.3;
    public static final int STATIC_ENEMY_EXP_GIVEN = 20;
    public static final String STATIC_ENEMY_SPRITE_PATH = "YELLOW";

    //! STATS OF ALL ENTITY mapped with the type as the key
    //  to easily extract data when assignign them to spawned enemies
    public static final Map<Integer, EntityStats> STATS = Map.of(
        //projectiles
        BASE_PROJ_TYPE, new EntityStats(BASE_PROJ_WIDTH, BASE_PROJ_HEIGHT, BASE_PROJ_SPD, BASE_PROJ_MAX_HP, NOT_USEFUL_VALUE, NOT_USEFUL_VALUE, BASE_PROJ_SPRITE_PATH, BASE_PROJ_RATE_MOD, BASE_PROJ_DMG_MOD),
        PIERCING_PROJ_TYPE, new EntityStats(PIERCING_PROJ_WIDTH, PIERCING_PROJ_HEIGHT, PIERCING_PROJ_SPD, PIERCING_PROJ_MAX_HP, NOT_USEFUL_VALUE, NOT_USEFUL_VALUE, PIERCING_PROJ_SPRITE_PATH, PIERCING_PROJ_RATE_MOD, PIERCING_PROJ_DMG_MOD),
        FAST_PROJ_TYPE, new EntityStats(FAST_PROJ_WIDTH, FAST_PROJ_HEIGHT, FAST_PROJ_SPD, FAST_PROJ_MAX_HP, NOT_USEFUL_VALUE, NOT_USEFUL_VALUE, FAST_PROJ_SPRITE_PATH, FAST_PROJ_RATE_MOD, FAST_PROJ_DMG_MOD),
        //enemies
        BASE_ENEMY_TYPE, new EntityStats(BASE_ENEMY_WIDTH, BASE_ENEMY_HEIGHT, BASE_ENEMY_SPD, BASE_ENEMY_MAX_HP, BASE_ENEMY_DMG, BASE_ENEMY_DMG_RST, BASE_ENEMY_SPRITE_PATH, BASE_ENEMY_EXP_GIVEN),
        FAST_ENEMY_TYPE, new EntityStats(FAST_ENEMY_WIDTH, FAST_ENEMY_HEIGHT, FAST_ENEMY_SPD, FAST_ENEMY_MAX_HP, FAST_ENEMY_DMG, FAST_ENEMY_DMG_RST, FAST_ENEMY_SPRITE_PATH, FAST_ENEMY_EXP_GIVEN),
        TANK_ENEMY_TYPE, new EntityStats(TANK_ENEMY_WIDTH, TANK_ENEMY_HEIGHT, TANK_ENEMY_SPD, TANK_ENEMY_MAX_HP, TANK_ENEMY_DMG, TANK_ENEMY_DMG_RST, TANK_ENEMY_SPRITE_PATH, TANK_ENEMY_EXP_GIVEN),
        STATIC_ENEMY_TYPE, new EntityStats(STATIC_ENEMY_WIDTH, STATIC_ENEMY_HEIGHT, STATIC_ENEMY_SPD, STATIC_ENEMY_MAX_HP, STATIC_ENEMY_DMG, STATIC_ENEMY_DMG_RST, STATIC_ENEMY_SPRITE_PATH, STATIC_ENEMY_EXP_GIVEN)
    );
    
}