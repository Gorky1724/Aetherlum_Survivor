package aetherlum_survivor.util;

import java.awt.image.BufferedImage;
import java.util.Map;

import aetherlum_survivor.util.EntityData.EntityStats;
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

    public static final BufferedImage player_dying_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PLAYER_WALKING);
    public static final int PL_DYING_NUM_FRAMES = 6; //num frames
    public static final int PL_DYING_FRAME_DURTN = 8; //clock cycles
    public static Image[] pl_DYING_ANIMATION_FRAMES; // {xUpperLeftCorner, yUpperLeftCorner, width, height}
        static {//initialized animationframes
        pl_DYING_ANIMATION_FRAMES = new Image[PL_DYING_NUM_FRAMES];
        pl_DYING_ANIMATION_FRAMES[0] = player_dying_sprite.getSubimage(28,71,36,57);
        pl_DYING_ANIMATION_FRAMES[1] = player_dying_sprite.getSubimage(158,72,34,56);
        pl_DYING_ANIMATION_FRAMES[2] = player_dying_sprite.getSubimage(285,74,35,54);
        pl_DYING_ANIMATION_FRAMES[3] = player_dying_sprite.getSubimage(415,84,38,44);
        pl_DYING_ANIMATION_FRAMES[3] = player_dying_sprite.getSubimage(543,107,54,21);
        pl_DYING_ANIMATION_FRAMES[3] = player_dying_sprite.getSubimage(671,117,62,11);
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

    public static final BufferedImage exp_globe_sprite = ResourceHandler.loadImage(ResourcePaths.Images.EXP_GLOBE);
    public static Image[] xp_GLOBE_ANIMATION_FRAMES;
        static {
            xp_GLOBE_ANIMATION_FRAMES = new Image[CLT_NUM_FRAMES];
            xp_GLOBE_ANIMATION_FRAMES[0] = exp_globe_sprite.getSubimage(9, 1, 16, 30);
    }


    //! PROJECTILES -------------------------------------------------------------------------------------
    public static final int PROJECTILES_FRAME_DURTN = 5; //clock cycles

    //! ALL DATA MAPPED BY TYPE AND THEN BY CONDITION
    public static final Map<Integer, Map<Integer, AnimationStats>> ANIMATION_STATS = Map.of( //creates immutable map
        EntityData.PLAYER_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(PL_IDLE_NUM_FRAMES, PL_IDLE_FRAME_DURTN, pl_IDLE_ANIMATION_FRAMES),
            EntityData.WALKING, new AnimationStats(PL_WALKING_NUM_FRAMES, PL_WALKING_FRAME_DURTN, pl_WALKING_ANIMATION_FRAMES)
            //EntityData.DYING, new AnimationStats(PL_DYING_NUM_FRAMES, PL_DYING_FRAME_DURTN, pl_DYING_ANIMATION_FRAMES)
        ),
        //collectibles (?)
        EntityData.HP_GLOBE_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(CLT_NUM_FRAMES, CLT_FRAME_DURTN, hp_GLOBE_ANIMATION_FRAMES)
        ),
        EntityData.XP_GLOBE_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(CLT_NUM_FRAMES, CLT_FRAME_DURTN, xp_GLOBE_ANIMATION_FRAMES)
        ),

        //projectiles

        //enemies
        EntityData.BASE_ENEMY_TYPE, Map.of(

        )
    );

}
