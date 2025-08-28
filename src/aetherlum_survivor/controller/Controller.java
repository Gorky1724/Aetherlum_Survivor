package aetherlum_survivor.controller;

import java.util.List;
import java.util.Map;

import aetherlum_survivor.model.Model;
import aetherlum_survivor.view.View;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

public class Controller implements InterfaceController{

    //---------------------------------------------------------------
    //! PRIVATE STATIC ATTRIBUTES
    private static Controller instance = null;

    //---------------------------------------------------------------

    //! CONSTRUCTOR
    private Controller() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public void startApplication() {
        View.getInstance().openGameFrame(); //only one frame with all the panels 
        openStartPanel();
    }

    //PANEL MANAGEMENT_____________________________
    @Override
    public void openStartPanel() {
        View.getInstance().openStartPanel();
    }

    @Override
    public void openSettingsPanel() {
        View.getInstance().openSettingsPanel();
    }

    @Override
    public void openScenarioPanel() {
        View.getInstance().openScenarioPanel();
    }

    @Override
    public void openGamePanel() {
        View.getInstance().openGamePanel();
    }

    @Override
    public void handleGameOver() {
        View.getInstance().openGameOverPanel();
    }

    @Override
    public void handlePauseGame() {
        this.stopGameLoop();
        KeyHandler.getInstance().resetKeys(); //makes sure that no player movement keys remains pressed when the game reopens

        View.getInstance().openPausePanel();
    }

    @Override
    public void handleLevelUp(Map<Integer, LevelUpOptions> randomLvlUp) {
        this.stopGameLoop();
        KeyHandler.getInstance().resetKeys(); //makes sure that no player movement keys remains pressed when the game reopens

        View.getInstance().openLevelUpPanel(randomLvlUp);
    }

    @Override
    public void handleResumeGame() {
        this.resumeGameLoop();

        View.getInstance().openGamePanel();
    }
    
    // HANLDE GAMELOOP_____________________________
    @Override
    public void startGameLoop() {
        Model.getInstance().startGameLoop();
    }

    @Override
    public void stopGameLoop() {
        Model.getInstance().stopGameLoop();
    }
    
    @Override
    public  void resumeGameLoop() {
        Model.getInstance().resumeGameLoop();
    }

    //UPDATES and REQUESTS_____________________________
    @Override
    public void requestViewUpdate() {
        View.getInstance().update();
    }

    @Override
    public void transmitScenarioToModel(int scenario_num) {
        Model.getInstance().selectedScenario(scenario_num);
    }

    @Override
    public void upgradePlayer(Map<Integer, LevelUpOptions> powerUpData) {
        Model.getInstance().upgradePlayer(powerUpData);
    }

    // EXPOSES DATA_____________________________
    @Override
    public EntityLogicalData getPlayerELD() {
        return Model.getInstance().getPlayerELD();
    }
    @Override
    public List<EntityLogicalData> getEnemiesELD() {
        return Model.getInstance().getEnemiesELD();
    }
    @Override
    public List<EntityLogicalData> getProjectilesELD() {
        return Model.getInstance().getProjectilesELD();
    }
    @Override
    public List<EntityLogicalData> getCollectiblesELD() {
        return Model.getInstance().getCollectiblesELD();
    }

    @Override
    public int getTimePassed() {
        return Model.getInstance().getTimePassed();
    }
    @Override
    public double[] getPlayerInfo() {
        return Model.getInstance().getPlayerInfo();
    }

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC METHODS

    //* singleton pattern
    public static InterfaceController getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;

    }

	//---------------------------------------------------------------
    
}
