package aetherlum_survivor.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import aetherlum_survivor.util.LevelUpData.LevelUpOptions;
import aetherlum_survivor.controller.Controller;

public class LevelUpPanel extends JPanel {
    
    private JButton powUp1_Butt;
    private JButton powUp2_Butt;
    private JButton powUp3_Butt;

    private Map<Integer, LevelUpOptions> powerUpsAvailable;

    private int codeOfSelectedPowUp;
    private LevelUpOptions powerUpSelected;

    public LevelUpPanel() {
        this.setBackground(new Color(120,98,10));

        this.setPreferredSize(getPreferredSize());

        //off screen drawing buffer, then reported on screen - for rendering fluidity
        this.setDoubleBuffered(true);

        JLabel askLabel = new JLabel("Choose your upgrade:");
        askLabel.setOpaque(true);
        askLabel.setBackground(new Color(0, 0, 0, 150)); // black, semitransparent
        askLabel.setForeground(Color.WHITE); //text color
        askLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); //extends dimension to extend background
        askLabel.setHorizontalAlignment(SwingConstants.CENTER);
        askLabel.setFont(new Font("Serif", Font.BOLD, 14));

        powUp1_Butt = new JButton("LOADING UPGRADE AVAILABLE");
        powUp2_Butt = new JButton("LOADING UPGRADE AVAILABLE");
        powUp3_Butt = new JButton("LOADING UPGRADE AVAILABLE");

        JButton[] buttons = {powUp1_Butt, powUp2_Butt, powUp3_Butt};
        //to assign buttons custom aspect
        for (JButton b : buttons) {
            b.setBackground(new Color(0,0,0));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false); //removes sub-rectangle on the button that shows it is clickable
            b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //more space if the inserted text needs to occupy it and resize button
            b.setFont(new Font("Serif", Font.ITALIC, 14));
        }

        //define menu layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        // centered on top
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2; 
        layout.insets = new Insets(20, 10, 30, 10);
        layout.anchor = GridBagConstraints.CENTER;
        this.add(askLabel, layout);

        // reset parameters for other elements
        layout.gridwidth = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        layout.anchor = GridBagConstraints.CENTER;

        // powerButt1 - top left
        layout.gridx = 0;
        layout.gridy = 1;
        this.add(this.powUp1_Butt, layout);

        // powerButt2 - top right
        layout.gridx = 1;
        layout.gridy = 1;
        this.add(this.powUp2_Butt, layout);

        // powerButt3 - bottom center
        layout.gridx = 0;
        layout.gridy = 2;
        layout.gridwidth = 2; // takes both columns becoming centered
        this.add(this.powUp3_Butt, layout);
    }

    public void setup(Map<Integer, LevelUpOptions> randomLvlUp) {
        
        this.powerUpsAvailable = randomLvlUp;
        List<Integer> codes = new ArrayList<>(powerUpsAvailable.keySet());

        //associates action listener
        JButton[] buttons = {powUp1_Butt, powUp2_Butt, powUp3_Butt};
        
        for (int i = 0; i < buttons.length && i < codes.size(); i++) {

            //removes precedent actionListeners from button
            for (ActionListener al : buttons[i].getActionListeners()) {
                buttons[i].removeActionListener(al);
            }

            int code = codes.get(i);
            buttons[i].setText(this.powerUpsAvailable.get(code).description);
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    codeOfSelectedPowUp = code;
                    handlePowerSelectedEvent();
                }
            });
        }
        
    }

    public void handlePowerSelectedEvent() {

        //assign powerUpSelected
        this.powerUpSelected = this.powerUpsAvailable.get(this.codeOfSelectedPowUp);

        //System.out.println("codeOfSelectedPowUp: " + this.codeOfSelectedPowUp);
        //System.out.println("powerUpSelected: " + this.powerUpSelected);

        Map<Integer, LevelUpOptions> dataPassed = new HashMap<>(Map.of(this.codeOfSelectedPowUp, this.powerUpSelected));

        Controller.getInstance().upgradePlayer(dataPassed);
        Controller.getInstance().resumeGameLoop();
        Controller.getInstance().openGamePanel();
    }
}
