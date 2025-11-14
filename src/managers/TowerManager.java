//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import enemies.Enemy;
import helpz.Utilz;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import objects.Tower;
import scenes.Playing;

/**
 * Gerencia todas as torres no jogo
 * Responsável por: posicionamento, atualização, renderização e sistema de ataque
 */
public class TowerManager {
    private final Playing playing;
    // torres agora são desenhadas como blocos coloridos
    private final ArrayList<Tower> towers = new ArrayList<>(); // Lista de todas as torres no mapa
    private int towerAmount = 0; // Contador para IDs únicos das torres

    public TowerManager(Playing playing) {
        this.playing = playing;
        // sem carregamento de sprites para torres
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
            // desenha um bloco colorido representando a torre (24x24, centralizado)
            final int blockSize = 24;
            final int offset = 4; // (32-24)/2 para centralizar
            int x = t.getX() + offset;
            int y = t.getY() + offset;

            Color col;
            switch (t.getTowerType()) {
                case 0 -> col = new Color(220, 100, 50); // Cannon - laranja
                case 1 -> col = new Color(100, 200, 50); // Archer - verde-claro
                case 2 -> col = new Color(150, 100, 200); // Wizard - roxo
                default -> col = Color.MAGENTA;
            }
            g.setColor(col);
            g.fillRect(x, y, blockSize, blockSize);
            // contorno
            g.setColor(Color.black);
            g.drawRect(x, y, blockSize, blockSize);
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
}
