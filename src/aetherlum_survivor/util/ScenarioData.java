package aetherlum_survivor.util;

public class ScenarioData {
    
    // SCENARIO 1 -------------------------------------------------------------------------------------
    public static final int[] SCENARIO_1st_ENEMIES = {EntityData.BASE_ENEMY_TYPE};
    public static final int SCENARIO_1st_MAX_NUM = 10;

    // SCENARIO 2 -------------------------------------------------------------------------------------
    public static final int[] SCENARIO_2nd_ENEMIES = {EntityData.BASE_ENEMY_TYPE,
                                                        EntityData.STATIC_ENEMY_TYPE
    };
    public static final int SCENARIO_2nd_MAX_NUM = 30;

    // SCENARIO 2 -------------------------------------------------------------------------------------
    public static final int[] SCENARIO_3rd_ENEMIES = {EntityData.BASE_ENEMY_TYPE,
                                                        EntityData.STATIC_ENEMY_TYPE,
                                                        EntityData.FAST_ENEMY_TYPE,
                                                        EntityData.TANK_ENEMY_TYPE
    };
    public static final int SCENARIO_3rd_MAX_NUM = 50;
}
