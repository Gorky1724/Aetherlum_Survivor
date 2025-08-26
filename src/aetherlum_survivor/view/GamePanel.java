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

    // to be able to repaint as desired - UPDATES the JPanel shown in the JFrame
    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;

        //needed to calculate the offset of each element that always centers camera on the player
        EntityLogicalData playerELD = Controller.getInstance().getPlayerELD();

		paintBackground(g2d);

        paintPlayer(g2d, playerELD);
        paintEnemies(g2d, playerELD);
        paintProjectiles(g2d, playerELD);
        paintCollectibles(g2d, playerELD);

        g2d.dispose(); //release computing resources
        
	}

    // LOGIC COORDINATES TO VIEW CONVERSION_______
    public Point convertLogicalToGraphical(double logicalX, double logicalY, EntityLogicalData playerELD) {

        int screenCenterX = (int) (Constants.SCREEN_WIDTH / 2 - playerELD.getWidth() / 2);
        int screenCenterY = (int) (Constants.SCREEN_HEIGHT / 2 - playerELD.getHeight() / 2);
        
        int screenX = (int) (logicalX - playerELD.getCoordX() + screenCenterX);
        int screenY = (int) (logicalY - playerELD.getCoordY() + screenCenterY);

        /*
        // screencenter == player_central_pixel_location
        int screenCenterX = Constants.SCREEN_WIDTH / 2;
        int screenCenterY = Constants.SCREEN_HEIGHT / 2;

        // player_logical_center
        double playerCenterX = playerELD.getCoordX() + playerELD.getWidth() / 2.0;
        double playerCenterY = playerELD.getCoordY() + playerELD.getHeight() / 2.0;

        // translation: move system to let player_center coincide with screen_center
        int screenX = (int) (logicalX - playerCenterX + screenCenterX);
        int screenY = (int) (logicalY - playerCenterY + screenCenterY);
        */
        
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
        Point playerLoc = convertLogicalToGraphical(playerELD.getCoordX(), playerELD.getCoordY(), playerELD);
        //System.out.println("Player graphical coord: " + playerLoc.x + ", " + playerLoc.y + ", " + (int) playerELD.getWidth() + ", " + (int) playerELD.getHeight());

        g2d.setColor(Color.WHITE);
        //getX() and getY() return 'double' values - here is needed an int so i use the attribute
        g2d.fillRect(playerLoc.x, playerLoc.y, (int) playerELD.getWidth(), (int) playerELD.getHeight());
    }

    public void paintEnemies(Graphics2D g2d, EntityLogicalData playerELD) {
        
        List<EntityLogicalData> enemyELD_list = Controller.getInstance().getEnemiesELD();
        for (EntityLogicalData enELD: enemyELD_list) {
            if(enELD.isActive()) {
                Point enemyLoc = convertLogicalToGraphical(enELD.getCoordX(),enELD.getCoordY(), playerELD);
                switch (enELD.getSpritePath()) {
                    case "YELLOW":
                        g2d.setColor(Color.YELLOW);
                        break;
                    case "GRAY":
                        g2d.setColor(Color.GRAY);
                        break;
                    case "MAGENTA":
                        g2d.setColor(Color.MAGENTA);
                        break;
                    case "GREEN":
                        g2d.setColor(Color.GREEN);
                        break;
                }
                //getX() and getY() return 'double' values - here is needed an int so i use the attribute
                g2d.fillRect(enemyLoc.x, enemyLoc.y, (int) enELD.getWidth(), (int) enELD.getHeight());
            }
        }

    }

    public void paintProjectiles(Graphics2D g2d, EntityLogicalData playerELD) {

        List<EntityLogicalData> projectilesELD_list = Controller.getInstance().getProjectilesELD();
        for(EntityLogicalData prjELD: projectilesELD_list) {
            if(prjELD.isActive()) {
                Point projectileLoc = convertLogicalToGraphical(prjELD.getCoordX(), prjELD.getCoordY(), playerELD);
                //System.out.println("Projectile graphical coord: " + projectileLoc.x + ", " + projectileLoc.y + ", " + (int) prjELD.getWidth() + ", " + (int) prjELD.getHeight());
                switch (prjELD.getSpritePath()) {
                    case "CYAN":
                        g2d.setColor(Color.CYAN);
                        break;
                    case "RED":
                        g2d.setColor(Color.RED);
                        break;
                    case "PINK":
                        g2d.setColor(Color.PINK);
                        break;
                }
                g2d.fillRect(projectileLoc.x, projectileLoc.y, (int) prjELD.getWidth(), (int) prjELD.getHeight());
            }
        }
    }

    public void paintCollectibles(Graphics2D g2d, EntityLogicalData playerELD) {
        //TODO - similar to paintEnemies
        
    }
    
}
