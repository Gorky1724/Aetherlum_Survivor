package aetherlum_survivor.controller;

public interface InterfaceController {
    
    public void startApplication();

    //panels management
    public void openStartPanel();

    public void openSettingsPanel();

    public void openGamePanel();

    // handle GameLoop
    public void startGameLoop();

    public void stopGameLoop();

    public void pauseGameLoop();

    public void resumeGameLoop();

    // updates and requests
    public void requestViewUpdate();

}
