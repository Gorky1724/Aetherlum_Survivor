package aetherlum_survivor.model;

import java.util.List;

import aetherlum_survivor.util.EntityLogicalData;

public interface InterfaceModel {

    //handle gameloop
    public void startGameLoop();

    public void pauseGameLoop();

    public void resumeGameLoop();

    //update
    public void update();

    public void selectedScenario(int selected_scenario_num);

    //exposes entities
    public EntityLogicalData getPlayerELD();

    public List<EntityLogicalData> getEnemiesELD();

    public List<EntityLogicalData> getProjectilesELD(); 
    
    public List<EntityLogicalData> getCollectiblesELD(); 
}
