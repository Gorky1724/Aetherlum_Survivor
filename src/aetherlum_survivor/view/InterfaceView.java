package aetherlum_survivor.view;

import java.util.Map;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public interface InterfaceView {

    //audio handling
    public void preloadClips();

    public void setAudioStatus(boolean audioStatus);

    public boolean getAudioStatus();

    //panel handling
    public void openGameFrame();

    public void openStartPanel();

    public void openSettingsPanel(String openedFrom);

    public void openScenarioPanel();
    
    public void openGamePanel();

    public void openGameOverPanel();

    public void openPausePanel();

    public void openLevelUpPanel(Map<Integer, LevelUpOptions> randomLvlUp);

    //update view
    public void update();

    public void updateDeathAnimationList(EntityLogicalData eld);

    public void transmitScenarioToGamePanel(int scenario_selected_num);

    //keylistener for game panel
    public void attachKeyListenerToGamePanel();
    
}
