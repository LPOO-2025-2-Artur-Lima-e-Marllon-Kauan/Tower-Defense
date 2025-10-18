//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import main.Game;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX;
    private int mouseY;

    public Playing(Game game) {
        super(game);
        this.loadDefaultLevel();
        this.bottomBar = new ActionBar(0, 640, 640, 100, this);
    }

    private void loadDefaultLevel() {
        this.lvl = LoadSave.GetLevelData("new_level");
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void render(Graphics g) {

        this.drawLevel(g);
        this.bottomBar.draw(g);
    }

    private void drawLevel(Graphics g) {
        for(int y = 0; y < this.lvl.length; ++y) {
            for(int x = 0; x < this.lvl[y].length; ++x) {
                int id = this.lvl[y][x];
                g.drawImage(this.getSprite(id), x * 32, y * 32, (ImageObserver)null);
            }
        }

    }

    private BufferedImage getSprite(int spriteID) {
        return this.game.getTileManager().getSprite(spriteID);
    }

    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            this.bottomBar.mouseClicked(x, y);
        }

    }

    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            this.bottomBar.mouseMoved(x, y);
        } else {
            this.mouseX = x / 32 * 32;
            this.mouseY = y / 32 * 32;
        }

    }

    public void mousePressed(int x, int y) {
        if (y >= 640) {
            this.bottomBar.mousePressed(x, y);
        }

    }

    public void mouseReleased(int x, int y) {
        this.bottomBar.mouseReleased(x, y);
    }

    public void mouseDragged(int x, int y) {
    }
}
