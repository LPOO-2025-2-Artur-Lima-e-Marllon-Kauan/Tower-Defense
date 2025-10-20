//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.Toolbar;

public class Editing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX;
    private int mouseY;
    private int lastTileX;
    private int lastTileY;
    private int lastTileId;
    private boolean drawSelect;
    private Toolbar toolbar;
    private PathPoint start;
    private PathPoint end;

    public Editing(Game game) {
        super(game);
        this.loadDefaultLevel();
        this.toolbar = new Toolbar(0, 640, 640, 160, this);
    }

    private void loadDefaultLevel() {
        this.lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        this.start = (PathPoint)points.get(0);
        this.end = (PathPoint)points.get(1);
    }

    public void update() {
        this.updateTick();
    }

    public void render(Graphics g) {
        this.drawLevel(g);
        this.toolbar.draw(g);
        this.drawSelectedTile(g);
        this.drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if (this.start != null) {
            g.drawImage(this.toolbar.getStartPathImg(), this.start.getxCord() * 32, this.start.getyCord() * 32, 32, 32, (ImageObserver)null);
        }

        if (this.end != null) {
            g.drawImage(this.toolbar.getEndPathImg(), this.end.getxCord() * 32, this.end.getyCord() * 32, 32, 32, (ImageObserver)null);
        }

    }

    private void drawLevel(Graphics g) {
        for(int y = 0; y < this.lvl.length; ++y) {
            for(int x = 0; x < this.lvl[y].length; ++x) {
                int id = this.lvl[y][x];
                if (this.isAnimation(id)) {
                    g.drawImage(this.getSprite(id, this.animationIndex), x * 32, y * 32, (ImageObserver)null);
                } else {
                    g.drawImage(this.getSprite(id), x * 32, y * 32, (ImageObserver)null);
                }
            }
        }

    }

    private void drawSelectedTile(Graphics g) {
        if (this.selectedTile != null && this.drawSelect) {
            g.drawImage(this.selectedTile.getSprite(), this.mouseX, this.mouseY, 32, 32, (ImageObserver)null);
        }

    }

    public void saveLevel() {
        LoadSave.SaveLevel("new_level", this.lvl, this.start, this.end);
        this.game.getPlaying().setLevel(this.lvl);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        this.drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if (this.selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            if (this.selectedTile.getId() >= 0) {
                if (this.lastTileX == tileX && this.lastTileY == tileY && this.lastTileId == this.selectedTile.getId()) {
                    return;
                }

                this.lastTileX = tileX;
                this.lastTileY = tileY;
                this.lastTileId = this.selectedTile.getId();
                this.lvl[tileY][tileX] = this.selectedTile.getId();
            } else {
                int id = this.lvl[tileY][tileX];
                if (this.game.getTileManager().getTile(id).getTileType() == 2) {
                    if (this.selectedTile.getId() == -1) {
                        this.start = new PathPoint(tileX, tileY);
                    } else {
                        this.end = new PathPoint(tileX, tileY);
                    }
                }
            }
        }

    }

    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            this.toolbar.mouseClicked(x, y);
        } else {
            this.changeTile(this.mouseX, this.mouseY);
        }

    }

    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            this.toolbar.mouseMoved(x, y);
            this.drawSelect = false;
        } else {
            this.drawSelect = true;
            this.mouseX = x / 32 * 32;
            this.mouseY = y / 32 * 32;
        }

    }

    public void mousePressed(int x, int y) {
        if (y >= 640) {
            this.toolbar.mousePressed(x, y);
        }

    }

    public void mouseReleased(int x, int y) {
        this.toolbar.mouseReleased(x, y);
    }

    public void mouseDragged(int x, int y) {
        if (y < 640) {
            this.changeTile(x, y);
        }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 82) {
            this.toolbar.rotateSprite();
        }

    }
}
