package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityLogicalData;
import aetherlum_survivor.util.ResourcePaths;
import aetherlum_survivor.util.ScenarioData;
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
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    public final List<EntityLogicalData> deathAnimations = new ArrayList<>();

    public int scenario_num;

    public BufferedImage tile;
    public int tile_width;
    public int tile_height;
    public int tile_x;
    public int tile_y;

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

		paintBackground(g2d, playerELD);

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

    //! TO SELECT SCENARIO BAKGROUNDTILES__________________________________
    public void setScenario(int scenario_selected_num) {
        this.scenario_num = scenario_selected_num;

        switch (this.scenario_num) { //assign adeguate values
            case 1:
                this.tile_width = ScenarioData.SCEN_1_TILE_WIDTH;
                this.tile_height = ScenarioData.SCEN_1_TILE_HEIGHT;
                this.tile_x = ScenarioData.SCEN_1_X;
                this.tile_y = ScenarioData.SCEN_1_Y;
                this.tile = ResourceHandler.loadImage(ResourcePaths.Images.SCEN_1);
                break;
            case 2:
                this.tile_width = ScenarioData.SCEN_2_TILE_WIDTH;
                this.tile_height = ScenarioData.SCEN_2_TILE_HEIGHT;
                this.tile_x = ScenarioData.SCEN_2_X;
                this.tile_y = ScenarioData.SCEN_2_Y;
                this.tile = ResourceHandler.loadImage(ResourcePaths.Images.SCEN_2);
                break;
            case 3:
                this.tile_width = ScenarioData.SCEN_3_TILE_WIDTH;
                this.tile_height = ScenarioData.SCEN_3_TILE_HEIGHT;
                this.tile_x = ScenarioData.SCEN_3_X;
                this.tile_y = ScenarioData.SCEN_3_Y;
                this.tile = ResourceHandler.loadImage(ResourcePaths.Images.SCEN_3);
                break;
        }
    }

    //! PAINT ELEMENTS_____________________________
    public void paintBackground(Graphics2D g2d, EntityLogicalData playerELD) {

        double playerX = playerELD.getCoordX();
        double playerY = playerELD.getCoordY();

        //how much of the player is inside the tile he is over
        int offsetX = (int) (playerX%this.tile_width);
        int offsetY = (int) (playerY%this.tile_height);

        //necessary tiles to cover screen + some space extra for movement
        int tilesNumX = Constants.SCREEN_WIDTH/this.tile_width + 4;
        int tilesNumY = Constants.SCREEN_HEIGHT/this.tile_height + 4;

        //draws
        for(int i = -(tilesNumX/2); i <= (tilesNumX/2); i++) {
            for(int j = -(tilesNumY/2); j <= (tilesNumY/2); j++) {
                int screenX = i * this.tile_width - offsetX + Constants.SCREEN_WIDTH/2;
                int screenY = j * this.tile_height - offsetY + Constants.SCREEN_HEIGHT/2;

                g2d.drawImage(this.tile, screenX, screenY, this.tile_width, this.tile_height, null);
            }
        }
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
                
                Image frameToDraw = AnimationHandler.getFrameToDraw(prjELD);
                if(prjELD.getDirection() == EntityData.RIGHT && prjELD.isActive()) {
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.NOT_FLIPPED, projectileLoc.x, projectileLoc.y, (int) prjELD.getWidth(), (int) prjELD.getHeight());
                } else if (prjELD.getDirection() == EntityData.LEFT && prjELD.isActive()){
                    AnimationHandler.drawSprite(g2d, frameToDraw, Constants.FLIPPED, projectileLoc.x, projectileLoc.y, (int) prjELD.getWidth(), (int) prjELD.getHeight());
                }
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

            Image frameToDraw = AnimationHandler.getFrameToDraw(eld);
            Point eldLoc = convertLogicalToGraphical(eld.getCoordX(), eld.getCoordY(), playerELD);

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

            long animationLasted = Controller.getInstance().getClockCycle() - eld.getStartingClockOfCondition();
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
