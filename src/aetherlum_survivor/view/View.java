package aetherlum_survivor.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;

public class View implements InterfaceView {

    //---------------------------------------------------------------
	//! PRIVATE STATIC ATTRIBUTES

    private static View instance = null;
    private JFrame gameFrame; //TODO jframe e contPane erano protected
    private Container contPane;

    //to have single instatiations
    private StartPanel startPanel;
    private SettingsPanel settingsPanel;
    private GamePanel gamePanel;

    //panel currently shown
    private JPanel currentPanel;

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
            this.gameFrame.setResizable(false); //the screen dimension must not vary
            this.gameFrame.setTitle("Aetherlum Survivor");

            this.contPane = gameFrame.getContentPane();
        }
        this.gameFrame.setVisible(true);
    }

    @Override
    public void openStartPanel(){
        if (this.startPanel == null) {
            this.startPanel = new StartPanel();
        }

        switchToPanel(this.startPanel);

        //change frame location
        final int X_LOC = 550;
        final int Y_LOC = 100;
        this.gameFrame.setLocation(X_LOC,Y_LOC);

        this.gameFrame.pack();
    }

    @Override
    public void openSettingsPanel(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openGamePanel'");
    }

    @Override
    public void openGamePanel(){
        if(this.gamePanel == null) {
            this.gamePanel = new GamePanel();
        }

        switchToPanel(this.gamePanel);
        
        //change frame location
        final int X_LOC = 300;
        final int Y_LOC = 70;
        this.gameFrame.setLocation(X_LOC,Y_LOC);

        this.gameFrame.pack();
    }

    @Override
    public void switchToPanel(JPanel newPanel) {
        //close precedently shown panel
        if (this.currentPanel != null) {
            this.contPane.remove(this.currentPanel);
        }

        //add to gameFrame
        this.contPane.setLayout(new BorderLayout());
        this.contPane.add(newPanel, BorderLayout.CENTER);

        //updates current panel
        this.currentPanel = newPanel;

        //updates frame
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.gameFrame.pack();

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
