package aetherlum_survivor.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Container;

public class View implements InterfaceView {

    //---------------------------------------------------------------
	//! PRIVATE STATIC ATTRIBUTES

    private static View instance = null;
    protected JFrame gameFrame;
    protected Container contPane;

    //---------------------------------------------------------------


    //! CONSTRUCTOR
    private View() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    @Override
    public void openGameFrame() {
        if(gameFrame == null) {
            gameFrame = new JFrame();
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setResizable(false); //the screen dimension must not vary
            gameFrame.setTitle("Aetherlum Survivor");

            contPane = gameFrame.getContentPane();
        }
        gameFrame.setVisible(true);
    }

    @Override
    public void openStartPanel(){
        StartPanel startPanel = new StartPanel();

        //add to gameFrame
        this.contPane.setLayout(new BorderLayout());
        this.contPane.add(startPanel, BorderLayout.CENTER);

        //change frame location
        final int X_LOC = 550;
        final int Y_LOC = 100;
        gameFrame.setLocation(X_LOC,Y_LOC);

        this.gameFrame.pack();
    }

    @Override
    public void closeStartPanel(){
        this.contPane.removeAll();

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }

    @Override
    public void openSettingsPanel(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openGamePanel'");
    }

    @Override
    public void closeSettingsPanel(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openGamePanel'");
    }

    @Override
    public void openGamePanel(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openGamePanel'");
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
