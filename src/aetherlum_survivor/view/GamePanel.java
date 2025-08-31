package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.model.Model;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.AnimationData.AnimationStats;
import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.EntityData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class GamePanel extends JPanel {

    public final List<EntityLogicalData> deathAnimations = new ArrayList<>();

    public GamePanel() {

        //off screen drawing buffer, then reported on screen - for rendering fluidity
        this.setDoubleBuffered(true);

        this.setBackground(Color.black);
    }

    //! PAINTCOMPONENT OVERRIDE_____________________________
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
        paintDeathAnimations(g2d, playerELD);

        paintAdditionalElements(g2d);

        g2d.dispose(); //release computing resources
        
	}

    //! LOGIC COORDINATES TO GRAPHICAL COORDINATES CONVERSION__________________________________
    public Point convertLogicalToGraphical(double logicalX, double logicalY, EntityLogicalData playerELD) {

        int screenCenterX = (int) (Constants.SCREEN_WIDTH / 2 - playerELD.getWidth() / 2);
        int screenCenterY = (int) (Constants.SCREEN_HEIGHT / 2 - playerELD.getHeight() / 2);
        
        int screenX = (int) (logicalX - playerELD.getCoordX() + screenCenterX);
        int screenY = (int) (logicalY - playerELD.getCoordY() + screenCenterY);
        
        return new Point(screenX, screenY);
    }

    //! RELATED TO DEATH ANIMATION__________________________________
    public void updateDeathAnimationList(EntityLogicalData eld) {
        this.deathAnimations.add(eld);
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

        Image frameToDraw = AnimationHandler.getFrameToDraw(playerELD);
        if(playerELD.getDirection() == EntityData.RIGHT && playerELD.isActive()) {
            AnimationHandler.drawSprite(g2d, frameToDraw, Constants.NOT_FLIPPED, playerLoc.x, playerLoc.y, (int) playerELD.getWidth(), (int) playerELD.getHeight());
        } else if (playerELD.getDirection() == EntityData.LEFT && playerELD.isActive()){
            AnimationHandler.drawSprite(g2d, frameToDraw, Constants.FLIPPED, playerLoc.x, playerLoc.y, (int) playerELD.getWidth(), (int) playerELD.getHeight());
        }                
    }

    public void paintEnemies(Graphics2D g2d, EntityLogicalData playerELD) {
        
        List<EntityLogicalData> enemyELD_list = Controller.getInstance().getEnemiesELD();
        for (EntityLogicalData enELD: enemyELD_list) {
            if(enELD.isActive()) {
                Point enemyLoc = convertLogicalToGraphical(enELD.getCoordX(),enELD.getCoordY(), playerELD);

                Image frameToDraw = AnimationHandler.getFrameToDraw(enELD);
                if(enELD.getDirection() == EntityData.LEFT && enELD.isActive()) {
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.NOT_FLIPPED, enemyLoc.x, enemyLoc.y, (int) enELD.getWidth(), (int) enELD.getHeight());
                } else if (enELD.getDirection() == EntityData.RIGHT && enELD.isActive()){
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.FLIPPED, enemyLoc.x, enemyLoc.y, (int) enELD.getWidth(), (int) enELD.getHeight());
                }
            }
        }

    }

    public void paintProjectiles(Graphics2D g2d, EntityLogicalData playerELD) {

        List<EntityLogicalData> projectilesELD_list = Controller.getInstance().getProjectilesELD();
        for(EntityLogicalData prjELD: projectilesELD_list) {
            if(prjELD.isActive()) {
                Point projectileLoc = convertLogicalToGraphical(prjELD.getCoordX(), prjELD.getCoordY(), playerELD);
                //System.out.println("Projectile graphical coord: " + projectileLoc.x + ", " + projectileLoc.y + ", " + (int) prjELD.getWidth() + ", " + (int) prjELD.getHeight());
                
                g2d.setColor(Color.WHITE);
                g2d.fillRect(projectileLoc.x, projectileLoc.y, (int) prjELD.getWidth(), (int) prjELD.getHeight());
            }
        }
    }

    public void paintCollectibles(Graphics2D g2d, EntityLogicalData playerELD) {
        
        List<EntityLogicalData> collectiblesELD_list = Controller.getInstance().getCollectiblesELD();
        for(EntityLogicalData cltELD: collectiblesELD_list) {
            if(cltELD.isActive()) {
                Point collectibleLoc = convertLogicalToGraphical(cltELD.getCoordX(), cltELD.getCoordY(), playerELD);
                Image frameToDraw = AnimationHandler.getFrameToDraw(cltELD);

                //doesen't need to be flipped
                g2d.drawImage(frameToDraw,collectibleLoc.x, collectibleLoc.y, (int) cltELD.getWidth(), (int) cltELD.getHeight(),null);
            }
        }
    }

    public void paintDeathAnimations(Graphics2D g2d, EntityLogicalData playerELD) {

        Iterator<EntityLogicalData> it = this.deathAnimations.iterator();
        while (it.hasNext()) {
            EntityLogicalData eld = it.next();


            //TODO temporrary - remove when implemented types
            if(eld.getType() == EntityData.XP_GLOBE_TYPE || eld.getType() == EntityData.HP_GLOBE_TYPE || eld.getType() == EntityData.PIERCING_PROJ_TYPE || eld.getType() == EntityData.FAST_PROJ_TYPE || eld.getType() == EntityData.BASE_PROJ_TYPE) {
                continue;
            }//

            Image frameToDraw = AnimationHandler.getFrameToDraw(eld);
            Point eldLoc = convertLogicalToGraphical(eld.getCoordX(), eld.getCoordY(), playerELD);

            //TODO - should be fixed width and height based on each different animation
            if(eld.getType() >= EntityData.ENEMIES_TYPE_RANGE[0] && eld.getType() <= EntityData.ENEMIES_TYPE_RANGE[1]) { //enemies have sprites in opposite direction
                if(eld.getDirection() == EntityData.LEFT) {
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.NOT_FLIPPED, eldLoc.x, eldLoc.y, (int) eld.getWidth(), (int) eld.getHeight());
                } else if (eld.getDirection() == EntityData.RIGHT){
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.FLIPPED, eldLoc.x, eldLoc.y, (int) eld.getWidth(), (int) eld.getHeight());
                }
            } else {
                if(eld.getDirection() == EntityData.RIGHT) {
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.NOT_FLIPPED, eldLoc.x, eldLoc.y, (int) eld.getWidth(), (int) eld.getHeight());
                } else if (eld.getDirection() == EntityData.LEFT){
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.FLIPPED, eldLoc.x, eldLoc.y, (int) eld.getWidth(), (int) eld.getHeight());
                }
            } 

            long animationLasted = Model.getInstance().getClockCyle() - eld.getStartingClockOfCondition();
            AnimationStats st = AnimationHandler.getAnimationStatsBasedOnTypeAndCondition(eld.getType(), EntityData.DYING);
            int animDuration = st.animationNumFrames*st.frameDuration - 2; //makes last frame last a little less to avoid redraw of the first
            if(animationLasted >= animDuration) {
                it.remove();

                //if it was the player that was dying and animation in finished = gameOver
                if(eld.getType() == EntityData.PLAYER_TYPE) {
                    Controller.getInstance().handleGameOver();
                }
            }
        }
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
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
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
