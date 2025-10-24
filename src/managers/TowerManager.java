//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import enemies.Enemy;
import helpz.LoadSave;
import helpz.Utilz;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import objects.Tower;
import scenes.Playing;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;
        this.loadTowerImgs();
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        this.towerImgs = new BufferedImage[3];

        for(int i = 0; i < 3; ++i) {
            this.towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }

    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        this.towers.add(new Tower(xPos, yPos, this.towerAmount++, selectedTower.getTowerType()));
    }

    public void update() {
        for(Tower t : this.towers) {
            t.update();
            this.attackEnemyIfClose(t);
        }

    }

    private void attackEnemyIfClose(Tower t) {
        for(Enemy e : this.playing.getEnemyManger().getEnemies()) {
            if (e.isAlive() && this.isEnemyInRange(t, e) && t.isCooldownOver()) {
                this.playing.shootEnemy(t, e);
                t.resetCooldown();
            }
        }

    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = Utilz.GetHypoDistance((float)t.getX(), (float)t.getY(), e.getX(), e.getY());
        return (float)range < t.getRange();
    }

    public void draw(Graphics g) {
        for(Tower t : this.towers) {
            g.drawImage(this.towerImgs[t.getTowerType()], t.getX(), t.getY(), (ImageObserver)null);
        }

    }

    public Tower getTowerAt(int x, int y) {
        for(Tower t : this.towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }

        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return this.towerImgs;
    }
}
