//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import helpz.Constants.Towers;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import main.GameStates;
import objects.Tower;
import scenes.Playing;

/**
 * Barra de ações na parte inferior da tela durante o jogo
 * Exibe: ouro, vidas, informações da wave, botões de torres, detalhes de torres selecionadas
 * Gerencia toda a UI de gameplay
 */
public class ActionBar extends Bar {
    private final Playing playing;
    private MyButton bMenu; // Botão para voltar ao menu
    private MyButton[] towerButtons; // 3 botões para comprar torres
    private Tower displayedTower; // Torre clicada para exibir informações
    private final DecimalFormat formatter; // Formatação de números decimais (timer)
    private int gold = 100; // Ouro inicial do jogador
    private boolean showTowerCost; // Exibe tooltip de custo ao passar mouse
    private int towerCostType; // Tipo da torre do tooltip

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        this.formatter = new DecimalFormat("0.0");
        this.initButtons();
    }

    /**
     * Inicializa todos os botões da ActionBar
     * - 1 botão de Menu
     * - 3 botões de torres (Cannon, Archer, Wizard)
     */
    private void initButtons() {
        this.bMenu = new MyButton("Menu", 2, 642, 100, 30);
        this.towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int)((float)w * 1.1F); // 10% de espaço entre botões

        // Cria os 3 botões de torres com IDs correspondentes
        for(int i = 0; i < this.towerButtons.length; ++i) {
            this.towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }

    }

    private void drawButtons(Graphics g) {
        this.bMenu.draw(g);

        for(MyButton b : this.towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x, b.y, b.width, b.height);

            // Desenha bloco colorido representando a torre
            Color col;
            switch (b.getId()) {
                case 0 -> col = new Color(220, 100, 50); // Cannon - laranja
                case 1 -> col = new Color(100, 200, 50); // Archer - verde-claro
                case 2 -> col = new Color(150, 100, 200); // Wizard - roxo
                default -> col = Color.MAGENTA;
            }
            g.setColor(col);
            g.fillRect(b.x + 5, b.y + 5, b.width - 10, b.height - 10);
            g.setColor(Color.black);
            g.drawRect(b.x + 5, b.y + 5, b.width - 10, b.height - 10);

            this.drawButtonFeedback(g, b);
        }

    }

    /**
     * Renderiza toda a ActionBar e seus componentes
     */
    public void draw(Graphics g) {
        // Fundo laranja da barra
        g.setColor(new Color(220, 123, 15));
        g.fillRect(this.x, this.y, this.width, this.height);
        
        // Desenha todos os elementos da UI
        this.drawButtons(g);
        this.drawDisplayedTower(g);
        this.drawWaveInfo(g);
        this.drawGoldAmount(g);
        this.drawLives(g);
        
        // Tooltip de custo da torre (ao passar mouse)
        if (this.showTowerCost) {
            this.drawTowerCost(g);
        }

    }

    /**
     * Desenha tooltip mostrando nome e custo da torre
     * Aparece ao passar o mouse sobre um botão de torre
     * Mostra aviso vermelho se não tiver ouro suficiente
     */
    private void drawTowerCost(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(280, 650, 120, 50);
        g.setColor(Color.black);
        g.drawRect(280, 650, 120, 50);
        g.drawString(this.getTowerCostName(), 285, 670);
        g.drawString("Cost: " + this.getTowerCostCost() + "g", 285, 695);
        
        // Aviso se não tiver ouro suficiente
        if (this.isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.RED);
            g.drawString("Can't Afford", 270, 725);
        }

    }

    private boolean isTowerCostMoreThanCurrentGold() {
        return this.getTowerCostCost() > this.gold;
    }

    private String getTowerCostName() {
        return Towers.GetName(this.towerCostType);
    }

    private int getTowerCostCost() {
        return Towers.GetTowerCost(this.towerCostType);
    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: " + this.gold + "g", 110, 725);
    }

    private void drawLives(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Lives: " + this.playing.getLives(), 350, 725);
    }

    /**
     * Desenha informações sobre as waves
     * - Timer até próxima wave
     * - Quantidade de inimigos restantes
     * - Wave atual / total
     */
    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        this.drawWaveTimerInfo(g);
        this.drawEnemiesLeftInfo(g);
        this.drawWavesLeftInfo(g);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = this.playing.getWaveManager().getWaveIndex();
        int size = this.playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + " / " + size, 425, 770);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = this.playing.getEnemyManger().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 425, 790);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (this.playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = this.playing.getWaveManager().getTimeLeft();
            String formattedText = this.formatter.format(timeLeft);
            g.drawString("Time Left: " + formattedText, 425, 750);
        }

    }

    /**
     * Desenha detalhes da torre selecionada/clicada
     * Mostra: bloco colorido, nome, ID, borda azul no mapa, círculo de alcance
     */
    private void drawDisplayedTower(Graphics g) {
        if (this.displayedTower != null) {
            // Caixa de informações da torre
            g.setColor(Color.gray);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            
            // Desenha bloco colorido da torre
            Color col;
            switch (this.displayedTower.getTowerType()) {
                case 0 -> col = new Color(220, 100, 50); // Cannon - laranja
                case 1 -> col = new Color(100, 200, 50); // Archer - verde-claro
                case 2 -> col = new Color(150, 100, 200); // Wizard - roxo
                default -> col = Color.MAGENTA;
            }
            g.setColor(col);
            g.fillRect(425, 655, 40, 40);
            g.setColor(Color.black);
            g.drawRect(425, 655, 40, 40);

            g.setFont(new Font("LucidaSans", Font.BOLD, 15));
            g.setColor(Color.black);
            g.drawString(Towers.GetName(this.displayedTower.getTowerType()), 490, 660);
            g.drawString("ID: " + this.displayedTower.getId(), 490, 675);
            
            // Feedback visual no mapa
            this.drawDisplayedTowerBorder(g); // Borda azul
            this.drawDisplayedTowerRange(g);  // Círculo de alcance
        }

    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(this.displayedTower.getX() + 16 - (int)(this.displayedTower.getRange() * 2.0F) / 2, this.displayedTower.getY() + 16 - (int)(this.displayedTower.getRange() * 2.0F) / 2, (int)this.displayedTower.getRange() * 2, (int)this.displayedTower.getRange() * 2);
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(this.displayedTower.getX(), this.displayedTower.getY(), 32, 32);
    }

    public void displayTower(Tower t) {
        this.displayedTower = t;
    }

    /**
     * Gerencia cliques nos botões da ActionBar
     * - Botão Menu: volta ao menu principal
     * - Botões de Torres: seleciona torre para construir (se tiver ouro)
     */
    public void mouseClicked(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.MENU);
        } else {
            for(MyButton b : this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    // Verifica se tem ouro suficiente
                    if (!this.isGoldEnoughForTower(b.getId())) {
                        return;
                    }

                    // Cria torre temporária para posicionamento
                    Tower selectedTower = new Tower(0, 0, -1, b.getId());
                    this.playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }

    }

    private boolean isGoldEnoughForTower(int towerType) {
        return this.gold >= Towers.GetTowerCost(towerType);
    }

    /**
     * Atualiza estados de hover dos botões
     * Mostra tooltip de custo quando mouse está sobre botão de torre
     */
    public void mouseMoved(int x, int y) {
        // Reseta todos os estados de hover
        this.bMenu.setMouseOver(false);
        this.showTowerCost = false;

        for(MyButton b : this.towerButtons) {
            b.setMouseOver(false);
        }

        // Verifica qual botão está sob o mouse
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMouseOver(true);
        } else {
            for(MyButton b : this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    this.showTowerCost = true;    // Ativa tooltip
                    this.towerCostType = b.getId(); // Define tipo para tooltip
                    return;
                }
            }
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMousePressed(true);
        } else {
            for(MyButton b : this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }

    }

    public void mouseReleased(int x, int y) {
        this.bMenu.resetBooleans();

        for(MyButton b : this.towerButtons) {
            b.resetBooleans();
        }

    }

    /**
     * Desconta o custo da torre do ouro do jogador
     */
    public void payForTower(int towerType) {
        this.gold -= Towers.GetTowerCost(towerType);
    }

    /**
     * Adiciona ouro ao jogador (ao matar inimigos ou por renda passiva)
     */
    public void addGold(int getReward) {
        this.gold += getReward;
    }
}
