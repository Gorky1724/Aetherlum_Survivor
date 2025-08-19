package aetherlum_survivor.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View implements InterfaceView {

    //---------------------------------------------------------------
	//! PRIVATE STATIC ATTRIBUTES

    private static View instance = null;
    protected JFrame gameFrame;

    //---------------------------------------------------------------


    //! CONSTRUCTOR
    private View() {
        //default
    }

    //---------------------------------------------------------------
	//! PUBLIC INSTANCE METHODS

    public void openGameFrame() {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                if(gameFrame == null) {
                    gameFrame = new JFrame();
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setResizable(false); //the screen dimension must not vary
                    gameFrame.setTitle("Aetherlum Survivor");
                    gameFrame.setLocationRelativeTo(null); //center of the screen
                }
                gameFrame.setVisible(true);
            }
		});

    }

    public void openStartPanel(){



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
