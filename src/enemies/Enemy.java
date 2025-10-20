//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package enemies;

import helpz.Constants.Enemies;
import java.awt.Rectangle;

public abstract class Enemy {
    protected float x;
    protected float y;
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;

    public Enemy(float x, float y, int ID, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.bounds = new Rectangle((int)x, (int)y, 32, 32);
        this.lastDir = -1;
        this.setStartHealth();
    }

    private void setStartHealth() {
        this.health = Enemies.GetStartHealth(this.enemyType);
        this.maxHealth = this.health;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (this.health <= 0) {
            this.alive = false;
        }

    }

    public void move(float speed, int dir) {
        this.lastDir = dir;
        switch (dir) {
            case 0 -> this.x -= speed;
            case 1 -> this.y -= speed;
            case 2 -> this.x += speed;
            case 3 -> this.y += speed;
        }

    }

    public void setPos(int x, int y) {
        this.x = (float)x;
        this.y = (float)y;
    }

    public float getHealthBarFloat() {
        return (float)this.health / (float)this.maxHealth;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public int getHealth() {
        return this.health;
    }

    public int getID() {
        return this.ID;
    }

    public int getEnemyType() {
        return this.enemyType;
    }

    public int getLastDir() {
        return this.lastDir;
    }

    public boolean isAlive() {
        return this.alive;
    }
}
