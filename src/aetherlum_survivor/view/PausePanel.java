package aetherlum_survivor.view;

import aetherlum_survivor.controller.Controller;
import aetherlum_survivor.util.EntityData;
import aetherlum_survivor.util.ResourcePaths;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PausePanel extends JPanel {

    private JButton settings_Butt;
    private JButton resume_Butt;
    private JButton interrupt_Butt;

    private GridBagConstraints layout;

    private BufferedImage bckgImage;

    //table
    private int level;
    private double currentHP;
    private double speed_upd; private double speed;
    private double maxHP_upd; private double maxHP;
    private double damage_upd; private double damage;
    private double dmgRes_upd; private double dmgRes;
    private double fireRate_upd; private double fireRate;

    private JScrollPane scrlLvlCHp;
    private JScrollPane scrlData;

    public PausePanel() {
        this.level = 1;
        this.speed = EntityData.PLAYER_SPD;
        this.maxHP = EntityData.PLAYER_MAX_HP; this.currentHP = this.maxHP;
        this.damage = EntityData.PLAYER_DMG; this.dmgRes = EntityData.PLAYER_DMG_RST;
        this.fireRate = EntityData.PLAYER_FIRE_RATE;

        setLayout(new GridBagLayout());
        this.layout = new GridBagConstraints();

        generateTables();

        //labels
        JLabel welcomeLabel = new JLabel("A fleeting interlude...get some rest while you have the chance.");
        welcomeLabel.setForeground(new Color(255,200,120));
        welcomeLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));

        this.layout.gridx = 0;
        this.layout.gridy = 0;
        this.layout.gridwidth = 2; 
        this.layout.insets = new Insets(20, 10, 30, 10);
        this.layout.anchor = GridBagConstraints.CENTER;
        this.add(welcomeLabel, this.layout);

        //buttons
        this.settings_Butt = new JButton("Settings");
        this.settings_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                View.getInstance().openSettingsPanel(); //TODO - differenciate if opened from Start or Pause to know where to return
			}
		});

        this.resume_Butt = new JButton("Resume Game");
        this.resume_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handleResumeGame();
            }
		});

        this.interrupt_Butt = new JButton("Quit Game");
        this.interrupt_Butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handleGameOver();
            }
		});


        // reset parameters for other elements
        layout.gridwidth = 1;
        layout.insets = new Insets(10, 20, 10, 20);
        layout.weightx = 1;

        layout.gridx = 0;
        layout.gridy = 3;
        layout.anchor = GridBagConstraints.CENTER;
        this.add(this.resume_Butt, layout);
        layout.gridx = 1;
        layout.gridy = 3;
        layout.anchor = GridBagConstraints.CENTER;
        this.add(this.interrupt_Butt, layout);
        layout.gridx = 0;
        layout.gridy = 4;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        this.add(this.settings_Butt, layout);

        //customize buttons
        resume_Butt.setBackground(new Color(50,50,50));
        resume_Butt.setForeground(Color.WHITE);
        resume_Butt.setFocusPainted(false);
        resume_Butt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resume_Butt.setPreferredSize(new Dimension(150,50));

        interrupt_Butt.setBackground(new Color(100,30,30));
        interrupt_Butt.setForeground(Color.WHITE);
        interrupt_Butt.setFocusPainted(false);
        interrupt_Butt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        interrupt_Butt.setPreferredSize(new Dimension(150,50));

        settings_Butt.setBackground(new Color(50,50,50));
        settings_Butt.setForeground(Color.LIGHT_GRAY);
        settings_Butt.setFocusPainted(false);
        settings_Butt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        settings_Butt.setPreferredSize(new Dimension(100,30));

        this.bckgImage = ResourceHandler.loadImage(ResourcePaths.Images.PAUSE_PANEL_BCKG);

    }

    public void updatePlayerData() {
        double[] plData = Controller.getInstance().getPlayerInfo();

        this.level = (int) plData[2];
        this.currentHP = plData[3];
        this.speed_upd = plData[5];
        this.maxHP_upd = plData[4];
        this.damage_upd = plData[6]; this.dmgRes_upd = plData[7];
        this.fireRate_upd = plData[8];

        remove(this.scrlLvlCHp);
        remove(this.scrlData);
        generateTables();

    }

    private void generateTables() {
        //currentHP and level table
        DefaultTableModel tabModel = new DefaultTableModel(new Object[]{"Level", "HP"},0);
        JTable playerLvlCurrentHPTable = new JTable(tabModel);
        tabModel.addRow(new Object[]{this.level, this.currentHP});
        //to show column names - also disable scrolling
        this.scrlLvlCHp = new JScrollPane(playerLvlCurrentHPTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        playerLvlCurrentHPTable.setPreferredScrollableViewportSize(playerLvlCurrentHPTable.getPreferredSize());

        //other datas
        JTable playerDataTable;
        if(this.level == 1) { //only one of data
            tabModel = new DefaultTableModel(new Object[]{"Statistic", "Value"},0);
            playerDataTable = new JTable(tabModel);

            tabModel.addRow(new Object[]{"Speed", this.speed});
            tabModel.addRow(new Object[]{"Max HP", this.maxHP});
            tabModel.addRow(new Object[]{"Damage", this.damage});
            tabModel.addRow(new Object[]{"Dmg Rst", this.dmgRes});
            tabModel.addRow(new Object[]{"Fire Rate", this.fireRate});
        } else {
            tabModel = new DefaultTableModel(new Object[]{"Statistic", "Original", "Enhanced"},0);
            playerDataTable = new JTable(tabModel);
            tabModel.addRow(new Object[]{"Speed", this.speed, this.speed_upd});
            tabModel.addRow(new Object[]{"Max HP", this.maxHP, this.maxHP_upd});
            tabModel.addRow(new Object[]{"Damage", this.damage, this.damage_upd});
            tabModel.addRow(new Object[]{"Dmg Rst", this.dmgRes, this.dmgRes_upd});
            tabModel.addRow(new Object[]{"Fire Rate", this.fireRate, this.fireRate_upd});
        }
        // tables customization
        customizeTables(playerLvlCurrentHPTable, playerDataTable);
        setColorIfEnhancedValueAndCenter(playerDataTable);
        setColorIfEnhancedValueAndCenter(playerLvlCurrentHPTable);

        //to show column names - also disable scrolling
        this.scrlData = new JScrollPane(playerDataTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        playerDataTable.setPreferredScrollableViewportSize(playerDataTable.getPreferredSize());

        //adds them to layout
        this.layout.gridx = 0;
        this.layout.gridy = 1;
        this.layout.gridwidth = 2;
        this.layout.insets = new Insets(10, 10, 10, 10);
        this.layout.anchor = GridBagConstraints.CENTER;
        this.add(scrlLvlCHp, layout);

        this.layout.gridx = 0;
        this.layout.gridy = 2;
        this.layout.gridwidth = 2;
        this.layout.insets = new Insets(10, 10, 30, 10);
        this.layout.anchor = GridBagConstraints.CENTER;
        this.add(scrlData, layout);

        //srollpane customization
        this.scrlLvlCHp.setOpaque(false);
        this.scrlLvlCHp.getViewport().setOpaque(false);
        this.scrlLvlCHp.setBorder(null);

        this.scrlData.setOpaque(false);
        this.scrlData.getViewport().setOpaque(false);
        this.scrlData.setBorder(null);
    }

    private void setColorIfEnhancedValueAndCenter(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override //modify base table's cells render
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                setHorizontalAlignment(SwingConstants.CENTER);

                if(table.getColumnCount() == 3) {
                    Object cell1 = table.getValueAt(row, 1);
                    Object cell2 = table.getValueAt(row, 2);
                    if (!cell1.equals(cell2)) {
                        setForeground(new Color(171,140,16));
                    } else {
                        setForeground(Color.WHITE);
                    }

                }

                return this;
            }
        });
    }

    private void customizeTables(JTable playerLvlCurrentHPTable, JTable playerDataTable) {
        playerLvlCurrentHPTable.setOpaque(false);
        playerLvlCurrentHPTable.setBackground(new Color(0,0,0,100));
        playerLvlCurrentHPTable.setForeground(new Color(171,140,16));
        playerLvlCurrentHPTable.setFont(new Font("Monospaced", Font.PLAIN, 12));
        playerLvlCurrentHPTable.setRowHeight(22);
        playerLvlCurrentHPTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        playerLvlCurrentHPTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        playerLvlCurrentHPTable.setShowGrid(false);
        playerLvlCurrentHPTable.setFocusable(false);
        playerLvlCurrentHPTable.setRowSelectionAllowed(false);
        JTableHeader headerLvlHP = playerLvlCurrentHPTable.getTableHeader();
        headerLvlHP.setBackground(new Color(30,30,30,200));
        headerLvlHP.setForeground(Color.ORANGE);
        headerLvlHP.setFont(new Font("Monospaced", Font.BOLD, 14));

        playerDataTable.setOpaque(false);
        playerDataTable.setBackground(new Color(0,0,0,100));
        playerDataTable.setForeground(Color.WHITE);
        playerDataTable.setFont(new Font("Monospaced", Font.PLAIN, 12));
        playerDataTable.setRowHeight(22);
        playerDataTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        playerDataTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        int columnCount = playerDataTable.getColumnCount();
        if(columnCount == 3) {
            playerDataTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        }
        playerDataTable.setShowGrid(false);
        playerDataTable.setIntercellSpacing(new Dimension(0, 0));
        playerDataTable.setFocusable(false);
        playerDataTable.setRowSelectionAllowed(false);
        JTableHeader headerData = playerDataTable.getTableHeader();
        headerData.setBackground(new Color(30,30,30,200));
        headerData.setForeground(Color.ORANGE);
        headerData.setFont(new Font("Monospaced", Font.BOLD, 14));
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
