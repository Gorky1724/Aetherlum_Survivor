package aetherlum_survivor.view;

import javax.swing.JPanel;

import java.awt.Color;

public class GamePanel extends JPanel {

    public GamePanel() {

    //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
    this.setDoubleBuffered(true);

    this.setBackground(Color.black);
    }
    
}
