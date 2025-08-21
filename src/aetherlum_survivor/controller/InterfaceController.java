package aetherlum_survivor.controller;

import java.util.List;

import aetherlum_survivor.util.EntityGraphicalData;

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
    public EntityGraphicalData getPlayerEGD();

    public List<EntityGraphicalData> getEnemiesEGD();

    public List<EntityGraphicalData> getProjectilesEGD();

}
