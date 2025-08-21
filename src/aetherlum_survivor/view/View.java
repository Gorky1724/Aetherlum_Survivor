package aetherlum_survivor.view;
import aetherlum_survivor.controller.KeyHandler;

import javax.swing.JFrame;
import javax.swing.JPanel;

import aetherlum_survivor.util.Constants;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.CardLayout; //to alternate JPanel

public class View implements InterfaceView {

    //---------------------------------------------------------------
	//! PRIVATE STATIC & NON ATTRIBUTES

    private static View instance = null;
    private JFrame gameFrame; 

    private StartPanel startPanel;
    private SettingsPanel settingsPanel;
    private GamePanel gamePanel;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    //strings to refer for the JPanel switch via CardLayout
    private static final String START_PANEL = "START";
    private static final String SETTINGS_PANEL = "SETTINGS";
    private static final String GAME_PANEL = "GAME";

    //---------------------------------------------------------------


    //! CONSTRUCTOR
    private View() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

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
            
            this.cardPanel.add(this.startPanel, START_PANEL);
            this.cardPanel.add(this.gamePanel, GAME_PANEL);
            this.cardPanel.add(this.settingsPanel, SETTINGS_PANEL);
            
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
    public void openGamePanel(){
        this.cardLayout.show(cardPanel, GAME_PANEL);

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();
    }

    @Override
    public void update(){
        this.gamePanel.repaint();
    }

    @Override
    public void attachKeyListenerToGamePanel(){
        this.gamePanel.addKeyListener((KeyListener) KeyHandler.getInstance());
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
