package inputs;

import Main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Main.GameStates.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A)
            GameStates.gameStates = MENU;

        else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameStates = PLAYING;
        }

        else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gameStates = SETTINGS;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
