package aetherlum_survivor.view;
import aetherlum_survivor.controller.KeyHandler;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import aetherlum_survivor.util.Constants;
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
    
    //strings to refer for the JPanel switch via CardLayout
    private static final String START_PANEL = "START";
    private static final String SETTINGS_PANEL = "SETTINGS";
    private static final String GAME_PANEL = "GAME";
    private static final String SCENARIO_PANEL = "SCENARIO";
    private static final String GAME_OVER_PANEL = "GAMEOVER";
    private static final String PAUSE_PANEL = "PAUSE";
    private static final String LEVEL_UP_PANEL = "LEVEL_UP";

    //---------------------------------------------------------------


    //! CONSTRUCTOR
    private View() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

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
                    
                    cardPanel.add(startPanel, START_PANEL);
                    cardPanel.add(gamePanel, GAME_PANEL);
                    cardPanel.add(settingsPanel, SETTINGS_PANEL);
                    cardPanel.add(scenarioPanel, SCENARIO_PANEL);
                    cardPanel.add(gameOverPanel, GAME_OVER_PANEL);
                    cardPanel.add(pausePanel, PAUSE_PANEL);
                    cardPanel.add(levelUpPanel, LEVEL_UP_PANEL);

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
                cardLayout.show(cardPanel, START_PANEL);

                gameFrame.revalidate();
                gameFrame.repaint();
                gameFrame.pack();
            }
        });
    }

    @Override
    public void openSettingsPanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(cardPanel, SETTINGS_PANEL);

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
                cardLayout.show(cardPanel, SCENARIO_PANEL);

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
                cardLayout.show(cardPanel, GAME_PANEL);

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
                cardLayout.show(cardPanel, GAME_OVER_PANEL);

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
                cardLayout.show(cardPanel, PAUSE_PANEL);

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
                cardLayout.show(cardPanel, LEVEL_UP_PANEL);

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
