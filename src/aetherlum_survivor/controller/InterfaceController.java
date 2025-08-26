package aetherlum_survivor.controller;

import java.util.List;

import aetherlum_survivor.util.EntityLogicalData;

public interface InterfaceController {
    
    public void startApplication();

    //panels management
    public void openStartPanel();

    public void openSettingsPanel();

    public void openScenarioPanel();

    public void openGamePanel();

    // handle GameLoop
    public void startGameLoop();

    public void stopGameLoop();

    public void resumeGameLoop();

    public void handleGameOver();

    public void handlePauseGame();

    public void handleLevelUp();

    public void handleResumeGame();

    // updates and requests
    public void requestViewUpdate();

    public void transmitScenarioToModel(int scenario_num);

    //exposes data
    public EntityLogicalData getPlayerELD();

    public List<EntityLogicalData> getEnemiesELD();

    public List<EntityLogicalData> getProjectilesELD();

    public List<EntityLogicalData> getCollectiblesELD();

    public int getTimePassed();

    public double[] getPlayerExpInfo();

}
