package aetherlum_survivor.view;
import aetherlum_survivor.controller.KeyHandler;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.ResourcePaths;
import aetherlum_survivor.util.LevelUpData.LevelUpOptions;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.Map;
import java.awt.CardLayout; //to alternate JPanel

public class View implements InterfaceView {

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static View instance = null;
    private JFrame gameFrame; 

    private StartPanel startPanel;
    private SettingsPanel settingsPanel;
    private GamePanel gamePanel;
    private ScenarioPanel scenarioPanel;
    private GameOverPanel gameOverPanel;
    private PausePanel pausePanel;
    private LevelUpPanel levelUpPanel;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    //---------------------------------------------------------------


    //! CONSTRUCTOR
    private View() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    //audio handling - to avoid lags and UI blocks during game
    @Override
    public void preloadClips() {
        ResourceHandler.preloadAll();
    }

    //panel handling
    @Override
    public void openGameFrame() {

        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() { // is required to make the this work inside the invokeLater()
                if(gameFrame == null) {
                    gameFrame = new JFrame();
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
                    gameFrame.setResizable(false); //the screen dimension must not vary
                    //change frame location
                    final int X_LOC = 300;
                    final int Y_LOC = 70;
                    gameFrame.setLocation(X_LOC,Y_LOC);
                    gameFrame.setTitle("Aetherlum Survivor");

                    //to setup CardLayout
                    cardLayout = new CardLayout();
                    cardPanel = new JPanel(cardLayout);

                    startPanel = new StartPanel();
                    settingsPanel = new SettingsPanel();
                    gamePanel = new GamePanel();
                    attachKeyListenerToGamePanel();
                    scenarioPanel = new ScenarioPanel();
                    gameOverPanel = new GameOverPanel();
                    pausePanel = new PausePanel();
                    levelUpPanel = new LevelUpPanel();
                    
                    cardPanel.add(startPanel, Constants.START_PANEL);
                    cardPanel.add(gamePanel, Constants.GAME_PANEL);
                    cardPanel.add(settingsPanel, Constants.SETTINGS_PANEL);
                    cardPanel.add(scenarioPanel, Constants.SCENARIO_PANEL);
                    cardPanel.add(gameOverPanel, Constants.GAME_OVER_PANEL);
                    cardPanel.add(pausePanel, Constants.PAUSE_PANEL);
                    cardPanel.add(levelUpPanel, Constants.LEVEL_UP_PANEL);

                    gameFrame.add(cardPanel);
                    gameFrame.setVisible(true);
                }
            }
        });
    }

    @Override
    public void openStartPanel(){
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.START_PANEL);

                ResourceHandler.playClip(ResourcePaths.Audio.START_PANEL_MUSIC, Constants.LOOPED);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openSettingsPanel(String openedFrom){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.SETTINGS_PANEL);

                settingsPanel.setParentPanel(openedFrom);

                ResourceHandler.playClip(ResourcePaths.Audio.SETTINGS_PANEL_MUSIC, Constants.LOOPED);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openScenarioPanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.SCENARIO_PANEL);

                ResourceHandler.playClip(ResourcePaths.Audio.SCENARIO_PANEL_MUSIC, Constants.LOOPED);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openGamePanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.GAME_PANEL);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();

                //sets focus to get keyboard inputs
                gamePanel.requestFocusInWindow();
            }
        });
    }

    @Override
    public void openGameOverPanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.GAME_OVER_PANEL);

                gameOverPanel.updatePanel();

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openPausePanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.PAUSE_PANEL);

                pausePanel.updatePlayerData();

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openLevelUpPanel(Map<Integer, LevelUpOptions> randomLvlUp){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, Constants.LEVEL_UP_PANEL);

                levelUpPanel.setup(randomLvlUp);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    //updateView
    @Override
    public void update(){
        this.gamePanel.repaint();
    }

    @Override
    public void updateDeathAnimationList(EntityLogicalData eld) {
        this.gamePanel.updateDeathAnimationList(eld);
    }

    @Override
    public void transmitScenarioToGamePanel(int scenario_selected_num) {
        this.gamePanel.setScenario(scenario_selected_num);
    }

    // keyListener for GamePanel
    @Override
    public void attachKeyListenerToGamePanel(){
        if (this.gamePanel.getKeyListeners().length == 0) { //return array of associated KeyListeners - avoids to attach another one if there is already one
            this.gamePanel.addKeyListener((KeyListener) KeyHandler.getInstance());

            //grants that keys will be read when called focus
            this.gamePanel.setFocusable(true);
        }
    }

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC INSTANCE METHODS

    public static InterfaceView getInstance() {
        if(instance == null) {
            instance = new View();
        }
        return instance;
    }

    //---------------------------------------------------------------
    
}
