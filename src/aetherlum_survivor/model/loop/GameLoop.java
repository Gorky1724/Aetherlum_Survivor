package aetherlum_survivor.model.loop;

import javax.swing.Timer;

import java.awt.event.ActionListener; //fo the logic of update

import aetherlum_survivor.util.Constants;

public class GameLoop {

    private Timer timer;
    private boolean running = false;

    private int intervalMs = Constants.TIMER_REPEAT_DELAY;

    public GameLoop(ActionListener update) {
        timer = new Timer(intervalMs, update);
        timer.setRepeats(true); // Ensures it repeats
    }

    public void start() {
        if (!running) {
            timer.start();
            running = true;
        }
    }

    public void pause() {
        if (running) {
            timer.stop();
            running = false;
        }
    }

    public void resume() {
        pause();
        start();
    }

    public boolean isRunning() {
        return running;
    }

}
