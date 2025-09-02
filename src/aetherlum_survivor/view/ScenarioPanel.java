package aetherlum_survivor.view;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.ResourcePaths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

//for the scenario selection
public class ScenarioPanel extends JPanel {

    private JButton scen1_Butt;
    private JButton scen2_Butt;
    private JButton scen3_Butt;

    private BufferedImage bckgImage;

    private int scenario_selected_num;

    public ScenarioPanel() {
        this.setBackground(Color.gray);

        this.setPreferredSize(getPreferredSize());

        //off screen drawing buffer, then reported on screen - for rendering fluidity
        this.setDoubleBuffered(true);

        JLabel askLabel = new JLabel("Where have you been deployed?");
        askLabel.setHorizontalAlignment(SwingConstants.CENTER);
        askLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        askLabel.setForeground(new Color(255, 215, 0));
        askLabel.setOpaque(true);
        askLabel.setBackground(new Color(250, 220, 110, 50)); 
        askLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        askLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230,220,180, 180), 2, false),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));

        JLabel scen1_Label = new JLabel("<html><center>" +
                                        "A destroyed village,<br/>" +
                                        "where only some<br/>" +
                                        "leftovers of the horde remains.<br/><br/>" +
                                        "<b><i>EASY SCENARIO</i></b>" +
                                        "</center></html>");
        scen1_Label.setPreferredSize(new Dimension(200, 80));
        
        this.scen1_Butt = new JButton("The Abandoned Village");
        this.scen1_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 1;
				handleScenarioSelectedEvent();
			}
		});

        JLabel scen2_Label = new JLabel("<html><center>" +
                                        "Act as a bait for the horde <br/>" +
                                        "allowing the citizens to escape.<br/>" +
                                        "Your sacrifice will be remembered.<br/><br/>" +
                                        "<b><i>MEDIUM SCENARIO</i></b>" +
                                        "</center></html>");
        scen2_Label.setPreferredSize(new Dimension(200, 80));
        this.scen2_Butt = new JButton("The Falling City");
        this.scen2_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 2;
				handleScenarioSelectedEvent();
			}
		});

        JLabel scen3_Label = new JLabel("<html><center>" +
                                        "Face the heart of the corruption.<br/>" +
                                        "Step on those twisted roots and attract their attention.<br/><br/>" +
                                        "<b><i>HARD SCENARIO</i></b>" +
                                        "</center></html>");
        scen3_Label.setPreferredSize(new Dimension(200, 80));
        this.scen3_Butt = new JButton("The Tainted Valley");
        this.scen3_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 3;
				handleScenarioSelectedEvent();
			}
		});

        //define menu layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints(); //to determine element location on grid

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 3; // extends on 3 columns
        layout.insets = new Insets(20, 10, 30, 10);
        layout.anchor = GridBagConstraints.CENTER;
        this.add(askLabel, layout);

        // Reset gridwidth for successive components
        layout.gridwidth = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        layout.anchor = GridBagConstraints.CENTER;

        // 1st column - scenario 1
        layout.gridx = 0;
        layout.gridy = 1;
        this.add(this.scen1_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen1_Label, layout);

        // 2nd column - scenario 2
        layout.gridx = 1;
        layout.gridy = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        this.add(this.scen2_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen2_Label, layout);

        // 3rd column - scenario 3
        layout.gridx = 2;
        layout.gridy = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        this.add(this.scen3_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen3_Label, layout);

        //to assign custom aspects
        JLabel[] labels = {scen1_Label, scen2_Label, scen3_Label};
        for (JLabel l : labels) {
            l.setHorizontalAlignment(SwingConstants.CENTER);
            l.setVerticalAlignment(SwingConstants.CENTER);

            l.setFont(new Font("SansSerif", Font.ITALIC, 11));
            l.setForeground(new Color(180, 220, 150)); //light green
            l.setOpaque(true);
            l.setBackground(new Color(0, 0, 0, 200)); // black, semitransparent
        }
        JButton[] buttons = {scen1_Butt, scen2_Butt, scen3_Butt};
        for (JButton b : buttons) {
            b.setFocusPainted(false); //removes sub-rectangle on the button that shows it is clickable
            b.setBackground(new Color(0,0,0));
            b.setForeground(new Color(180, 220, 150));
            b.setFont(new Font("Monospaced", Font.BOLD, 14));

            b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(240, 255, 220, 180), 1, false),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)
            ));
        }

        this.bckgImage = ResourceHandler.loadImage(ResourcePaths.Images.SCENARIO_PANEL_BCKG);
    }

    private void handleScenarioSelectedEvent() {
        Controller.getInstance().transmitScenarioToModel(scenario_selected_num);
        View.getInstance().transmitScenarioToGamePanel(scenario_selected_num);

        Controller.getInstance().openGamePanel();
        Controller.getInstance().startGameLoop();
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
