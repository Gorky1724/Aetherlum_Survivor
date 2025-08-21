package aetherlum_survivor.controller;

public interface InterfaceControllerForModel {

    // handle GameLoop
    public void startGameLoop();

    public void stopGameLoop();

    public void pauseGameLoop();

    public void resumeGameLoop();

    // updates and requests
    public void requestViewUpdate();
}
