package aetherlum_survivor.view;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityLogicalData;

public class AnimationHandler {

    //! UTILITY METHODS FOR VIEW
    public static int getCurrentFrame(EntityLogicalData eld) {
        long currentClockCycle = Controller.getInstance().getClockCycle();
        long startingClockOfCondition = eld.getStartingClockOfCondition();

        long elapsed = currentClockCycle - startingClockOfCondition;

        if (elapsed < 0) {
            return 0;
        }

        AnimationData data = getAnimationDataBasedOnType(eld.getType());

        int frameDuration = data.getFrameDuration();
        int animationNumFrames = data.getAnimationNumFrames();
        long totalAnimationTime = (long) (animationNumFrames * frameDuration);
        long timeInCycle = elapsed % totalAnimationTime;
        int currentFrame = (int) (timeInCycle / frameDuration);
    }
    
}
