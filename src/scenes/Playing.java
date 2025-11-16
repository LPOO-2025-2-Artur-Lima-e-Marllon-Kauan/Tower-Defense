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
import java.util.ArrayList;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;

/**
 * Cena principal onde o jogo acontece
 * Gerencia: mapa, inimigos, torres, projéteis, waves, economia (ouro) e vidas
 */
public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl; // Matriz do mapa (20x20)
    private final ActionBar actionBar; // Barra inferior com informações e controles
    private int mouseX; // Posição X do mouse (alinhada ao grid)
    private int mouseY; // Posição Y do mouse (alinhada ao grid)
    private final EnemyManager enemyManager;
    private final TowerManager towerManager;
    private final ProjectileManager projManager;
    private final WaveManager waveManager;
    private PathPoint start; // Ponto onde inimigos aparecem
    private PathPoint end; // Ponto onde inimigos escapam
    private Tower selectedTower; // Torre selecionada para posicionar
    private int goldTick; // Contador para geração passiva de ouro
    private int lives = 8; // Vidas do jogador (aumentado para 8 corações)
    private final int TILE_SIZE = 32; // tamanho do tile (padronizado)

    public Playing(Game game) {
        super(game);
        this.loadDefaultLevel();
        this.actionBar = new ActionBar(0, 600, 640, 200, this);
        this.enemyManager = new EnemyManager(this, this.start, this.end);
        this.towerManager = new TowerManager(this);
        this.projManager = new ProjectileManager(this);
        this.waveManager = new WaveManager(this);
    }

    private void loadDefaultLevel() {
        this.lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        this.start = points.get(0);
        this.end = points.get(1);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    /**
     * Loop principal de atualização do jogo (60 vezes por segundo)
     * Ordem:
     * 1. Atualiza animações
     * 2. Gerencia waves (tempo entre waves, spawn de inimigos)
     * 3. Gera ouro passivo (1 ouro a cada 3 segundos - 180 ticks)
     * 4. Atualiza todos os managers (inimigos, torres, projéteis)
     */
    public void update() {
        this.updateTick(); // Atualiza contador de animações
        this.waveManager.update();
        
        // Sistema de renda passiva: +1 ouro a cada 180 ticks (3 segundos)
        ++this.goldTick;
        if (this.goldTick % 180 == 0) {
            this.actionBar.addGold(1);
        }

        // Gerenciamento de waves: quando todos inimigos morrem, prepara próxima wave
        if (this.isAllEnemiesDead() && this.isThereMoreWaves()) {
            this.waveManager.startWaveTimer();
            if (this.isWaveTimerOver()) {
                this.waveManager.increaseWaveIndex();
                this.enemyManager.getEnemies().clear();
                this.waveManager.resetEnemyIndex();
            }
        }

        // Spawn de inimigos durante a wave
        if (this.isTimeForNewEnemy()) {
            this.spawnEnemy();
        }

        // Atualiza todos os elementos do jogo
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

    /**
     * Verifica se todos os inimigos da wave atual foram eliminados
     * Retorna false se:
     * 1. Ainda há inimigos para spawnar na wave atual
     * 2. Existe pelo menos um inimigo vivo no mapa
     */
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
            // desenha um bloco colorido semi-transparente da torre selecionada (24x24)
            final int blockSize = 24;
            final int offset = 4;
            int x = this.mouseX + offset;
            int y = this.mouseY + offset;

            Color col;
            switch (this.selectedTower.getTowerType()) {
                case 0 -> col = new Color(220, 100, 50, 150); // Cannon - laranja semi-transparente
                case 1 -> col = new Color(100, 200, 50, 150); // Archer - verde-claro semi-transparente
                case 2 -> col = new Color(150, 100, 200, 150); // Wizard - roxo semi-transparente
                case 3 -> col = new Color(80, 200, 120, 150); // Cospe Veneno - verde semi-transparente
                default -> col = new Color(255, 0, 255, 150);
            }
            g.setColor(col);
            g.fillRect(x, y, blockSize, blockSize);
            g.setColor(Color.WHITE);
            g.drawRect(x, y, blockSize, blockSize);
        }

    }

    private void drawLevel(Graphics g) {
        for(int y = 0; y < this.lvl.length; ++y) {
            for(int x = 0; x < this.lvl[y].length; ++x) {
                int id = this.lvl[y][x];
                int tileType = this.game.getTileManager().getTile(id).getTileType();

                Color color;
                switch (tileType) {
                    case 0 -> color = new Color(0, 100, 200); // água - azul
                    case 1 -> color = new Color(50, 150, 50); // chão/grama - verde
                    case 2 -> color = Color.BLACK; // caminho - preto
                    default -> color = Color.MAGENTA;
                }

                g.setColor(color);
                g.fillRect(x * 32, y * 32, 32, 32);
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

    /**
     * Gerencia cliques do mouse
     * Três comportamentos possíveis:
     * 1. Clique na ActionBar (y >= 640): delega para ActionBar
     * 2. Torre selecionada: tenta posicionar torre (se for grama e não tem torre lá)
     * 3. Sem torre selecionada: seleciona torre existente para ver info
     */
    public void mouseClicked(int x, int y) {
        if (y >= this.actionBar.getY()) {
            this.actionBar.mouseClicked(x, y);
        } else if (this.selectedTower != null) {
            // Verifica se pode construir: deve ser grama e não ter torre
            if (this.isTileGrass(this.mouseX, this.mouseY) && this.getTowerAt(this.mouseX, this.mouseY) == null) {
                this.towerManager.addTower(this.selectedTower, this.mouseX, this.mouseY);
                this.removeGold(this.selectedTower.getTowerType()); // Desconta custo
                this.selectedTower = null; // Limpa seleção
            }
        } else {
            // Sem torre selecionada: clica em torre existente para ver detalhes
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

    /**
     * Verifica se um tile é grama (tipo 1)
     * Torres só podem ser construídas em grama
     */
    private boolean isTileGrass(int x, int y) {
        int id = this.lvl[y / this.TILE_SIZE][x / this.TILE_SIZE];
        int tileType = this.game.getTileManager().getTile(id).getTileType();
        return tileType == 1; // 0=água, 1=grama, 2=estrada
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
        if (y >= this.actionBar.getY()) {
            this.actionBar.mouseMoved(x, y);
        } else {
            this.mouseX = x / this.TILE_SIZE * this.TILE_SIZE;
            this.mouseY = y / this.TILE_SIZE * this.TILE_SIZE;
        }

    }

    public void mousePressed(int x, int y) {
        if (y >= this.actionBar.getY()) {
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
        this.lives--;
        if (this.lives <= 0) {
            main.GameStates.SetGameState(main.GameStates.GAME_OVER);
        }
    }

    public int getLives() {
        return this.lives;
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
