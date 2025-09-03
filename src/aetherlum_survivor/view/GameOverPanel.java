package aetherlum_survivor.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.ResourcePaths;


public class GameOverPanel extends JPanel {

    private JLabel memoryLabel;

    private BufferedImage bckgImage;
    private GridBagConstraints layout;

    public GameOverPanel() {

        //gameover label
        JLabel gameOverLabel = new JLabel("YOU DIED");

        this.memoryLabel = new JLabel("PLACEHOLDER");

        //button to get back to main menu
        JButton mainMenu_Butt = new JButton("Return to Main Menu");
        mainMenu_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                View.getInstance().openStartPanel();
            }
		});

        setLayout(new GridBagLayout());
        this.layout = new GridBagConstraints(); 

        //customization
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 72));
        gameOverLabel.setForeground(new Color(200, 50, 50));
        gameOverLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

        memoryLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        memoryLabel.setOpaque(true);
        memoryLabel.setBackground(new Color(180, 200, 170,100));
        memoryLabel.setForeground(Color.BLACK);
        memoryLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        memoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        memoryLabel.setVerticalAlignment(SwingConstants.CENTER);

        mainMenu_Butt.setFont(new Font("Monospaced", Font.BOLD, 17));
        mainMenu_Butt.setBackground(new Color(130, 150, 140));
        mainMenu_Butt.setForeground(new Color(30,75,75));
        mainMenu_Butt.setFocusPainted(false);
        mainMenu_Butt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30,75,75), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        //layout
        this.layout.gridx = 0;
        this.layout.gridy = 0;
        this.layout.insets = new Insets(40, 10, 30, 10);
        this.layout.anchor = GridBagConstraints.CENTER;
        this.add(gameOverLabel, this.layout);

        this.layout.gridy = 1;
        this.layout.insets = new Insets(20, 40, 20, 40);
        this.layout.fill = GridBagConstraints.BOTH;
        this.layout.weighty = 1; // to ocupy central vertical space
        this.add(memoryLabel, this.layout);

        this.layout.gridy = 2;
        this.layout.insets = new Insets(20, 0, 60, 0);
        this.layout.fill = GridBagConstraints.NONE;
        this.layout.weighty = 0; //put at the extreme south 
        this.add(mainMenu_Butt, this.layout);

        this.bckgImage = ResourceHandler.loadImage(ResourcePaths.Images.GAMEOVER_PANEL_BCKG);
    }

    public void updatePanel() {

        //commemorative label
        int sec = Controller.getInstance().getTimePassed();
        String timeSurvived = formatTime(sec);
        double[] playerInfo = Controller.getInstance().getPlayerInfo();
        int level = (int)playerInfo[2];
        memoryLabel.setText("<html><center>" +
                            "\"Here lies a forgotten hero, entombed within this meager grave. <br/>" +
                            "Built in Aetherlum's darkest hours, when he perished by the hand of the Plague.<br/>" +
                            "Yet he did not fall in vain.<br/>" +
                            "Many were saved by his courage, for he held the horde at bay for <b>" + timeSurvived + "</b> minutes.<br/>" +
                            "And the <b>" + level + "</b> blessings granted him by the gods were not merely strength,<br/>" +
                            "but a light that inspired many.\"" +
                            "</center></html>");
    }

    //! UTILITIES
    public String formatTime(int seconds){
        long min = seconds / 60;
        long sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    //! TO PAINT BACKGROUND
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bckgImage != null) {
            g.drawImage(bckgImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
}
