package aetherlum_survivor.view;

import java.util.Map;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public interface InterfaceView {

    //panel handling
    public void openGameFrame();

    public void openStartPanel();

    public void openSettingsPanel();

    public void openScenarioPanel();
    
    public void openGamePanel();

    public void openGameOverPanel();

    public void openPausePanel();

    public void openLevelUpPanel(Map<Integer, LevelUpOptions> randomLvlUp);

    //update view
    public void update();

    //keylistener for game panel
    public void attachKeyListenerToGamePanel();
    
}
