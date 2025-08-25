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

    // updates and requests
    public void requestViewUpdate();

    public void transmitScenarioToModel(int scenario_num);

    //exposes entities
    public EntityLogicalData getPlayerELD();

    public List<EntityLogicalData> getEnemiesELD();

    public List<EntityLogicalData> getProjectilesELD();

    public List<EntityLogicalData> getCollectiblesELD();

}
