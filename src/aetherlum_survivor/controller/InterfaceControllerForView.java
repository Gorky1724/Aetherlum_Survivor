package aetherlum_survivor.controller;

public interface InterfaceControllerForView {

    public void startApplication();

    //panels management
    public void openStartPanel();

    public void openSettingsPanel();

    public void openGamePanel();

    //updates and requests
    public void startGameLoop();
    
}
