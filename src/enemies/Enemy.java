//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package enemies;

import helpz.Constants.Enemies;
import java.awt.Rectangle;
import managers.EnemyManager;

/**
 * Classe abstrata base para todos os inimigos
 * Contém lógica comum: movimento, vida, dano, efeitos (lentidão)
 * Subclasses: Orc, Bat, Knight, Wolf
 */
public abstract class Enemy {
    protected EnemyManager enemyManager;
    protected float x; // Posição X em pixels
    protected float y; // Posição Y em pixels
    protected Rectangle bounds; // Hitbox para colisões
    protected int health; // Vida atual
    protected int maxHealth; // Vida máxima
    protected int ID; // Identificador único
    protected int enemyType; // Tipo: 0=Orc, 1=Bat, 2=Knight, 3=Wolf
    protected int lastDir; // Última direção: 0=LEFT, 1=UP, 2=RIGHT, 3=DOWN, -1=não definida
    protected boolean alive = true; // Está vivo?
    protected int slowTickLimit = 120; // Duração do efeito de lentidão (2 segundos)
    protected int slowTick; // Contador do efeito de lentidão

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
        this.slowTick = this.slowTickLimit;
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        this.bounds = new Rectangle((int)x, (int)y, 32, 32);
        this.lastDir = -1;
        this.setStartHealth();
    }

    private void setStartHealth() {
        this.health = Enemies.GetStartHealth(this.enemyType);
        this.maxHealth = this.health;
    }

    /**
     * Aplica dano ao inimigo
     * Se a vida chegar a 0, morre e dá recompensa ao jogador
     */
    public void hurt(int dmg) {
        this.health -= dmg;
        if (this.health <= 0) {
            this.alive = false;
            this.enemyManager.rewardPlayer(this.enemyType);
        }

    }

    public void kill() {
        this.alive = false;
        this.health = 0;
    }

    public void slow() {
        this.slowTick = 0;
    }

    /**
     * Move o inimigo na direção especificada
     * Se estiver sob efeito de lentidão, velocidade é reduzida em 50%
     * Direções: 0=ESQUERDA, 1=CIMA, 2=DIREITA, 3=BAIXO
     */
    public void move(float speed, int dir) {
        this.lastDir = dir;
        // Aplica efeito de lentidão se ativo
        if (this.slowTick < this.slowTickLimit) {
            ++this.slowTick;
            speed *= 0.5F; // Reduz velocidade pela metade
        }

        switch (dir) {
            case 0 -> this.x -= speed; // ESQUERDA
            case 1 -> this.y -= speed; // CIMA
            case 2 -> this.x += speed; // DIREITA
            case 3 -> this.y += speed; // BAIXO
        }

        this.updateHitbox();
    }

    private void updateHitbox() {
        this.bounds.x = (int)this.x;
        this.bounds.y = (int)this.y;
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

    public boolean isSlowed() {
        return this.slowTick < this.slowTickLimit;
    }
}
