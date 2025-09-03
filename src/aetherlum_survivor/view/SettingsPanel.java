package aetherlum_survivor.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aetherlum_survivor.util.Constants;
import aetherlum_survivor.util.ResourcePaths;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage; 
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

    private JButton audio_Butt;

    private BufferedImage bckgImage; 

    private boolean audioEnabled;
    private String openedFrom; //for the back() method

    public SettingsPanel() {
        
        JLabel settingsLabel = new JLabel("SETTINGS");
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        settingsLabel.setForeground(new Color(200, 150, 100));

        this.audio_Butt = new JButton("Audio: ON");
        this.audioEnabled = true;
        this.audio_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleAudio();
			}
		});

        JButton back_Butt = new JButton("Back");
        back_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});

        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(40, 10, 30, 10);
        layout.weighty = 0.15;
        this.add(settingsLabel, layout);

        layout.gridy = 1;
        layout.insets = new Insets(20, 40, 20, 40);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.weighty = 0.7;
        this.add(this.audio_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(20, 0, 60, 0);
        layout.fill = GridBagConstraints.NONE;
        layout.weighty = 0.15;
        this.add(back_Butt, layout);

        //buttons customization
        this.audio_Butt.setFocusPainted(false);
        this.audio_Butt.setOpaque(true);
        this.audio_Butt.setFont(new Font("Monospaced", Font.BOLD, 17));
        this.audio_Butt.setForeground(new Color(20, 10, 10));
        this.audio_Butt.setBackground(new Color(200, 150, 100));
        this.audio_Butt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(20, 10, 10, 180), 2, false),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        back_Butt.setFocusPainted(false);
        back_Butt.setOpaque(true);
        back_Butt.setFont(new Font("Monospaced", Font.BOLD, 15));
        back_Butt.setForeground(new Color(200, 150, 100));
        back_Butt.setBackground(new Color(15, 10, 10));
        back_Butt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 150, 100), 3, false),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        this.bckgImage = ResourceHandler.loadImage(ResourcePaths.Images.SETTINGS_PANEL_BCKG);
    }

    //! BUTTON METHODS
    private void toggleAudio() {
        //View.getInstance().setAudioStatus(audioEnabled);
    }

    private void back() { //back to panel where settings where opened from
        if(openedFrom == Constants.START_PANEL) {
            View.getInstance().openStartPanel();
        } else if(openedFrom == Constants.PAUSE_PANEL) {
            View.getInstance().openPausePanel();
        }
    }

    //! UTILITIES
    public void setParentPanel(String openedFrom) {
        this.openedFrom = openedFrom;
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
