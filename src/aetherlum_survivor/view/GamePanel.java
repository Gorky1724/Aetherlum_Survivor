package aetherlum_survivor.view;

import javax.swing.JPanel;

import java.awt.Dimension; 
import java.awt.Color;

import aetherlum_survivor.util.Constants;

public class GamePanel extends JPanel {

    public GamePanel() {
    this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

    //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
    this.setDoubleBuffered(true);

    this.setBackground(Color.black);
    }
    
}
