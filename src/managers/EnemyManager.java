//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import enemies.Bat;
import enemies.Enemy;
import enemies.Knight;
import enemies.Orc;
import enemies.Wolf;
import helpz.LoadSave;
import helpz.Constants.Enemies;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import objects.PathPoint;
import scenes.Playing;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList();
    private PathPoint start;
    private PathPoint end;
    private int HPbarWidth = 20;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.enemyImgs = new BufferedImage[4];
        this.start = start;
        this.end = end;
        this.addEnemy(0);
        this.addEnemy(1);
        this.addEnemy(2);
        this.addEnemy(3);
        this.loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for(int i = 0; i < 4; ++i) {
            this.enemyImgs[i] = atlas.getSubimage(i * 32, 32, 32, 32);
        }

    }

    public void update() {
        updateWaveManager();

        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        for(Enemy e : this.enemies) {
            if (e.isAlive()) {
                this.updateEnemyMove(e);
            }
        }

    }

    private void updateWaveManager() {
        playing.getWaveManager().update();
    }

    private void spawnEnemy() {
        addEnemy(playing.getWaveManager().getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(playing.getWaveManager().isTimeForNewEnemy()){
            if(playing.getWaveManager().isThereMoreWaves())
                return true;
        }

        return false;
    }

    public void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            this.setNewDirectionAndMove(e);
        }

        int newX = (int)(e.getX() + this.getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + this.getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));
        if (this.getTileType(newX, newY) == 2) {
            e.move(Enemies.GetSpeed(e.getEnemyType()), e.getLastDir());
        } else if (!this.isAtEnd(e)) {
            this.setNewDirectionAndMove(e);
        }

    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();
        int xCord = (int)(e.getX() / 32.0F);
        int yCord = (int)(e.getY() / 32.0F);
        this.fixEnemyOffsetTile(e, dir, xCord, yCord);
        if (!this.isAtEnd(e)) {
            if (dir != 0 && dir != 2) {
                int newX = (int)(e.getX() + this.getSpeedAndWidth(2, e.getEnemyType()));
                if (this.getTileType(newX, (int)e.getY()) == 2) {
                    e.move(Enemies.GetSpeed(e.getEnemyType()), 2);
                } else {
                    e.move(Enemies.GetSpeed(e.getEnemyType()), 0);
                }
            } else {
                int newY = (int)(e.getY() + this.getSpeedAndHeight(1, e.getEnemyType()));
                if (this.getTileType((int)e.getX(), newY) == 2) {
                    e.move(Enemies.GetSpeed(e.getEnemyType()), 1);
                } else {
                    e.move(Enemies.GetSpeed(e.getEnemyType()), 3);
                }
            }

        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            case 2:
                if (xCord < 19) {
                    ++xCord;
                }
                break;
            case 3:
                if (yCord < 19) {
                    ++yCord;
                }
        }

        e.setPos(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {
        return e.getX() == (float)(this.end.getxCord() * 32) && e.getY() == (float)(this.end.getyCord() * 32);
    }

    private int getTileType(int x, int y) {
        return this.playing.getTileType(x, y);
    }

    private float getSpeedAndHeight(int dir, int enemyType) {
        if (dir == 1) {
            return -Enemies.GetSpeed(enemyType);
        } else {
            return dir == 3 ? Enemies.GetSpeed(enemyType) + 32.0F : 0.0F;
        }
    }

    private float getSpeedAndWidth(int dir, int enemyType) {
        if (dir == 0) {
            return -Enemies.GetSpeed(enemyType);
        } else {
            return dir == 2 ? Enemies.GetSpeed(enemyType) + 32.0F : 0.0F;
        }
    }

    public void addEnemy(int enemyType) {
        int x = this.start.getxCord() * 32;
        int y = this.start.getyCord() * 32;
        switch (enemyType) {
            case 0 -> this.enemies.add(new Orc((float)x, (float)y, 0));
            case 1 -> this.enemies.add(new Bat((float)x, (float)y, 0));
            case 2 -> this.enemies.add(new Knight((float)x, (float)y, 0));
            case 3 -> this.enemies.add(new Wolf((float)x, (float)y, 0));
        }

    }

    public void draw(Graphics g) {
        for(Enemy e : this.enemies) {
            if (e.isAlive()) {
                this.drawEnemy(e, g);
                this.drawHealthBar(e, g);
            }
        }

    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + 16 - this.getNewBarWidth(e) / 2, (int)e.getY() - 10, this.getNewBarWidth(e), 3);
    }

    private int getNewBarWidth(Enemy e) {
        return (int)((float)this.HPbarWidth * e.getHealthBarFloat());
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(this.enemyImgs[e.getEnemyType()], (int)e.getX(), (int)e.getY(), (ImageObserver)null);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}
