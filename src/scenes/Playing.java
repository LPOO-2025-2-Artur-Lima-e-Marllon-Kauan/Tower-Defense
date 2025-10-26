//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import enemies.Enemy;
import helpz.LoadSave;
import helpz.Constants.Enemies;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX;
    private int mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projManager;
    private WaveManager waveManager;
    private PathPoint start;
    private PathPoint end;
    private Tower selectedTower;
    private int goldTick;

    public Playing(Game game) {
        super(game);
        this.loadDefaultLevel();
        this.actionBar = new ActionBar(0, 640, 640, 160, this);
        this.enemyManager = new EnemyManager(this, this.start, this.end);
        this.towerManager = new TowerManager(this);
        this.projManager = new ProjectileManager(this);
        this.waveManager = new WaveManager(this);
    }

    private void loadDefaultLevel() {
        this.lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        this.start = (PathPoint)points.get(0);
        this.end = (PathPoint)points.get(1);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void update() {
        this.updateTick();
        this.waveManager.update();
        ++this.goldTick;
        if (this.goldTick % 180 == 0) {
            this.actionBar.addGold(1);
        }

        if (this.isAllEnemiesDead() && this.isThereMoreWaves()) {
            this.waveManager.startWaveTimer();
            if (this.isWaveTimerOver()) {
                this.waveManager.increaseWaveIndex();
                this.enemyManager.getEnemies().clear();
                this.waveManager.resetEnemyIndex();
            }
        }

        if (this.isTimeForNewEnemy()) {
            this.spawnEnemy();
        }

        this.enemyManager.update();
        this.towerManager.update();
        this.projManager.update();
    }

    private boolean isWaveTimerOver() {
        return this.waveManager.isWaveTimerOver();
    }

    private boolean isThereMoreWaves() {
        return this.waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {
        if (this.waveManager.isThereMoreEnemiesInWave()) {
            return false;
        } else {
            for(Enemy e : this.enemyManager.getEnemies()) {
                if (e.isAlive()) {
                    return false;
                }
            }

            return true;
        }
    }

    private void spawnEnemy() {
        this.enemyManager.spawnEnemy(this.waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        return this.waveManager.isTimeForNewEnemy() && this.waveManager.isThereMoreEnemiesInWave();
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void render(Graphics g) {
        this.drawLevel(g);
        this.actionBar.draw(g);
        this.enemyManager.draw(g);
        this.towerManager.draw(g);
        this.projManager.draw(g);
        this.drawSelectedTower(g);
        this.drawHighlight(g);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(this.mouseX, this.mouseY, 32, 32);
    }

    private void drawSelectedTower(Graphics g) {
        if (this.selectedTower != null) {
            g.drawImage(this.towerManager.getTowerImgs()[this.selectedTower.getTowerType()], this.mouseX, this.mouseY, (ImageObserver)null);
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

    public int getTileType(int x, int y) {
        int xCord = x / 32;
        int yCord = y / 32;
        if (xCord >= 0 && xCord <= 19) {
            if (yCord >= 0 && yCord <= 19) {
                int id = this.lvl[y / 32][x / 32];
                return this.game.getTileManager().getTile(id).getTileType();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            this.actionBar.mouseClicked(x, y);
        } else if (this.selectedTower != null) {
            if (this.isTileGrass(this.mouseX, this.mouseY) && this.getTowerAt(this.mouseX, this.mouseY) == null) {
                this.towerManager.addTower(this.selectedTower, this.mouseX, this.mouseY);
                this.removeGold(this.selectedTower.getTowerType());
                this.selectedTower = null;
            }
        } else {
            Tower t = this.getTowerAt(this.mouseX, this.mouseY);
            this.actionBar.displayTower(t);
        }

    }

    private void removeGold(int towerType) {
        this.actionBar.payForTower(towerType);
    }

    private Tower getTowerAt(int x, int y) {
        return this.towerManager.getTowerAt(x, y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = this.lvl[y / 32][x / 32];
        int tileType = this.game.getTileManager().getTile(id).getTileType();
        return tileType == 1;
    }

    public void shootEnemy(Tower t, Enemy e) {
        this.projManager.newProjectile(t, e);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            this.selectedTower = null;
        }

    }

    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            this.actionBar.mouseMoved(x, y);
        } else {
            this.mouseX = x / 32 * 32;
            this.mouseY = y / 32 * 32;
        }

    }

    public void mousePressed(int x, int y) {
        if (y >= 640) {
            this.actionBar.mousePressed(x, y);
        }

    }

    public void mouseReleased(int x, int y) {
        this.actionBar.mouseReleased(x, y);
    }

    public void mouseDragged(int x, int y) {
    }

    public void rewardPlayer(int enemyType) {
        this.actionBar.addGold(Enemies.GetReward(enemyType));
    }

    // Called when an enemy reaches the end of the path
    public void enemyEscaped() {
        // On first escape, the player loses the game
        main.GameStates.SetGameState(main.GameStates.MENU);
    }

    public TowerManager getTowerManager() {
        return this.towerManager;
    }

    public EnemyManager getEnemyManger() {
        return this.enemyManager;
    }

    public WaveManager getWaveManager() {
        return this.waveManager;
    }
}
