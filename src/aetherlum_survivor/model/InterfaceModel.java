package aetherlum_survivor.model;

import java.util.List;

import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public interface InterfaceModel {

    //handle gameloop
    public void startGameLoop();

    public void stopGameLoop();

    public void resumeGameLoop();

    public void setGameOver();

    //ingame timer
    public int getTimePassed();

    //update
    public void update();

    public void selectedScenario(int selected_scenario_num);

    public void checkCollision();

    public void upgradePlayer(LevelUpOptions powerUpData);

    //exposes data
    public EntityLogicalData getPlayerELD();

    public List<EntityLogicalData> getEnemiesELD();

    public List<EntityLogicalData> getProjectilesELD(); 
    
    public List<EntityLogicalData> getCollectiblesELD(); 

    public double[] getPlayerExpInfo();
}
