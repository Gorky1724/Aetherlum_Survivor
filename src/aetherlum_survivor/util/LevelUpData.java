package aetherlum_survivor.util;

import java.util.Map;

import aetherlum_survivor.util.EntityData.EntityStats;

public class LevelUpData {

    //! GENERIC VALUE
    public final static int NULL_VALUE = -999;

    //! INNER CLASS structur for having compact data
    public static class LevelUpOptions {
        
        public final double flatValue;
        public final double percentageValue;

        public final String description;

        public final int newProjType;

        public LevelUpOptions(String descr, double flatValue, double percentageValue) { 
            this.description = descr;
            //one of the two may be NULL_VALUE when assigned
            this.flatValue = flatValue;
            this.percentageValue = percentageValue;

            this.newProjType = NULL_VALUE;
        }

        public LevelUpOptions(String descr, int newProjType) {
            this.description = descr;
            this.newProjType = newProjType;

            this.flatValue = NULL_VALUE;
            this.percentageValue = NULL_VALUE;
        }
    }

    //id_code come key

    /* 
        this.speed = EntityData.PLAYER_SPD;
        this.maxHitPoints = EntityData.PLAYER_MAX_HP;
        this.currentHP = this.maxHitPoints;
        this.damage = EntityData.PLAYER_DMG;
        this.damageResistance = EntityData.PLAYER_DMG_RST;
        this.fireRate = EntityData.PLAYER_FIRE_RATE;
        EntityData.BASE_PROJ_TYPE
    */

    /* 
    //! STATS OF ALL LEVELUP POSSIBILITIES mapped with the type as the key
    public static final Map<Integer, LevelUpData> STATS = Map.of(
        BASE_PROJ_TYPE, new EntityStats(BASE_PROJ_WIDTH, BASE_PROJ_HEIGHT, BASE_PROJ_SPD, BASE_PROJ_MAX_HP, NOT_USEFUL_VALUE, NOT_USEFUL_VALUE, BASE_PROJ_SPRITE_PATH, BASE_PROJ_RATE_MOD, BASE_PROJ_DMG_MOD),
    );
    */
    
}
