package aetherlum_survivor.controller;

public interface InterfaceControllerForView {

    public void startApplication();

    //panels management
    public void openStartPanel();

    public void closeStartPanel();

    public void openSettingsPanel();

    public void closeSettingsPanel();

    public void openGamePanel();

    public void closeGamePanel();

    public void requestStartGameLoop();

    //update
    public void requestViewUpdate();
    
}
