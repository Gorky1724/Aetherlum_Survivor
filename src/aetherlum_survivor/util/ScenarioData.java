package aetherlum_survivor.util;

public class ScenarioData {

    //framedata extracted using: http://www.spritecow.com/
    //tiles painted using: https://www.spritefusion.com/

    //specific scenario info
    public static int SCEN_1_X = 0;
    public static int SCEN_1_Y = 0;
    public static int SCEN_1_TILE_WIDTH = 480;
    public static int SCEN_1_TILE_HEIGHT = 560;

    public static int SCEN_2_X = 0;
    public static int SCEN_2_Y = 0;
    public static int SCEN_2_TILE_WIDTH = 656;
    public static int SCEN_2_TILE_HEIGHT = 432;

    public static int SCEN_3_X = 0;
    public static int SCEN_3_Y = 0;
    public static int SCEN_3_TILE_WIDTH = 560;
    public static int SCEN_3_TILE_HEIGHT = 672;

    //
    public static int[] SCENARIO_ENEMIES;
    public static  int SCENARIO_MAX_NUM;

    public ScenarioData(int selected_scenario_num) {

        switch (selected_scenario_num) {
        case 1: {
            // SCENARIO 1
            SCENARIO_ENEMIES = new int[] {EntityData.BASE_ENEMY_TYPE};
            SCENARIO_MAX_NUM = 10;
            break;
            }
        case 2:{
            // SCENARIO 2
            SCENARIO_ENEMIES = new int[] {
                EntityData.BASE_ENEMY_TYPE,
                EntityData.FAST_ENEMY_TYPE
            };
            SCENARIO_MAX_NUM = 25;
            break;
            }
        case 3: {
            // SCENARIO 3
            SCENARIO_ENEMIES = new int[] {
                EntityData.BASE_ENEMY_TYPE,
                EntityData.STATIC_ENEMY_TYPE,
                EntityData.FAST_ENEMY_TYPE,
                EntityData.TANK_ENEMY_TYPE
            };
            SCENARIO_MAX_NUM = 40;
            break;
            }
        default: {
            System.out.println("!!!>> WRONG SCENARIO NUMBER - This should never print out");
            break;
            }
        }
    }

    public int[] getEnemiesTypesAvaliable(){
        return SCENARIO_ENEMIES;
    }

    public int getEnemiesMaxNum(){
        return SCENARIO_MAX_NUM;
    }
}
