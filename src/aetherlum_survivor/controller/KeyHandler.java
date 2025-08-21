package aetherlum_survivor.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements InterfaceKeyHandler, KeyListener{

    //---------------------------------------------------------------
	//! PRIVATE ATTRIBUTES

    private static KeyHandler instance = null;

    private boolean up_pressed = false;
    private boolean right_pressed = false;
    private boolean down_pressed = false;
    private boolean left_pressed = false;
	
    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! PUBLIC METHODS

    // GETTERS_____________________________
    @Override
    public boolean getUpPressed() {
        return this.up_pressed;    
    }
    @Override
    public boolean getRightPressed() {
        return this.right_pressed;    
    }
    @Override
    public boolean getDownPressed() {
        return this.down_pressed;    
    }
    @Override
    public boolean getLeftPressed() {
        return this.left_pressed;    
    }

    // KEY INPUT_____________________________
    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //for each pression, saves the direction moved
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.up_pressed = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.right_pressed = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.down_pressed = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.left_pressed = true;
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.up_pressed = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.right_pressed = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.down_pressed = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.left_pressed = false;
                break;
        }
    }

    //---------------------------------------------------------------

    //---------------------------------------------------------------
	//! STATIC METHODS

    //* singleton pattern
    public static InterfaceKeyHandler getInstance() {
        if(instance == null) {
            instance = new KeyHandler();
        }
        return instance;

    }

	//---------------------------------------------------------------
    
}
