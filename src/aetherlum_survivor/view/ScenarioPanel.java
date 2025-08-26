package aetherlum_survivor.view;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aetherlum_survivor.controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//for the scenario selection
public class ScenarioPanel extends JPanel {

    private JButton scen1_Butt;
    private JButton scen2_Butt;
    private JButton scen3_Butt;

    private int scenario_selected_num;

    public ScenarioPanel() {
        this.setBackground(Color.gray);

        this.setPreferredSize(getPreferredSize());

        //off screen drawing buffer, then reported on screen - for rendering fluidity
        this.setDoubleBuffered(true);

        JLabel askLabel = new JLabel("Where have you been deployed?");
        //askLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel scen1_Label = new JLabel("<html><center>" +
                                        "A destroyed village, where only some<br/>" +
                                        "leftovers of the horde remains.<br/>" +
                                        "<b>Easy Scenario</b>" +
                                        "</center></html>");
        scen1_Label.setPreferredSize(new Dimension(200, 80));
        scen1_Label.setHorizontalAlignment(SwingConstants.CENTER);
        scen1_Label.setVerticalAlignment(SwingConstants.TOP);
        this.scen1_Butt = new JButton("The Abandoned Village");
        this.scen1_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 1;
				handleScenarioSelectedEvent();
			}
		});

        JLabel scen2_Label = new JLabel("<html><center>" +
                                        "Act as a bait for the horde and allow<br/>" +
                                        "the citizens to escape.<br/>" +
                                        "Your sacrifice will be remembered.<br/>" +
                                        "<b>Medium Scenario</b>" +
                                        "</center></html>");
        scen2_Label.setPreferredSize(new Dimension(200, 80));
        scen2_Label.setHorizontalAlignment(SwingConstants.CENTER);
        scen2_Label.setVerticalAlignment(SwingConstants.TOP);
        this.scen2_Butt = new JButton("The Falling City");
        this.scen2_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 2;
				handleScenarioSelectedEvent();
			}
		});

        JLabel scen3_Label = new JLabel("<html><center>" +
                                        "Face the heart of the horde.<br/>" +
                                        "<b>Hard Scenario</b>" +
                                        "</center></html>");
        scen3_Label.setPreferredSize(new Dimension(200, 80));
        scen3_Label.setHorizontalAlignment(SwingConstants.CENTER);
        scen3_Label.setVerticalAlignment(SwingConstants.TOP);
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
        layout.gridwidth = 3; // Si estende su 3 colonne
        layout.insets = new Insets(20, 10, 30, 10);
        layout.anchor = GridBagConstraints.CENTER;
        this.add(askLabel, layout);

        // Reset gridwidth per i componenti successivi
        layout.gridwidth = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        layout.anchor = GridBagConstraints.CENTER;

        // Prima colonna - Scenario 1
        layout.gridx = 0;
        layout.gridy = 1;
        this.add(this.scen1_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen1_Label, layout);

        // Seconda colonna - Scenario 2
        layout.gridx = 1;
        layout.gridy = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        this.add(this.scen2_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen2_Label, layout);

        // Terza colonna - Scenario 3
        layout.gridx = 2;
        layout.gridy = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        this.add(this.scen3_Butt, layout);

        layout.gridy = 2;
        layout.insets = new Insets(5, 20, 10, 20);
        this.add(scen3_Label, layout);
    }

    private void handleScenarioSelectedEvent() {
        Controller.getInstance().transmitScenarioToModel(scenario_selected_num);
        System.out.println(">> SelectedScenario: " + scenario_selected_num);
        
        Controller.getInstance().openGamePanel();
        System.out.println(">> ScenarioPanel --> GamePanel");

        Controller.getInstance().startGameLoop();
        System.out.println(">> New Game Started from Scenario Panel");
        
        //TODO to try if needed
        /*
        //to grant that UI is updated before starting newGame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(">> New Game Started from Start Panel");
                ControllerForView.getInstance().requestStartGameLoop();
            }
        });
        */
    }

    
}
