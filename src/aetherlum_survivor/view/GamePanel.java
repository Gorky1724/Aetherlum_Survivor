package aetherlum_survivor.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel {

    public GamePanel() {

    //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
    this.setDoubleBuffered(true);

    this.setBackground(Color.black);
    }

    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;
        /*
		paintMap(g2d);
        paintPlayer(g2d);
        paintEntities(g2d);
        */
	}
    
}
