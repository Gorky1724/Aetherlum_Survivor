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
    BufferedImage player_idle_sprite = ResourceHandler.loadImage(ResourcePaths.Images.PLAYER_IDLE);
    public static final int PL_IDLE_NUM_FRAMES = 7; //num frames
    public static final int PL_IDLE_FRAME_DURTN = 8; //clock cycles
    public static Image[] pl_IDLE_ANIMATION_FRAMES; // {xUpperLeftCorner, yUpperLeftCorner, width, height}
        {
        pl_IDLE_ANIMATION_FRAMES = new Image[PL_IDLE_NUM_FRAMES];
        pl_IDLE_ANIMATION_FRAMES[0] = player_idle_sprite.getSubimage(36,62,28,66);
        pl_IDLE_ANIMATION_FRAMES[1] = player_idle_sprite.getSubimage(164,62,28,66);
        pl_IDLE_ANIMATION_FRAMES[2] = player_idle_sprite.getSubimage(289,62,31,66);
        pl_IDLE_ANIMATION_FRAMES[3] = player_idle_sprite.getSubimage(417,62,33,66);
        pl_IDLE_ANIMATION_FRAMES[4] = player_idle_sprite.getSubimage(545,62,31,66);
        pl_IDLE_ANIMATION_FRAMES[5] = player_idle_sprite.getSubimage(673,62,31,66);
        pl_IDLE_ANIMATION_FRAMES[6] = player_idle_sprite.getSubimage(804,62,28,66);
    }


    //! PROJECTILES -------------------------------------------------------------------------------------
    public static final int PROJECTILES_FRAME_DURTN = 20; //clock cycles

    //! ALL DATA MAPPED BY TYPE AND THEN BY CONDITION
    public static final Map<Integer, Map<Integer, AnimationStats>> ANIMATION_STATS = Map.of( //creates immutable map
        EntityData.PLAYER_TYPE, Map.of(
            EntityData.IDLE, new AnimationStats(PL_IDLE_NUM_FRAMES, PL_IDLE_FRAME_DURTN, pl_IDLE_ANIMATION_FRAMES)
            //EntityData.WALKING, new AnimationStats(PL_WALKING_NUM_FRAMES, PL_WALKING_FRAME_DURTN, pl_WALKING_ANIMATION_FRAMES),
            //EntityData.DYING, new AnimationStats(PL_DYING_NUM_FRAMES, PL_DYING_FRAME_DURTN, pl_DYING_ANIMATION_FRAMES)
        ),
        //collectibles (?)

        //projectiles

        //enemies
        EntityData.BASE_ENEMY_TYPE, Map.of(

        )
    );

}
