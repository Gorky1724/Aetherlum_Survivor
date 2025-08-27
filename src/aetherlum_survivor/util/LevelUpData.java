package aetherlum_survivor.util;

import java.util.Map;

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

    //! LEVEL UP DATA
    public static final int NUM_OPTIONS = 3; // possible upgrades every level-up
    public static final int LVL_PRJ_UNLOCK_INTERVAL = 4; //every N levels is granted the unlocking of a new projectile

    //! LEVEL UP OPTIONS
    // FV = incresed with flat value || PV = increased with percentage value
    public static final int[] LVL_UP_RANGE = {0,5}; //included

    public static final int CODE_SPD_FV = 0;
    public static final double SPD_FV = 1;
    public static final String SPD_FV_DESCR = "SPEED: +" + SPD_FV;

    public static final int CODE_MAX_HP_FV = 1;
    public static final double MAX_HP_FV = 100;
    public static final String MAX_HP_FV_DESCR = "MAX HP: +" + MAX_HP_FV; 

    public static final int CODE_MAX_HP_PV = 2;
    public static final double MAX_HP_PV = 1.1;
    public static final String MAX_HP_PV_DESCR = "MAX HP: +" + (int)((MAX_HP_PV - 1)*100) + "%";

    public static final int CODE_DMG_FV = 3;
    public static final double DMG_FV = 1;
    public static final String DMG_FV_DESCR = "DAMAGE: +" + DMG_FV; 

    public static final int CODE_DMG_RST_FV = 4;
    public static final double DMG_RST_FV = 0.05;
    public static final String DMG_RST_FV_DESCR = "DAMAGE RESISTANCE (%): +" + DMG_RST_FV;

    public static final int CODE_FIRE_RATE_PV = 5;
    public static final double FIRE_RATE_PV = 0.97;
    public static final String FIRE_RATE_PV_DESCR = "FIRE RATE: -" + (int)((1 - FIRE_RATE_PV)*100) + "%";

    //! LEVEL UP UNLOCK PROJECTILE OPTIONS
    public static final int[] PRJ_UNLOCK_RANGE = {101,102}; //included

    public static final int CODE_UNLOCK_FAST_PROJ = 101;
    public static final int FST_PROJ_TYPE = EntityData.FAST_PROJ_TYPE;
    public static final String UNLOCK_FST_PROJ_DESCR = "UNLOCKS FAST SPELL";

    public static final int CODE_UNLOCK_PIERCING_PROJ = 102;
    public static final int PRCNG_PROJ_TYPE = EntityData.PIERCING_PROJ_TYPE;
    public static final String UNLOCK_PRCNG_PROJ_DESCR = "UNLOCKS PIERCING SPELL";

    //! STATS OF ALL LEVELUP POSSIBILITIES mapped with the type as the key
    //available every levels
    public static final Map<Integer, LevelUpOptions> LEVEL_UP_OPTIONS = Map.of( //immutable map
        CODE_SPD_FV, new LevelUpOptions(SPD_FV_DESCR, SPD_FV, NULL_VALUE),
        CODE_MAX_HP_FV, new LevelUpOptions(MAX_HP_FV_DESCR, MAX_HP_FV, NULL_VALUE),
        CODE_MAX_HP_PV, new LevelUpOptions(MAX_HP_PV_DESCR, NULL_VALUE, MAX_HP_PV),
        CODE_DMG_FV, new LevelUpOptions(DMG_FV_DESCR, DMG_FV, NULL_VALUE),
        CODE_DMG_RST_FV, new LevelUpOptions(DMG_RST_FV_DESCR, DMG_RST_FV, NULL_VALUE),
        CODE_FIRE_RATE_PV, new LevelUpOptions(FIRE_RATE_PV_DESCR, NULL_VALUE, FIRE_RATE_PV)
    );

    public static final Map<Integer, LevelUpOptions> UNLOCK_PROJ_OPTIONS = Map.of( //immutable map
        //prj unlock
        CODE_UNLOCK_FAST_PROJ, new LevelUpOptions(UNLOCK_FST_PROJ_DESCR, FST_PROJ_TYPE),
        CODE_UNLOCK_PIERCING_PROJ, new LevelUpOptions(UNLOCK_PRCNG_PROJ_DESCR, PRCNG_PROJ_TYPE)
    );
}
