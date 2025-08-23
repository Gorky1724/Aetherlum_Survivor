package aetherlum_survivor.view;

import javax.swing.JPanel;

import aetherlum_survivor.controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

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

    //layout coords
    //layout data
    private final int TOP_INS = 10; //pixels
    private final int LFT_INS = 10;
    private final int BTM_INS = 10;
    private final int RGT_INS = 10;
    private final int X0 = 0;
    private final int X1 = 1;
    private final int X2 = 2;
    private final int Y0 = 0;
    private final int Y1 = 1;
    private final int Y2 = 2;
    private final int WDTH = 3;

    public ScenarioPanel() {
        this.setBackground(Color.gray);

        this.setPreferredSize(getPreferredSize());

        //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
        this.setDoubleBuffered(true);

        JLabel askLabel = new JLabel("Where have you been deployed?");
        //askLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel scen1_Label = new JLabel("A destroyed village, where only some leftovers of the horde remains. \n Easy Scenario.");
        //scen1_Label.setHorizontalAlignment(SwingConstants.CENTER);
        this.scen1_Butt = new JButton("The Abandoned Village");
        this.scen1_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 1;
				handleScenarioSelectedEvent();
			}
		});

        // TODO:
        /* add single ActionListener with a scenario selected associated for each.
           Than the selected scenario must be reported to the model
           The model will tell this info to Enemies (to have the right amount and type of enemies to spawn via the method) - to implement
           and print the right background basing on this info
         */

        JLabel scen2_Label = new JLabel("Act as a bait for the horde and allow the citizens to escape. \n Your sacrifice will be remembered \n Medium Scenario.");
        //scen2_Label.setHorizontalAlignment(SwingConstants.CENTER);
        this.scen2_Butt = new JButton("The Falling City");
        this.scen2_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                scenario_selected_num = 2;
				handleScenarioSelectedEvent();
			}
		});

        JLabel scen3_Label = new JLabel("Face the heart of the horde. \n Hard Scenario.");
        //scen3_Label.setHorizontalAlignment(SwingConstants.CENTER);
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
        layout.insets = new Insets(TOP_INS, LFT_INS, BTM_INS, RGT_INS); // space between elements

        layout.gridx = X1;
        layout.gridy = Y0;
        layout.gridwidth = WDTH;
        this.add(askLabel, layout);

        layout.gridx = X0;
        layout.gridy = Y1;
        this.add(this.scen1_Butt, layout);
        layout.gridx = X0;
        layout.gridy = Y2;
        this.add(scen1_Label, layout);

        layout.gridx = X1;
        layout.gridy = Y1;
        this.add(this.scen2_Butt, layout);
        layout.gridx = X1;
        layout.gridy = Y2;
        this.add(scen2_Label, layout);

        layout.gridx = X2;
        layout.gridy = Y1;
        this.add(this.scen3_Butt, layout);
        layout.gridx = X2;
        layout.gridy = Y2;
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
