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

/**
 * Gerencia todas as torres no jogo
 * Responsável por: posicionamento, atualização, renderização e sistema de ataque
 */
public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs; // Sprites das torres (Cannon, Archer, Wizard)
    private ArrayList<Tower> towers = new ArrayList(); // Lista de todas as torres no mapa
    private int towerAmount = 0; // Contador para IDs únicos das torres

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

    /**
     * Adiciona uma nova torre no mapa
     * A posição já vem alinhada ao grid (múltiplo de 32)
     */
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        this.towers.add(new Tower(xPos, yPos, this.towerAmount++, selectedTower.getTowerType()));
    }

    /**
     * Atualiza todas as torres a cada frame
     * Para cada torre: atualiza cooldown e verifica se pode atacar
     */
    public void update() {
        for(Tower t : this.towers) {
            t.update(); // Incrementa contador de cooldown
            this.attackEnemyIfClose(t); // Verifica se há inimigos no alcance
        }

    }

    /**
     * Verifica se há inimigos no alcance da torre e ataca
     * Condições para atacar:
     * 1. Inimigo está vivo
     * 2. Inimigo está dentro do alcance da torre
     * 3. Cooldown da torre acabou
     */
    private void attackEnemyIfClose(Tower t) {
        for(Enemy e : this.playing.getEnemyManger().getEnemies()) {
            if (e.isAlive() && this.isEnemyInRange(t, e) && t.isCooldownOver()) {
                this.playing.shootEnemy(t, e); // Cria projétil
                t.resetCooldown(); // Reinicia tempo de espera para próximo ataque
            }
        }

    }

    /**
     * Calcula se o inimigo está dentro do alcance da torre
     * Usa distância euclidiana (hipotenusa) para cálculo preciso
     */
    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = Utilz.GetHypoDistance((float)t.getX(), (float)t.getY(), e.getX(), e.getY());
        return (float)range < t.getRange();
    }

    public void draw(Graphics g) {
        for(Tower t : this.towers) {
            g.drawImage(this.towerImgs[t.getTowerType()], t.getX(), t.getY(), (ImageObserver)null);
        }

    }

    /**
     * Busca uma torre em uma posição específica
     * Retorna null se não houver torre na posição
     */
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
