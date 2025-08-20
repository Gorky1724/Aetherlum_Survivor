package aetherlum_survivor.view;

import javax.swing.JPanel;

public interface InterfaceView {

    public void openGameFrame();

    public void openStartPanel();

    public void openSettingsPanel();
    
    public void openGamePanel();

    public void switchToPanel(JPanel newPanel);
    
}
