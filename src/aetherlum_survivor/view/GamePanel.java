package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.Constants;

import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

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
        EntityLogicalData playerELD = Controller.getInstance().getPlayerELD();

		paintBackground(g2d);

        paintPlayer(g2d, playerELD);
        paintProjectiles(g2d, playerELD);
        paintEnemies(g2d, playerELD);

        g2d.dispose(); //release computing resources
        
	}

    // LOGIC COORDINATES TO VIEW CONVERSION_______
    public Point convertLogicalToGraphical(double logicalX, double logicalY, EntityLogicalData playerELD) {
        int screenCenterX = (int) (Constants.SCREEN_WIDTH / 2 - playerELD.getWidth() / 2);
        int screenCenterY = (int) (Constants.SCREEN_HEIGHT / 2 - playerELD.getHeight() / 2);
        
        int screenX = (int) (logicalX - playerELD.getCoordX() + screenCenterX);
        int screenY = (int) (logicalY - playerELD.getCoordY() + screenCenterY);
        return new Point(screenX, screenY);
    }

    // PAINT ELEMENTS_____________________________
    //TODO the implementation must be improved with sprites and animation
    public void paintBackground(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);

        //TODO quando ci saranno delle tile di background questa non andrà più bene:
        // a quel punto infatti il background non potrà seguire il personaggio ma dovrà restare fermo
        // e aggiungere tiles pian piano che il personaggio si avvicina ad una direzione, rimuovendo quelle troppo lontane
        int GAME_PANEL_TOP_LEFT_CORNER_X = 0;
        int GAME_PANEL_TOP_LEFT_CORNER_Y = 0;
        g2d.fillRect(GAME_PANEL_TOP_LEFT_CORNER_X, GAME_PANEL_TOP_LEFT_CORNER_Y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    public void paintPlayer(Graphics2D g2d, EntityLogicalData playerELD) {
        //the player must always be centered on the screen: gX = halfScreenWidth - halfCharacterDimension, gY = halfScreenHeight - halfCharacterDimension
        int playerGraphicalX = (int) (Constants.SCREEN_WIDTH / 2 - playerELD.getWidth() / 2);
        int playerGraphicalY = (int) (Constants.SCREEN_HEIGHT / 2 - playerELD.getHeight() / 2);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerGraphicalX, playerGraphicalY, (int) playerELD.getWidth(), (int) playerELD.getHeight());
    }

    public void paintEnemies(Graphics2D g2d, EntityLogicalData playerELD) {
        //TODO
        //List<EntityLogicalData> enemyELD_List = Controller.getInstance().getEnemiesELD();
        // Itera attraverso la lista dei nemici e disegnali
        // for (Enemy enemy : enemyELD_List) {
        //     // Calcola la posizione sullo schermo e disegna
        //    g2d.fillRect(enemy.getCoordX() - cameraX, 0 - cameraY, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // }

        //TODO remove - used for reference in the moving
        Point enemyLoc = convertLogicalToGraphical(70,70, playerELD);
        g2d.setColor(Color.YELLOW);
        //getX() and getY() return 'double' values - here is needed an int so i use the attribute
        g2d.fillRect(enemyLoc.x, enemyLoc.y, 10, 10);
    }

    public void paintProjectiles(Graphics2D g2d, EntityLogicalData playerELD) {
        //TODO
        //List<EntityLogicalData> projectileELD_List = Controller.getInstance().getEnemiesELD();
    }
    
}
