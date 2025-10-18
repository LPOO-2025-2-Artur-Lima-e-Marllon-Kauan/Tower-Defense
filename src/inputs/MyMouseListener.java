//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Main.Game;
import Main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private Game game;

    public MyMouseListener(Game game) {
        this.game = game;
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING -> this.game.getPlaying().mouseMoved(e.getX(), e.getY());
            case MENU -> this.game.getMenu().mouseMoved(e.getX(), e.getY());
            case SETTINGS -> this.game.getSettings().mouseMoved(e.getX(), e.getY());
        }

    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            switch (GameStates.gameState) {
                case PLAYING -> this.game.getPlaying().mouseClicked(e.getX(), e.getY());
                case MENU -> this.game.getMenu().mouseClicked(e.getX(), e.getY());
                case SETTINGS -> this.game.getSettings().mouseClicked(e.getX(), e.getY());
            }
        }

    }

    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING -> this.game.getPlaying().mousePressed(e.getX(), e.getY());
            case MENU -> this.game.getMenu().mousePressed(e.getX(), e.getY());
            case SETTINGS -> this.game.getSettings().mousePressed(e.getX(), e.getY());
        }

    }

    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING -> this.game.getPlaying().mouseReleased(e.getX(), e.getY());
            case MENU -> this.game.getMenu().mouseReleased(e.getX(), e.getY());
            case SETTINGS -> this.game.getSettings().mouseReleased(e.getX(), e.getY());
        }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
