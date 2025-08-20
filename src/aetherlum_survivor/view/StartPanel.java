package aetherlum_survivor.view;

import aetherlum_survivor.controller.ControllerForView;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class StartPanel extends JPanel {

    private JButton newGameButton;
    private JButton settingsMenuButton;

    private final int SPANEL_WIDTH = 250;
    private final int SPANEL_HEIGHT = 500;

    //layout data
    private final int TOP_INS = 10; //pixels
    private final int LFT_INS = 10;
    private final int BTM_INS = 10;
    private final int RGT_INS = 10;
    private final int X0 = 0;
    private final int Y0 = 0;
    private final int Y1 = 1;
    private final int Y2 = 2;

    public StartPanel() {
        this.setPreferredSize(new Dimension(SPANEL_WIDTH, SPANEL_HEIGHT));
        //this.setPreferredSize(getPreferredSize());

        //buffer di disegno off-screen poi riportato sullo schermo - per fluidità di rendering
        this.setDoubleBuffered(true);

        this.newGameButton = new JButton("Try to Survive");
        this.newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleNewGameEvent();
			}
		});
        JLabel newGameLabel = new JLabel("Are you ready to face the Plague?");
        newGameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.settingsMenuButton = new JButton("Settings");
        this.settingsMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSettingsMenuEvent();
			}
		});
        
        //define menu layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints(); //to determine element location on grid
        layout.insets = new Insets(TOP_INS, LFT_INS, BTM_INS, RGT_INS); // space between elements

        layout.gridx = X0;
        layout.gridy = Y0;
        this.add(newGameLabel, layout);

        layout.gridy = Y1; // change to next row
        this.add(this.newGameButton, layout);

        layout.gridy = Y2; // change to next row
        this.add(this.settingsMenuButton, layout);
    }

    private void handleNewGameEvent() {
        ControllerForView.getInstance().openGamePanel();
        System.out.println(">> StartPanel --> GamePanel");

        //to grant that UI is updated before starting newGame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(">> New Game Started from Start Panel");
                ControllerForView.getInstance().requestStartGameLoop();
            }
        });
        
    }

    private void handleSettingsMenuEvent() {
        ControllerForView.getInstance().openSettingsPanel();

        System.out.println(">> Settings Menu Opened from Main Menu");
    }

    
}
