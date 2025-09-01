package aetherlum_survivor.util;

import java.awt.image.BufferedImage;
import java.util.Map;

import aetherlum_survivor.view.ResourceHandler;

import java.awt.Image;

public class AnimationData {

    //framedata extracted using: http://www.spritecow.com/
    
    public static class AnimationStats { //inner class

        public final int animationNumFrames;
        public final int frameDuration;

        public final Image[] animationFrames;

        public AnimationStats(int animationNumFrames, int frameDuration, Image[] animationFrames) {
            this.animationNumFrames = animationNumFrames;
            this.frameDuration = frameDuration;
            this.animationFrames = animationFrames;
        }

    }

    //! GENERAL
    public static final int DEATH_ANIMATION_DEFAULT_DURATION = 36; //numframes*frameduration - common number to all animations

    //! PLAYER -------------------------------------------------------------------------------------
    public static final BufferedImage player_idle_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PLAYER_IDLE);
    public static final int PL_IDLE_NUM_FRAMES = 7; //num frames
    public static final int PL_IDLE_FRAME_DURTN = 5; //clock cycles
    public static Image[] pl_IDLE_ANIMATION_FRAMES; // {xUpperLeftCorner, yUpperLeftCorner, width, height}
        static {//initialized animationframes
            pl_IDLE_ANIMATION_FRAMES = new Image[PL_IDLE_NUM_FRAMES];
            pl_IDLE_ANIMATION_FRAMES[0] = player_idle_sprite.getSubimage(36,62,28,66);
            pl_IDLE_ANIMATION_FRAMES[1] = player_idle_sprite.getSubimage(164,62,28,66);
            pl_IDLE_ANIMATION_FRAMES[2] = player_idle_sprite.getSubimage(289,62,31,66);
            pl_IDLE_ANIMATION_FRAMES[3] = player_idle_sprite.getSubimage(417,62,33,66);
            pl_IDLE_ANIMATION_FRAMES[4] = player_idle_sprite.getSubimage(545,62,31,66);
            pl_IDLE_ANIMATION_FRAMES[5] = player_idle_sprite.getSubimage(673,62,31,66);
            pl_IDLE_ANIMATION_FRAMES[6] = player_idle_sprite.getSubimage(804,62,28,66);
    }

    public static final BufferedImage player_walking_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PLAYER_WALKING);
    public static final int PL_WALKING_NUM_FRAMES = 8; //num frames
    public static final int PL_WALKING_FRAME_DURTN = 5; //clock cycles
    public static Image[] pl_WALKING_ANIMATION_FRAMES; // {xUpperLeftCorner, yUpperLeftCorner, width, height}
        static {//initialized animationframes
            pl_WALKING_ANIMATION_FRAMES = new Image[PL_WALKING_NUM_FRAMES];
            pl_WALKING_ANIMATION_FRAMES[0] = player_walking_sprite.getSubimage(31,69,36,59);
            pl_WALKING_ANIMATION_FRAMES[1] = player_walking_sprite.getSubimage(159,70,36,58);
            pl_WALKING_ANIMATION_FRAMES[2] = player_walking_sprite.getSubimage(288,67,44,61);
            pl_WALKING_ANIMATION_FRAMES[3] = player_walking_sprite.getSubimage(417,64,34,64);
            pl_WALKING_ANIMATION_FRAMES[4] = player_walking_sprite.getSubimage(543,69,36,59);
            pl_WALKING_ANIMATION_FRAMES[5] = player_walking_sprite.getSubimage(669,70,38,58);
            pl_WALKING_ANIMATION_FRAMES[6] = player_walking_sprite.getSubimage(797,69,47,59);
            pl_WALKING_ANIMATION_FRAMES[7] = player_walking_sprite.getSubimage(927,68,36,60);
    }

    public static final BufferedImage player_dying_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PLAYER_DYING);
    public static final int PL_DYING_NUM_FRAMES = 6; //num frames
    public static final int PL_DYING_FRAME_DURTN = 7; //clock cycles
    public static Image[] pl_DYING_ANIMATION_FRAMES; // {xUpperLeftCorner, yUpperLeftCorner, width, height}
        static {//initialized animationframes
            pl_DYING_ANIMATION_FRAMES = new Image[PL_DYING_NUM_FRAMES];
            pl_DYING_ANIMATION_FRAMES[0] = player_dying_sprite.getSubimage(28,71,36,57);
            pl_DYING_ANIMATION_FRAMES[1] = player_dying_sprite.getSubimage(158,72,34,56);
            pl_DYING_ANIMATION_FRAMES[2] = player_dying_sprite.getSubimage(285,74,35,54);
            pl_DYING_ANIMATION_FRAMES[3] = player_dying_sprite.getSubimage(415,84,38,44);
            pl_DYING_ANIMATION_FRAMES[4] = player_dying_sprite.getSubimage(543,107,54,21);
            pl_DYING_ANIMATION_FRAMES[5] = player_dying_sprite.getSubimage(671,117,62,11);
    }

    //! COLLECTIBLES -------------------------------------------------------------------------------------
    public static final int CLT_NUM_FRAMES = 1; //num frames
    public static final int CLT_FRAME_DURTN = 1;

    public static final BufferedImage health_globe_sprite = ResourceHandler.loadImage(ResourcePaths.Images.HEALTH_GLOBE);
    public static Image[] hp_GLOBE_ANIMATION_FRAMES;
        static {
            hp_GLOBE_ANIMATION_FRAMES = new Image[CLT_NUM_FRAMES];
            hp_GLOBE_ANIMATION_FRAMES[0] = health_globe_sprite.getSubimage(8, 2, 16, 28);
    }
    public static final BufferedImage health_glb_taken_sprite = ResourceHandler.loadImage(ResourcePaths.Images.HEALTH_TAKEN);
    public static final int HEALTH_TAKEN_NUM_FRAMES = 6; 
    public static final int HEALTH_TAKEN_FRAME_DURTN = 5; 
    public static Image[] health_TAKEN_ANIMATION_FRAMES; //death animation 
        static {
            health_TAKEN_ANIMATION_FRAMES = new Image[HEALTH_TAKEN_NUM_FRAMES];
            health_TAKEN_ANIMATION_FRAMES[0] = health_glb_taken_sprite.getSubimage(24,470,18,18);
            health_TAKEN_ANIMATION_FRAMES[1] = health_glb_taken_sprite.getSubimage(82,464,28,28);
            health_TAKEN_ANIMATION_FRAMES[2] = health_glb_taken_sprite.getSubimage(142,461,36,35);
            health_TAKEN_ANIMATION_FRAMES[3] = health_glb_taken_sprite.getSubimage(202,459,42,40);
            health_TAKEN_ANIMATION_FRAMES[4] = health_glb_taken_sprite.getSubimage(265,456,45,44);
            health_TAKEN_ANIMATION_FRAMES[5] = health_glb_taken_sprite.getSubimage(516,452,55,53);
    }

    public static final BufferedImage exp_globe_sprite = ResourceHandler.loadImage(ResourcePaths.Images.EXP_GLOBE);
    public static Image[] xp_GLOBE_ANIMATION_FRAMES;
        static {
            xp_GLOBE_ANIMATION_FRAMES = new Image[CLT_NUM_FRAMES];
            xp_GLOBE_ANIMATION_FRAMES[0] = exp_globe_sprite.getSubimage(9, 1, 16, 30);
    }
    public static final BufferedImage exp_glb_taken_sprite = ResourceHandler.loadImage(ResourcePaths.Images.EXP_TAKEN);
    public static final int EXP_TAKEN_NUM_FRAMES = 8; 
    public static final int EXP_TAKEN_FRAME_DURTN = 5; 
    public static Image[] exp_TAKEN_ANIMATION_FRAMES; //death animation 
        static {
            exp_TAKEN_ANIMATION_FRAMES = new Image[EXP_TAKEN_NUM_FRAMES];
            exp_TAKEN_ANIMATION_FRAMES[0] = exp_glb_taken_sprite.getSubimage(403,273,23,35);
            exp_TAKEN_ANIMATION_FRAMES[1] = exp_glb_taken_sprite.getSubimage(467,270,24,37);
            exp_TAKEN_ANIMATION_FRAMES[2] = exp_glb_taken_sprite.getSubimage(531,269,24,39);
            exp_TAKEN_ANIMATION_FRAMES[3] = exp_glb_taken_sprite.getSubimage(595,268,24,39);
            exp_TAKEN_ANIMATION_FRAMES[4] = exp_glb_taken_sprite.getSubimage(659,267,23,37);
            exp_TAKEN_ANIMATION_FRAMES[5] = exp_glb_taken_sprite.getSubimage(722,266,25,37);
            exp_TAKEN_ANIMATION_FRAMES[6] = exp_glb_taken_sprite.getSubimage(786,265,25,36);
            exp_TAKEN_ANIMATION_FRAMES[7] = exp_glb_taken_sprite.getSubimage(850,264,24,35);
    }


    //! PROJECTILES -------------------------------------------------------------------------------------
    public static final int PROJECTILES_NUM_FRAMES = 5;
    public static final int PROJECTILES_FRAME_DURTN = 3;

    public static final BufferedImage base_proj_sprite = ResourceHandler.loadImage(ResourcePaths.Images.BASE_PROJECTILE);
    public static Image[] baseprj_WALKING_ANIMATION_FRAMES; 
        static {
            baseprj_WALKING_ANIMATION_FRAMES = new Image[PROJECTILES_NUM_FRAMES];
            baseprj_WALKING_ANIMATION_FRAMES[0] = base_proj_sprite.getSubimage(42,29,16,9);
            baseprj_WALKING_ANIMATION_FRAMES[1] = base_proj_sprite.getSubimage(104,27,18,12);
            baseprj_WALKING_ANIMATION_FRAMES[2] = base_proj_sprite.getSubimage(166,28,20,10);
            baseprj_WALKING_ANIMATION_FRAMES[3] = base_proj_sprite.getSubimage(229,27,21,12);
            baseprj_WALKING_ANIMATION_FRAMES[4] = base_proj_sprite.getSubimage(295,27,19,12);
    }
    public static final int BASEPROJ_DYING_NUM_FRAMES = 2;
    public static final int BASEPROJ_DYING_FRAME_DURTN = 7;
    public static Image[] baseprj_DYING_ANIMATION_FRAMES; //death animation 
        static {
            baseprj_DYING_ANIMATION_FRAMES = new Image[BASEPROJ_DYING_NUM_FRAMES];
            baseprj_DYING_ANIMATION_FRAMES[0] = base_proj_sprite.getSubimage(427,20,15,25);
            baseprj_DYING_ANIMATION_FRAMES[1] = base_proj_sprite.getSubimage(483,13,23,39);
    }

    public static final BufferedImage fast_proj_sprite = ResourceHandler.loadImage(ResourcePaths.Images.FAST_PROJECTILE);
    public static Image[] fastprj_WALKING_ANIMATION_FRAMES;
        static {
            fastprj_WALKING_ANIMATION_FRAMES = new Image[PROJECTILES_NUM_FRAMES];
            fastprj_WALKING_ANIMATION_FRAMES[0] = fast_proj_sprite.getSubimage(28,27,10,10);
            fastprj_WALKING_ANIMATION_FRAMES[1] = fast_proj_sprite.getSubimage(90,19,18,29);
            fastprj_WALKING_ANIMATION_FRAMES[2] = fast_proj_sprite.getSubimage(155,19,16,24);
            fastprj_WALKING_ANIMATION_FRAMES[3] = fast_proj_sprite.getSubimage(220,21,14,21);
            fastprj_WALKING_ANIMATION_FRAMES[4] = fast_proj_sprite.getSubimage(348,23,9,16);
    }
    public static final int FASTPROJ_DYING_NUM_FRAMES = 2;
    public static final int FASTPROJ_DYING_FRAME_DURTN = 7;
    public static Image[] fastprj_DYING_ANIMATION_FRAMES; 
        static {
            fastprj_DYING_ANIMATION_FRAMES = new Image[FASTPROJ_DYING_NUM_FRAMES];
            fastprj_DYING_ANIMATION_FRAMES[0] = fast_proj_sprite.getSubimage(535,19,18,25);
            fastprj_DYING_ANIMATION_FRAMES[1] = fast_proj_sprite.getSubimage(406,16,21,30);
    }

    public static final BufferedImage piercing_proj_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PIERCING_PROJECTILE);
    public static Image[] piercingprj_WALKING_ANIMATION_FRAMES;
        static {
            piercingprj_WALKING_ANIMATION_FRAMES = new Image[PROJECTILES_NUM_FRAMES];
            piercingprj_WALKING_ANIMATION_FRAMES[0] = piercing_proj_sprite.getSubimage(27,54,29,17);
            piercingprj_WALKING_ANIMATION_FRAMES[1] = piercing_proj_sprite.getSubimage(89,54,31,18);
            piercingprj_WALKING_ANIMATION_FRAMES[2] = piercing_proj_sprite.getSubimage(152,54,33,18);
            piercingprj_WALKING_ANIMATION_FRAMES[3] = piercing_proj_sprite.getSubimage(207,56,42,15);
            piercingprj_WALKING_ANIMATION_FRAMES[4] = piercing_proj_sprite.getSubimage(278,55,34,17);
    }
    public static final int PIERCINGPROJ_DYING_NUM_FRAMES = 2;
    public static final int PIERCINGPROJ_DYING_FRAME_DURTN = 7;
    public static Image[] piercingprj_DYING_ANIMATION_FRAMES; 
        static {
            piercingprj_DYING_ANIMATION_FRAMES = new Image[PIERCINGPROJ_DYING_NUM_FRAMES];
            piercingprj_DYING_ANIMATION_FRAMES[0] = piercing_proj_sprite.getSubimage(351,47,25,34);
            piercingprj_DYING_ANIMATION_FRAMES[1] = piercing_proj_sprite.getSubimage(407,44,22,51);
    }

    //! ENEMIES -------------------------------------------------------------------------------------
    //NOTE: static enemies being idle are consideren in walking status for more semplicity
    public static final BufferedImage base_enemy_sprite = ResourceHandler.loadImage(ResourcePaths.Images.BASE_ENEMY);
    public static final int BASEEN_WALKING_NUM_FRAMES = 4; 
    public static final int BASEEN_WALKING_FRAME_DURTN = 10; 
    public static Image[] baseen_WALKING_ANIMATION_FRAMES; 
        static {
            baseen_WALKING_ANIMATION_FRAMES = new Image[BASEEN_WALKING_NUM_FRAMES];
            baseen_WALKING_ANIMATION_FRAMES[0] = base_enemy_sprite.getSubimage(24,26,49,38);
            baseen_WALKING_ANIMATION_FRAMES[1] = base_enemy_sprite.getSubimage(127,23,37,41);
            baseen_WALKING_ANIMATION_FRAMES[2] = base_enemy_sprite.getSubimage(220,25,46,39);
            baseen_WALKING_ANIMATION_FRAMES[3] = base_enemy_sprite.getSubimage(319,23,38,41);
    }
    public static final int BASEEN_DYING_NUM_FRAMES = 4; 
    public static final int BASEEN_DYING_FRAME_DURTN = 6;
    public static Image[] baseen_DYING_ANIMATION_FRAMES;
        static {
            baseen_DYING_ANIMATION_FRAMES = new Image[BASEEN_DYING_NUM_FRAMES];
            baseen_DYING_ANIMATION_FRAMES[0] = base_enemy_sprite.getSubimage(399,467,57,45);
            baseen_DYING_ANIMATION_FRAMES[1] = base_enemy_sprite.getSubimage(491,476,60,36);
            baseen_DYING_ANIMATION_FRAMES[2] = base_enemy_sprite.getSubimage(10,548,61,28);
            baseen_DYING_ANIMATION_FRAMES[3] = base_enemy_sprite.getSubimage(106,558,61,18);
            //baseen_DYING_ANIMATION_FRAMES[4] = base_enemy_sprite.getSubimage(199,566,65,10);
            //baseen_DYING_ANIMATION_FRAMES[5] = base_enemy_sprite.getSubimage(288,571,73,5);

    }

    public static final BufferedImage fast_enemy_sprite = ResourceHandler.loadImage(ResourcePaths.Images.FAST_ENEMY);
    public static final int FASTEN_WALKING_NUM_FRAMES = 4;
    public static final int FASTEN_WALKING_FRAME_DURTN = 5;
    public static Image[] fasten_WALKING_ANIMATION_FRAMES;
        static {
            fasten_WALKING_ANIMATION_FRAMES = new Image[FASTEN_WALKING_NUM_FRAMES];
            fasten_WALKING_ANIMATION_FRAMES[0] = fast_enemy_sprite.getSubimage(50,10,42,51);
            fasten_WALKING_ANIMATION_FRAMES[1] = fast_enemy_sprite.getSubimage(179,8,47,52);
            fasten_WALKING_ANIMATION_FRAMES[2] = fast_enemy_sprite.getSubimage(308,18,48,41);
            fasten_WALKING_ANIMATION_FRAMES[3] = fast_enemy_sprite.getSubimage(436,7,48,56);
    }
    public static final int FASTEN_DYING_NUM_FRAMES = 6;
    public static final int FASTEN_DYING_FRAME_DURTN = 4;
    public static Image[] fasten_DYING_ANIMATION_FRAMES;
        static {
            fasten_DYING_ANIMATION_FRAMES = new Image[FASTEN_DYING_NUM_FRAMES];
            fasten_DYING_ANIMATION_FRAMES[0] = fast_enemy_sprite.getSubimage(54,129,32,43);
            fasten_DYING_ANIMATION_FRAMES[1] = fast_enemy_sprite.getSubimage(184,135,24,37);
            fasten_DYING_ANIMATION_FRAMES[2] = fast_enemy_sprite.getSubimage(312,133,28,38);
            fasten_DYING_ANIMATION_FRAMES[3] = fast_enemy_sprite.getSubimage(440,136,27,34);
            fasten_DYING_ANIMATION_FRAMES[4] = fast_enemy_sprite.getSubimage(57,196,23,37);
            fasten_DYING_ANIMATION_FRAMES[5] = fast_enemy_sprite.getSubimage(183,209,24,25);
            //fasten_DYING_ANIMATION_FRAMES[6] = fast_enemy_sprite.getSubimage(442,218,21,16);
            //fasten_DYING_ANIMATION_FRAMES[7] = fast_enemy_sprite.getSubimage(310,314,21,6);
    }

    public static final BufferedImage static_enemy_sprite = ResourceHandler.loadImage(ResourcePaths.Images.STATIC_ENEMY);
    public static final int STATICEN_WALKING_NUM_FRAMES = 6;
    public static final int STATICEN_WALKING_FRAME_DURTN = 7;
    public static Image[] staticen_WALKING_ANIMATION_FRAMES;
        static {
            staticen_WALKING_ANIMATION_FRAMES = new Image[STATICEN_WALKING_NUM_FRAMES];
            staticen_WALKING_ANIMATION_FRAMES[0] = static_enemy_sprite.getSubimage(21,5,24,27);
            staticen_WALKING_ANIMATION_FRAMES[1] = static_enemy_sprite.getSubimage(87,6,22,26);
            staticen_WALKING_ANIMATION_FRAMES[2] = static_enemy_sprite.getSubimage(152,7,24,25);
            staticen_WALKING_ANIMATION_FRAMES[3] = static_enemy_sprite.getSubimage(215,7,24,25);
            staticen_WALKING_ANIMATION_FRAMES[4] = static_enemy_sprite.getSubimage(23,39,24,25);
            staticen_WALKING_ANIMATION_FRAMES[5] = static_enemy_sprite.getSubimage(85,37,24,27);
    }
    public static final int STATICEN_DYING_NUM_FRAMES = 7;
    public static final int STATICEN_DYING_FRAME_DURTN = 5;
    public static Image[] staticen_DYING_ANIMATION_FRAMES;
        static {
            staticen_DYING_ANIMATION_FRAMES = new Image[STATICEN_DYING_NUM_FRAMES];
            staticen_DYING_ANIMATION_FRAMES[0] = static_enemy_sprite.getSubimage(83,133,29,27);
            staticen_DYING_ANIMATION_FRAMES[1] = static_enemy_sprite.getSubimage(144,135,34,25);
            staticen_DYING_ANIMATION_FRAMES[2] = static_enemy_sprite.getSubimage(204,136,44,24);
            staticen_DYING_ANIMATION_FRAMES[3] = static_enemy_sprite.getSubimage(7,170,53,22);
            staticen_DYING_ANIMATION_FRAMES[4] = static_enemy_sprite.getSubimage(68,176,60,16);
            staticen_DYING_ANIMATION_FRAMES[5] = static_enemy_sprite.getSubimage(142,77,38,19);
            staticen_DYING_ANIMATION_FRAMES[6] = static_enemy_sprite.getSubimage(200,82,49,14);
    }

    public static final BufferedImage tank_enemy_sprite = ResourceHandler.loadImage(ResourcePaths.Images.TANK_ENEMY);
    public static final int TANKEN_WALKING_NUM_FRAMES = 7;
    public static final int TANKEN_WALKING_FRAME_DURTN = 5;
    public static Image[] tanken_WALKING_ANIMATION_FRAMES;
        static {
            tanken_WALKING_ANIMATION_FRAMES = new Image[TANKEN_WALKING_NUM_FRAMES];
            tanken_WALKING_ANIMATION_FRAMES[0] = tank_enemy_sprite.getSubimage(27,11,41,53);
            tanken_WALKING_ANIMATION_FRAMES[1] = tank_enemy_sprite.getSubimage(347,11,39,53);
            tanken_WALKING_ANIMATION_FRAMES[2] = tank_enemy_sprite.getSubimage(27,76,42,52);
            tanken_WALKING_ANIMATION_FRAMES[3] = tank_enemy_sprite.getSubimage(107,76,41,52);
            tanken_WALKING_ANIMATION_FRAMES[4] = tank_enemy_sprite.getSubimage(187,75,39,53);
            tanken_WALKING_ANIMATION_FRAMES[5] = tank_enemy_sprite.getSubimage(267,76,42,52);
            tanken_WALKING_ANIMATION_FRAMES[6] = tank_enemy_sprite.getSubimage(347,76,41,52);
    }
    public static final int TANKEN_DYING_NUM_FRAMES = 8;
    public static final int TANKEN_DYING_FRAME_DURTN = 5;
    public static Image[] tanken_DYING_ANIMATION_FRAMES;
        static {
            tanken_DYING_ANIMATION_FRAMES = new Image[TANKEN_DYING_NUM_FRAMES];
            tanken_DYING_ANIMATION_FRAMES[0] = tank_enemy_sprite.getSubimage(22,272,44,48);
            tanken_DYING_ANIMATION_FRAMES[1] = tank_enemy_sprite.getSubimage(96,275,46,45);
            tanken_DYING_ANIMATION_FRAMES[2] = tank_enemy_sprite.getSubimage(174,279,46,41);
            tanken_DYING_ANIMATION_FRAMES[3] = tank_enemy_sprite.getSubimage(253,285,48,35);
            tanken_DYING_ANIMATION_FRAMES[4] = tank_enemy_sprite.getSubimage(334,286,51,34);
            tanken_DYING_ANIMATION_FRAMES[5] = tank_enemy_sprite.getSubimage(12,354,52,30);
            tanken_DYING_ANIMATION_FRAMES[6] = tank_enemy_sprite.getSubimage(90,360,57,24);
            tanken_DYING_ANIMATION_FRAMES[7] = tank_enemy_sprite.getSubimage(170,364,54,20);
    }

    //! ALL DATA MAPPED BY TYPE AND THEN BY CONDITION
    public static final Map<Integer, Map<Integer, AnimationStats>> ANIMATION_STATS = Map.of( //creates immutable map
        EntityData.PLAYER_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(PL_IDLE_NUM_FRAMES, PL_IDLE_FRAME_DURTN, pl_IDLE_ANIMATION_FRAMES),
            EntityData.WALKING, new AnimationStats(PL_WALKING_NUM_FRAMES, PL_WALKING_FRAME_DURTN, pl_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(PL_DYING_NUM_FRAMES, PL_DYING_FRAME_DURTN, pl_DYING_ANIMATION_FRAMES)
        ),
        //collectibles
        EntityData.HP_GLOBE_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(CLT_NUM_FRAMES, CLT_FRAME_DURTN, hp_GLOBE_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(HEALTH_TAKEN_NUM_FRAMES, HEALTH_TAKEN_FRAME_DURTN, health_TAKEN_ANIMATION_FRAMES)
        ),
        EntityData.XP_GLOBE_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(CLT_NUM_FRAMES, CLT_FRAME_DURTN, xp_GLOBE_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(EXP_TAKEN_NUM_FRAMES, EXP_TAKEN_FRAME_DURTN, exp_TAKEN_ANIMATION_FRAMES)
        ),

        //projectiles
        EntityData.BASE_PROJ_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(PROJECTILES_NUM_FRAMES, PROJECTILES_FRAME_DURTN, baseprj_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(BASEPROJ_DYING_NUM_FRAMES, BASEPROJ_DYING_FRAME_DURTN, baseprj_DYING_ANIMATION_FRAMES)
        ),
        EntityData.FAST_PROJ_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(PROJECTILES_NUM_FRAMES, PROJECTILES_FRAME_DURTN, fastprj_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(FASTPROJ_DYING_NUM_FRAMES, FASTPROJ_DYING_FRAME_DURTN, fastprj_DYING_ANIMATION_FRAMES)
        ),
        EntityData.PIERCING_PROJ_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(PROJECTILES_NUM_FRAMES, PROJECTILES_FRAME_DURTN, piercingprj_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(PIERCINGPROJ_DYING_NUM_FRAMES, PIERCINGPROJ_DYING_FRAME_DURTN, piercingprj_DYING_ANIMATION_FRAMES)
        ),

        //enemies
        EntityData.BASE_ENEMY_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(BASEEN_WALKING_NUM_FRAMES, BASEEN_WALKING_FRAME_DURTN, baseen_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(BASEEN_DYING_NUM_FRAMES, BASEEN_DYING_FRAME_DURTN, baseen_DYING_ANIMATION_FRAMES)
        ),
        EntityData.FAST_ENEMY_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(FASTEN_WALKING_NUM_FRAMES, FASTEN_WALKING_FRAME_DURTN, fasten_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(FASTEN_DYING_NUM_FRAMES, FASTEN_DYING_FRAME_DURTN, fasten_DYING_ANIMATION_FRAMES)
        ),
        EntityData.STATIC_ENEMY_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(STATICEN_WALKING_NUM_FRAMES, STATICEN_WALKING_FRAME_DURTN, staticen_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(STATICEN_DYING_NUM_FRAMES, STATICEN_DYING_FRAME_DURTN, staticen_DYING_ANIMATION_FRAMES)
        ),
        EntityData.TANK_ENEMY_TYPE, Map.of(
            EntityData.WALKING, new AnimationStats(TANKEN_WALKING_NUM_FRAMES, TANKEN_WALKING_FRAME_DURTN, tanken_WALKING_ANIMATION_FRAMES),
            EntityData.DYING, new AnimationStats(TANKEN_DYING_NUM_FRAMES, TANKEN_DYING_FRAME_DURTN, tanken_DYING_ANIMATION_FRAMES)
        )
    );
}
