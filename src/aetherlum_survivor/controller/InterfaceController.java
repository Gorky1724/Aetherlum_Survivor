package aetherlum_survivor.controller;

import java.util.List;

import aetherlum_survivor.util.EntityLogicalData;

public interface InterfaceController {
    
    public void startApplication();

    //panels management
    public void openStartPanel();

    public void openSettingsPanel();

    public void openGamePanel();

    // handle GameLoop
    public void startGameLoop();

    public void pauseGameLoop();

    public void resumeGameLoop();

    // updates and requests
    public void requestViewUpdate();

    //exposes entities
    public EntityLogicalData getPlayerELD();

    public List<EntityLogicalData> getEnemiesELD();

    public List<EntityLogicalData> getProjectilesELD();

}
