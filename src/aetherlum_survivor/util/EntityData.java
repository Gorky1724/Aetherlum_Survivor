package aetherlum_survivor.util;

import java.util.Map;

public class EntityData {

    //! GENERIC VALUE
    public static final int NULL_VALUE = -1;

    //! INNER CLASS structur for having compact data
    public static class EntityStats {
        public final int width, height;
        public final double speed, maxHP, damage, damageResistance;
        
        public EntityStats(int width, int height, double speed, double maxHP, double damage, double damageResistance) {
            this.width = width; this.height = height; this.speed = speed;
            this.maxHP = maxHP; this.damage = damage; this.damageResistance = damageResistance;
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

    public static final double XP_BAR = 100;
    public static final int MAX_LEVEL = 25;

    //! COLLECTIBLES -------------------------------------------------------------------------------------
    public static final int[] COLLECTIBLES_TYPE_RANGE = {10,100}; //included
    public static final int MAX_COLLECTIBLES_SPAWN = 10;

    public static final int EXP_GLOBE_TYPE = 10;
    public static final int EXP_GLOBE_EXP_GIVEN = 50;
    public static final int EXP_GLOBE_WIDTH = Constants.TILE_SIZE;
    public static final int EXP_GLOBE_HEIGHT = Constants.TILE_SIZE;
    public static final int EXP_GLOBE_NOT_USEFUL_STAT = 0;

    public static final int HEALTH_GLOBE_TYPE = 20;
    public static final int HEALTH_GLOBE_HEALTH_GIVEN = 25;
    public static final int HEALTH_GLOBE_WIDTH = Constants.TILE_SIZE;
    public static final int HEALTH_GLOBE_HEIGHT = Constants.TILE_SIZE;
    public static final int HEALTH_GLOBE_NOT_USEFUL_STAT = 0;
    
    //! PROJECTILES -------------------------------------------------------------------------------------
    public static final int[] PROJECTILES_TYPE_RANGE = {400,700};
    public static final int MAX_PROJECTILES_SPAWN = 1;

    //! ENEMIES -------------------------------------------------------------------------------------
    public static final int[] ENEMIES_TYPE_RANGE = {1000,1200};

    public static final int BASE_ENEMY_TYPE = 1000;
    public static final int BASE_ENEMY_WIDTH = Constants.TILE_SIZE;
    public static final int BASE_ENEMY_HEIGHT = Constants.TILE_SIZE;
    public static final double BASE_ENEMY_SPD = 8;
    public static final double BASE_ENEMY_MAX_HP = 30;
    public static final double BASE_ENEMY_DMG = 10;
    public static final double BASE_ENEMY_DMG_RST = 0;

    public static final int FAST_ENEMY_TYPE = 1001;
    public static final int FAST_ENEMY_WIDTH = Constants.TILE_SIZE;
    public static final int FAST_ENEMY_HEIGHT = Constants.TILE_SIZE;
    public static final double FAST_ENEMY_SPD = 12;
    public static final double FAST_ENEMY_MAX_HP = 20;
    public static final double FAST_ENEMY_DMG = 10;
    public static final double FAST_ENEMY_DMG_RST = 0;

    public static final int TANK_ENEMY_TYPE = 1002;
    public static final int TANK_ENEMY_WIDTH = Constants.TILE_SIZE;
    public static final int TANK_ENEMY_HEIGHT = Constants.TILE_SIZE;
    public static final double TANK_ENEMY_SPD = 6;
    public static final double TANK_ENEMY_MAX_HP = 50;
    public static final double TANK_ENEMY_DMG = 7;
    public static final double TANK_ENEMY_DMG_RST = 0.2;

    public static final int STATIC_ENEMY_TYPE = 1010;
    public static final int STATIC_ENEMY_WIDTH = Constants.TILE_SIZE;
    public static final int STATIC_ENEMY_HEIGHT = Constants.TILE_SIZE;
    public static final double STATIC_ENEMY_SPD = 0;
    public static final double STATIC_ENEMY_MAX_HP = 30;
    public static final double STATIC_ENEMY_DMG = 15;
    public static final double STATIC_ENEMY_DMG_RST = 0.3;

    // to easily extract data when assignign them to spawned enemies
    public static final Map<Integer, EntityStats> ENEMY_STATS = Map.of(
        BASE_ENEMY_TYPE, new EntityStats(BASE_ENEMY_WIDTH, BASE_ENEMY_HEIGHT, BASE_ENEMY_SPD, BASE_ENEMY_MAX_HP, BASE_ENEMY_DMG, BASE_ENEMY_DMG_RST),
        FAST_ENEMY_TYPE, new EntityStats(FAST_ENEMY_WIDTH, FAST_ENEMY_HEIGHT, FAST_ENEMY_SPD, FAST_ENEMY_MAX_HP, FAST_ENEMY_DMG, FAST_ENEMY_DMG_RST),
        TANK_ENEMY_TYPE, new EntityStats(TANK_ENEMY_WIDTH, TANK_ENEMY_HEIGHT, TANK_ENEMY_SPD, TANK_ENEMY_MAX_HP, TANK_ENEMY_DMG, TANK_ENEMY_DMG_RST),
        STATIC_ENEMY_TYPE, new EntityStats(STATIC_ENEMY_WIDTH, STATIC_ENEMY_HEIGHT, STATIC_ENEMY_SPD, STATIC_ENEMY_MAX_HP, STATIC_ENEMY_DMG, STATIC_ENEMY_DMG_RST)
    );
    
}
