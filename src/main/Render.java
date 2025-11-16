//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.Graphics;

public class Render {
    private Game game;

    public Render(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        switch (GameStates.gameState) {
            case PLAYING -> this.game.getPlaying().render(g);
            case MENU -> this.game.getMenu().render(g);
            case SETTINGS -> this.game.getSettings().render(g);
            case EDIT -> this.game.getEditor().render(g);
            case GAME_OVER -> this.game.getGameOver().render(g);
        }

    }
}
