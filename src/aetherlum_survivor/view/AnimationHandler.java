package aetherlum_survivor.view;

import java.awt.Image;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.AnimationData.AnimationStats;
import aetherlum_survivor.util.AnimationData;
import aetherlum_survivor.util.EntityLogicalData;

public class AnimationHandler {

    //! UTILITY METHODS FOR VIEW
    public static AnimationStats getAnimationStatsBasedOnTypeAndCondition(int type, int condition) {
        return AnimationData.ANIMATION_STATS.get(type).get(condition);
    }

    public static int getCurrentFrameNum(EntityLogicalData eld) {
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
        long timeInCycle = elapsed % totalAnimationTime;
        int currentFrame = (int) (timeInCycle / frameDuration);
        return currentFrame;
    }

    public static Image[] getAnimationFrames(int type, int condition) {
        AnimationStats stats = getAnimationStatsBasedOnTypeAndCondition(type, condition);
        return stats.animationFrames;
    }

    //unic method that directly return the frame to paint
    public static Image getFrameToDraw(EntityLogicalData eld) {
        Image[] animationFrames = getAnimationFrames(eld.getType(), eld.getCondition());
        int currentFrame = getCurrentFrameNum(eld);
        return animationFrames[currentFrame];
    }
    
}
