package aetherlum_survivor.view;

import java.awt.Graphics2D;
import java.awt.Image;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.AnimationData.AnimationStats;
import aetherlum_survivor.util.AnimationData;
import aetherlum_survivor.util.EntityLogicalData;

public class AnimationHandler {

    //! helper for Animation Handler and view
    public static AnimationStats getAnimationStatsBasedOnTypeAndCondition(int type, int condition) {
        return AnimationData.ANIMATION_STATS.get(type).get(condition);
    }

    private static int getCurrentFrameNum(EntityLogicalData eld) {
        long currentClockCycle = Controller.getInstance().getClockCycle();
        long startingClockOfCondition = eld.getStartingClockOfCondition();

        long elapsed = currentClockCycle - startingClockOfCondition;

        if (elapsed < 0) {
            return 0;
        }

        AnimationStats stats = getAnimationStatsBasedOnTypeAndCondition(eld.getType(), eld.getCondition());

        int frameDuration = stats.frameDuration;
        int animationNumFrames = stats.animationNumFrames;
        long totalAnimationTime = (long) (animationNumFrames * frameDuration);

        long timeInCycle = elapsed % totalAnimationTime; //the rest is the cycle clock n° of current animation cicle
        int currentFrame = (int) (timeInCycle / frameDuration); //number of frames of the animation already passed

        return currentFrame;
    }

    private static Image[] getAnimationFrames(int type, int condition) {
        AnimationStats stats = getAnimationStatsBasedOnTypeAndCondition(type, condition);
        return stats.animationFrames;
    }

    //! unic method that directly return the frame to paint
    public static Image getFrameToDraw(EntityLogicalData eld) {
        //System.out.println("type: "+eld.getType()+"\n cond: "+eld.getCondition());
        Image[] animationFrames = getAnimationFrames(eld.getType(), eld.getCondition());
        int currentFrame = getCurrentFrameNum(eld);
        return animationFrames[currentFrame];
    }

    //! draws with option to flip
    public static void drawSprite(Graphics2D g2d, Image ftd, boolean flipped, int x, int y, int width, int height) {
        
        if(flipped) { //draws flipped
            g2d.drawImage(ftd, x + width, y, -width, height, null);
        } else {
            g2d.drawImage(ftd,x,y,width,height,null);
        }
    }    
}
