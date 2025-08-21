package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityGraphicalData;
import aetherlum_survivor.util.Constants;

import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel {

    public GamePanel() {

    //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
    this.setDoubleBuffered(true);

    this.setBackground(Color.black);
    }

    // to be able to repaint as desired
    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;

        //calculate camera OFFSET - in this way camera is always centered on the player
        EntityGraphicalData playerEGD = Controller.getInstance().getPlayerEGD();
        int cameraX = (int) (playerEGD.getCoordX() - Constants.SCREEN_WIDTH / 2);
        int cameraY = (int) (playerEGD.getCoordY() - Constants.SCREEN_HEIGHT / 2);

		paintBackground(g2d, cameraX, cameraY);

        paintPlayer(g2d, cameraX, cameraY, playerEGD);
        paintProjectiles(g2d, cameraX, cameraY);
        paintEnemies(g2d, cameraX, cameraY);

        g2d.dispose(); //release computing resources
        
	}

    // PAINT ELEMENTS_____________________________
    public void paintBackground(Graphics2D g2d, int cameraX, int cameraY) {
        //TODO
    }

    public void paintPlayer(Graphics2D g2, int cameraX, int cameraYd, EntityGraphicalData egd) {
        //TODO
    }

    public void paintEnemies(Graphics2D g2d, int cameraX, int cameraY) {
        //TODO
        List<EntityGraphicalData> enemyEGD_List = Controller.getInstance().getEnemiesEGD();
        // Itera attraverso la lista dei nemici e disegnali
        // for (Enemy enemy : enemies) {
        //     // Calcola la posizione sullo schermo e disegna
        // }
    }

    public void paintProjectiles(Graphics2D g2d, int cameraX, int cameraY) {
        //TODO
        List<EntityGraphicalData> projectileEGD_List = Controller.getInstance().getEnemiesEGD();
    }
    
}
