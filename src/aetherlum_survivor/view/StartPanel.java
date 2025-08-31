package aetherlum_survivor.view;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.ResourcePaths;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StartPanel extends JPanel {

    private JButton newGameButton;
    private JButton settingsMenuButton;

    //background data
    BufferedImage bckgImage;

    //! CONSTRUCTOR
    public StartPanel() {
        this.setPreferredSize(getPreferredSize());

        //off screen drawing buffer, then reported on screen - for rendering fluidity
        this.setDoubleBuffered(true);

        this.newGameButton = new JButton("Try to Survive");
        this.newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleNewGameEvent();
			}
		});
        JLabel newGameLabel = new JLabel("<html><center>" +
                                        "\"We will give rest to their haunted souls.\"<br/>" +
                                        "<b>Are you ready to face the Plague?</b>" +
                                        "</center></html>");
        newGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newGameLabel.setFont(new Font("Monospaced", Font.ITALIC, 18));
        newGameLabel.setForeground(new Color(180, 220, 150)); //light green
        newGameLabel.setOpaque(true);
        newGameLabel.setBackground(new Color(0, 0, 0, 200)); // black, semitransparent


        this.settingsMenuButton = new JButton("Settings");
        this.settingsMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSettingsMenuEvent();
			}
		});

        JLabel titleLabel = new JLabel("AETHERLUM SURVIVOR");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 48));
        titleLabel.setForeground(new Color(220, 255, 180)); // similar to the ghosts
        //titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); //extra border
        
        //define menu layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints(); //to determine element location on grid
        layout.insets = new Insets(15, 30, 15, 30); // space between elements
        //title
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.HORIZONTAL; //extends text and area
        this.add(titleLabel, layout);
        //newgame
        layout.gridy = 1;
        layout.insets = new Insets(5, 30, 25, 30);
        layout.fill = GridBagConstraints.BOTH;
        this.add(newGameLabel, layout);
        layout.gridy = 2;
        layout.insets = new Insets(10, 50, 10, 50);
        layout.ipadx = 20; // to add dimension
        this.add(this.newGameButton, layout);
        //settings
        layout.gridy = 3;
        layout.insets = new Insets(5, 80, 5, 80);
        layout.fill = GridBagConstraints.NONE;
        //layout.ipady = 10;
        this.add(this.settingsMenuButton, layout);

        //set custom elements aspect
        //to assign buttons custom aspect
        JButton[] buttons = {newGameButton, settingsMenuButton};
        for (JButton b : buttons) {
            b.setFocusPainted(false); //removes sub-rectangle on the button that shows it is clickable
            b.setOpaque(true);

            b.setFont(new Font("Monospaced", Font.BOLD, 16));
            b.setForeground(new Color(240, 255, 220)); // Light green color
            b.setBackground(new Color(10,10,10)); //transparent black
            
            //border with glowing effect
            b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 220, 100, 180), 2, true),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
            ));
            
        }

        //load image and scale to fit JPanel dimension
        this.bckgImage = ResourceHandler.loadImage(ResourcePaths.Images.START_PANEL_BCKG);
        
    }

    //! TO PAINT BACKGROUND
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (bckgImage != null) {
            g.drawImage(bckgImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    //! BUTTON EVENT HANDLERS
    private void handleNewGameEvent() {
        Controller.getInstance().openScenarioPanel();
        
        //System.out.println(">> StartPanel --> ScenarioPanel");
    }
    
    private void handleSettingsMenuEvent() {
        Controller.getInstance().openSettingsPanel();

        //System.out.println(">> Settings Menu Opened from Main Menu");
    }

    
}
