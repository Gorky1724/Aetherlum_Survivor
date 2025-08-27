package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;

import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class GamePanel extends JPanel {

    public GamePanel() {

        //off screen drawing buffer, then reported on screen - for rendering fluidity
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

        paintAdditionalElements(g2d);

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

    //! PAINT ELEMENTS_____________________________
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

    // paint additional elements: healthBar, xpBar, timer, P to Pause
    public void paintAdditionalElements(Graphics2D g2d) {
        //data
        int sec = Controller.getInstance().getTimePassed();
        double[] bar_level_hp = Controller.getInstance().getPlayerInfo();
        double currentXp = bar_level_hp[0];
        double xpBar = bar_level_hp[1];
        int level = (int) bar_level_hp[2]; 
        double currentHP = bar_level_hp[3];
        double maxHP = bar_level_hp[4];

        // HEALTH BAR
        int hbWidth = EntityData.PLAYER_WIDTH + 10;
        int hbHeight = 5;
        int hbCoordX = (int) (Constants.SCREEN_WIDTH/2 - EntityData.PLAYER_WIDTH/2 - 5);
        int hbCoordY = (int) (Constants.SCREEN_HEIGHT/2 + EntityData.PLAYER_HEIGHT/2 + 2);
        // percentage to fill
        double hpPercent = currentHP / maxHP;
        int hbFillWidth = (int) (hpPercent * hbWidth);
        //bar
        g2d.setColor(new Color(50, 50, 50, 150)); //dark grey - transparent
        g2d.fillRect(hbCoordX, hbCoordY, hbWidth, hbHeight);
        //hp_amount
        g2d.setColor(new Color(200, 0, 0, 200)); //red - transparent
        g2d.fillRect(hbCoordX, hbCoordY, hbFillWidth, hbHeight);
        //border
        g2d.setColor(Color.WHITE);
        g2d.drawRect(hbCoordX, hbCoordY, hbWidth, hbHeight);

        // XP BAR
        int barWidth = Constants.SCREEN_WIDTH - 60;
        int barHeight = 15;
        int barCoordX = 20; //top_left_corner
        int barCoordY = 20;
        //bar
        g2d.setColor(new Color(50, 50, 50, 150)); //dark grey - transparent
        g2d.fillRect(barCoordX, barCoordY, barWidth, barHeight);
        //xp_progression
        double progress = currentXp / xpBar;
        int widthToFill = (int) (progress * barWidth);
        if(level == EntityData.MAX_LEVEL) { //color all bar if is maxLevel
            widthToFill = barWidth;
        }
        g2d.setColor(new Color(255, 215, 0, 200)); //gold - semitransparent
        g2d.fillRect(barCoordX, barCoordY, widthToFill, barHeight);
        //bar_border
        g2d.setColor(Color.WHITE);
        g2d.drawRect(barCoordX, barCoordY, barWidth, barHeight);

        // TEXT UNDER IT
        int panelWidth = barWidth - 40;
        int panelHeight = 20;
        int panelX = barCoordX + 20;
        int panelY = barCoordY + barHeight + 5;
        g2d.setColor(new Color(30, 30, 30, 180));
        g2d.fillRect(panelX, panelY, panelWidth, panelHeight);
        //text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif", Font.BOLD, 14));
        //level - left
        g2d.drawString("Lvl: " + level, panelX + 10, panelY + 15);
        //timer - center
        String timerText = formatTime(sec);
        int textWidth = g2d.getFontMetrics().stringWidth(timerText);
        g2d.drawString(timerText, panelX + (panelWidth - textWidth) / 2, panelY + 15);
        //pause notify - right
        String pauseText = "P to Pause";
        int pauseWidth = g2d.getFontMetrics().stringWidth(pauseText);
        g2d.drawString(pauseText, panelX + panelWidth - pauseWidth - 10, panelY + 15);
        
    }

    //! UTILITIES_____________________________
    public String formatTime(int seconds){
        long min = seconds / 60;
        long sec = seconds % 60;
        return String.format("%02d:%02d", min, sec); //formats time to mm:ss instead of m:s
    }
    
}
