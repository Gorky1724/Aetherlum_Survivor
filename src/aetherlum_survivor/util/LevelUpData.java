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

    // FV = incresed with flat value || PV = increased with percentage value
    public static final int CODE_SPD_FV = 10;
    public static final double SPD_FV = 1;
    public static final String SPD_FV_DESCR = "SPEED: +" + SPD_FV;

    public static final int CODE_MAX_HP_FV = 20;
    public static final double MAX_HP_FV = 100;
    public static final String MAX_HP_FV_DESCR = "MAX HP: +" + MAX_HP_FV; 

    public static final int CODE_MAX_HP_PV = 25;
    public static final double MAX_HP_PV = 1.1;
    public static final String MAX_HP_PV_DESCR = "MAX HP: +" + (MAX_HP_PV - 1)*100 + "%";

    public static final int CODE_DMG_FV = 50;
    public static final double DMG_FV = 1;
    public static final String DMG_FV_DESCR = "DAMAGE: +" + DMG_FV; 

    public static final int CODE_DMG_RST_FV = 55;
    public static final double DMG_RST_FV = 0.05;
    public static final String DMG_RST_FV_DESCR = "DAMAGE RESISTANCE (%): +" + DMG_RST_FV;

    public static final int CODE_FIRE_RATE_PV = 70;
    public static final double FIRE_RATE_PV = 0.97;
    public static final String FIRE_RATE_PV_DESCR = "FIRE RATE: -" + (1 - FIRE_RATE_PV) + "%";

    public static final int CODE_UNLOCK_PIERCING_PROJ = 100;
    public static final int PRCNG_PROJ_TYPE = EntityData.PIERCING_PROJ_TYPE;
    public static final String UNLOCK_PRCNG_PROJ_DESCR = "UNLOCKS PIERCING SPELL";

    public static final int CODE_UNLOCK_FAST_PROJ = 110;
    public static final int FST_PROJ_TYPE = EntityData.FAST_PROJ_TYPE;
    public static final String UNLOCK_FST_PROJ_DESCR = "UNLOCKS FAST SPELL";

    //! STATS OF ALL LEVELUP POSSIBILITIES mapped with the type as the key
    public static final Map<Integer, LevelUpOptions> STATS = Map.of(
        CODE_SPD_FV, new LevelUpOptions(SPD_FV_DESCR, SPD_FV, NULL_VALUE),
        CODE_MAX_HP_FV, new LevelUpOptions(MAX_HP_FV_DESCR, MAX_HP_FV, NULL_VALUE),
        CODE_MAX_HP_PV, new LevelUpOptions(MAX_HP_PV_DESCR, NULL_VALUE, MAX_HP_PV),
        CODE_DMG_FV, new LevelUpOptions(DMG_FV_DESCR, DMG_FV, NULL_VALUE),
        CODE_DMG_RST_FV, new LevelUpOptions(DMG_RST_FV_DESCR, DMG_RST_FV, NULL_VALUE),
        CODE_FIRE_RATE_PV, new LevelUpOptions(FIRE_RATE_PV_DESCR, FIRE_RATE_PV, NULL_VALUE),
        CODE_UNLOCK_PIERCING_PROJ, new LevelUpOptions(UNLOCK_PRCNG_PROJ_DESCR, PRCNG_PROJ_TYPE),
        CODE_UNLOCK_FAST_PROJ, new LevelUpOptions(UNLOCK_FST_PROJ_DESCR, FST_PROJ_TYPE)
    );
}
