//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import helpz.LevelBuild;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import Main.Game;
import Main.GameStates;
import managers.TileManager;
import ui.MyButton;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    private MyButton bMenu;

    public Playing(Game game) {
        super(game);
        this.initButtons();
        this.lvl = LevelBuild.getLevelData();
        this.tileManager = new TileManager();
    }

    private void initButtons() {
        this.bMenu = new MyButton("Menu", 2, 2, 100, 30);
    }

    public void render(Graphics g) {
        for(int y = 0; y < this.lvl.length; ++y) {
            for(int x = 0; x < this.lvl[y].length; ++x) {
                int id = this.lvl[y][x];
                g.drawImage(this.tileManager.getSprite(id), x * 32, y * 32, (ImageObserver)null);
            }
        }

        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        this.bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.MENU);
        }

    }

    public void mouseMoved(int x, int y) {
        this.bMenu.setMouseOver(false);
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMouseOver(true);
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMousePressed(true);
        }

    }

    public void mouseReleased(int x, int y) {
        this.bMenu.resetBooleans();
    }
}
