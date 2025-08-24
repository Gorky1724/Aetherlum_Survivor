package aetherlum_survivor.view;

public interface InterfaceView {

    //panel handling
    public void openGameFrame();

    public void openStartPanel();

    public void openSettingsPanel();

    public void openScenarioPanel();
    
    public void openGamePanel();

    public void openGameOverPanel();

    //update view
    public void update();

    //keylistener for game panel
    public void attachKeyListenerToGamePanel();
    
}
