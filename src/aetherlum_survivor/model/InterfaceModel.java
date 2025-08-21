package aetherlum_survivor.model;

import java.util.List;

import aetherlum_survivor.util.EntityGraphicalData;

public interface InterfaceModel {

    //handle gameloop
    public void startGameLoop();

    public void pauseGameLoop();

    public void resumeGameLoop();

    //update
    public void update();

    //exposes entities
    public EntityGraphicalData getPlayerEGD();

    public List<EntityGraphicalData> getEnemiesEGD();

    public List<EntityGraphicalData> getProjectilesEGD();
    
}
