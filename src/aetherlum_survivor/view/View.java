package aetherlum_survivor.view;
import aetherlum_survivor.controller.KeyHandler;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
        if(this.gameFrame == null) {
            this.gameFrame = new JFrame();
            this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.gameFrame.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
            this.gameFrame.setResizable(false); //the screen dimension must not vary
            //change frame location
            final int X_LOC = 300;
            final int Y_LOC = 70;
            this.gameFrame.setLocation(X_LOC,Y_LOC);
            this.gameFrame.setTitle("Aetherlum Survivor");

            //to setup CardLayout
            this.cardLayout = new CardLayout();
            this.cardPanel = new JPanel(cardLayout);

            this.startPanel = new StartPanel();
            this.settingsPanel = new SettingsPanel();
            this.gamePanel = new GamePanel();
            attachKeyListenerToGamePanel();
            this.scenarioPanel = new ScenarioPanel();
            this.gameOverPanel = new GameOverPanel();
            this.pausePanel = new PausePanel();
            this.levelUpPanel = new LevelUpPanel();
            
            this.cardPanel.add(this.startPanel, START_PANEL);
            this.cardPanel.add(this.gamePanel, GAME_PANEL);
            this.cardPanel.add(this.settingsPanel, SETTINGS_PANEL);
            this.cardPanel.add(this.scenarioPanel, SCENARIO_PANEL);
            this.cardPanel.add(this.gameOverPanel, GAME_OVER_PANEL);
            this.cardPanel.add(this.pausePanel, PAUSE_PANEL);
            this.cardPanel.add(this.levelUpPanel, LEVEL_UP_PANEL);

            this.gameFrame.add(this.cardPanel);
            this.gameFrame.setVisible(true);
        }
    }

    @Override
    public void openStartPanel(){
        this.cardLayout.show(cardPanel, START_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void openSettingsPanel(){
        this.cardLayout.show(cardPanel, SETTINGS_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void openScenarioPanel(){
        this.cardLayout.show(cardPanel, SCENARIO_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void openGamePanel(){
        this.cardLayout.show(cardPanel, GAME_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();

        //sets focus to get keyboard inputs
        this.gamePanel.requestFocusInWindow();
    }

    @Override
    public void openGameOverPanel(){
        this.cardLayout.show(cardPanel, GAME_OVER_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void openPausePanel(){
        this.cardLayout.show(cardPanel, PAUSE_PANEL);

        this.pausePanel.updatePlayerData();

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void openLevelUpPanel(Map<Integer, LevelUpOptions> randomLvlUp){
        this.cardLayout.show(cardPanel, LEVEL_UP_PANEL);

        this.levelUpPanel.setup(randomLvlUp);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
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
