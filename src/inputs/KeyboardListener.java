//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener {
    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (GameStates.gameState == GameStates.EDIT) {
            this.game.getEditor().keyPressed(e);
        } else if (GameStates.gameState == GameStates.PLAYING) {
            this.game.getPlaying().keyPressed(e);
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
