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
import objects.Tower;
import scenes.Playing;

/**
 * Barra de ações na parte inferior da tela durante o jogo
 * Exibe: ouro, vidas, informações da wave, botões de torres, detalhes de torres selecionadas
 * Gerencia toda a UI de gameplay
 */
public class ActionBar extends Bar {
    private final Playing playing;
    // Botões para selecionar as torres disponíveis
    private MyButton[] towerButtons; // 4 botões para comprar torres (inclui Cospe Veneno)
    private MyButton upgradeButton; // Botão para fazer upgrade da torre selecionada
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
        this.towerButtons = new MyButton[4];
        int w = 50;
        int h = 50;
        int xStart = 20;   // alinhado mais à esquerda para um layout limpo
        int yStart = 635;  // um pouco acima da base da ActionBar
        int xOffset = (int)(w * 1.2f); // pequeno espaço entre botões

        // Cria os 4 botões de torres com IDs correspondentes
        for (int i = 0; i < this.towerButtons.length; ++i) {
            this.towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }

        // Botão de upgrade da torre selecionada
        int upgW = 110;
        int upgH = 35;
        int upgX = 480;
        int upgY = 630;
        this.upgradeButton = new MyButton("Upgrade", upgX, upgY, upgW, upgH);
    }

    private void drawButtons(Graphics g) {
        // Título da área de seleção de torres
        g.setColor(Color.WHITE);
        g.setFont(new Font("LucidaSans", Font.BOLD, 16));
        g.drawString("Torres", 20, 630);

        for (MyButton b : this.towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x, b.y, b.width, b.height);

            // Desenha bloco colorido representando a torre
            Color col;
            switch (b.getId()) {
                case 0 -> col = new Color(220, 100, 50); // Cannon - laranja
                case 1 -> col = new Color(100, 200, 50); // Archer - verde-claro
                case 2 -> col = new Color(150, 100, 200); // Wizard - roxo
                case 3 -> col = new Color(80, 200, 120); // Cospe Veneno - verde venenoso
                default -> col = Color.MAGENTA;
            }
            g.setColor(col);
            g.fillRect(b.x + 5, b.y +5 , b.width - 10, b.height - 10);
            g.setColor(Color.black);
            g.drawRect(b.x + 5, b.y +5, b.width - 10, b.height - 10);

            this.drawButtonFeedback(g, b);
        }

    }

    /**
     * Renderiza toda a ActionBar e seus componentes
     */
    public void draw(Graphics g) {
        // Fundo simples e escuro da barra inferior
        g.setColor(new Color(25, 25, 35));
        g.fillRect(this.x, this.y, this.width, this.height);

        // Elementos principais do HUD: torres, info de torre e HUD de waves/recursos
        this.drawButtons(g);
        this.drawDisplayedTower(g);
        this.drawWaveInfo(g);

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
        g.fillRect(280, 635, 150, 50);
        g.setColor(Color.black);
        g.drawRect(280, 635, 150, 50);
        g.drawString(this.getTowerCostName(), 285, 655);
        g.drawString("Custo: " + this.getTowerCostCost() + "g", 285, 675);
        
        // Aviso se não tiver ouro suficiente
        if (this.isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.RED);
            g.drawString("Ouro insuficiente", 270, 630);
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
        // Mesmo estilo de texto das waves, posicionado à direita
        g.drawString("Ouro: " + this.gold + "g", 450, 700);
    }

    private void drawLives(Graphics g) {
        // Logo abaixo do ouro, ainda acima das waves
        g.drawString("Vidas: " + this.playing.getLives(), 450, 720);
    }

    /**
     * Desenha informações sobre as waves
     * - Timer até próxima wave
     * - Quantidade de inimigos restantes
     * - Wave atual / total
     */
    private void drawWaveInfo(Graphics g) {
        // Texto da HUD da ActionBar deve ser branco para boa visibilidade
        g.setColor(Color.WHITE);
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));

        // Ouro e vidas acima das informações de wave/inimigos, mesmo estilo de fonte
        this.drawGoldAmount(g);
        this.drawLives(g);

        this.drawWaveTimerInfo(g);
        this.drawEnemiesLeftInfo(g);
        this.drawWavesLeftInfo(g);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = this.playing.getWaveManager().getWaveIndex();
        int size = this.playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + " / " + size, 450, 750);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = this.playing.getEnemyManger().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 450, 770);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (this.playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = this.playing.getWaveManager().getTimeLeft();
            String formattedText = this.formatter.format(timeLeft);
            g.drawString("Time Left: " + formattedText, 450, 20);
        }

    }

    /**
     * Desenha detalhes da torre selecionada/clicada
     * Mostra: bloco colorido, nome, ID, borda azul no mapa, círculo de alcance
     */
    private void drawDisplayedTower(Graphics g) {
        if (this.displayedTower != null) {
            // Destaque no mapa
            this.drawDisplayedTowerBorder(g); // borda azul em volta da torre
            this.drawDisplayedTowerRange(g);  // círculo de alcance

            // Painel de informações da torre selecionada
            int infoX = 20;
            int infoY = 700;
            int infoW = 260;
            int infoH = 85;

            g.setColor(new Color(40, 40, 60));
            g.fillRect(infoX, infoY, infoW, infoH);
            g.setColor(Color.WHITE);
            g.drawRect(infoX, infoY, infoW, infoH);

            g.setFont(new Font("LucidaSans", Font.BOLD, 14));
            String name = Towers.GetName(this.displayedTower.getTowerType());
            g.drawString(name + " Lv." + this.displayedTower.getLevel(), infoX + 10, infoY + 20);

            g.setFont(new Font("LucidaSans", Font.PLAIN, 12));
            g.drawString("Dano: " + this.displayedTower.getDmg(), infoX + 10, infoY + 40);
            g.drawString("Alcance: " + (int)this.displayedTower.getRange(), infoX + 10, infoY + 55);
            g.drawString("Cooldown: " + (int)this.displayedTower.getCooldown(), infoX + 10, infoY + 70);

            // Informações de upgrade e botão
            this.drawUpgradeSection(g, infoX + infoW + 10, infoY);
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

    private void drawUpgradeSection(Graphics g, int x, int y) {
        if (this.displayedTower == null) {
            return;
        }

        g.setFont(new Font("LucidaSans", Font.PLAIN, 12));
        g.setColor(Color.WHITE);

        int currentLevel = this.displayedTower.getLevel();
        if (currentLevel >= Towers.MAX_LEVEL) {
            g.drawString("Nível máximo alcançado", x, y + 20);
            return;
        }

        int nextLevel = currentLevel + 1;
        int cost = Towers.GetUpgradeCost(this.displayedTower.getTowerType(), currentLevel);
        g.drawString("Upgrade -> Lv." + nextLevel, x, y + 20);
        g.drawString("Custo: " + cost + "g", x, y + 40);

        // Feedback visual se não tiver ouro suficiente
        if (this.gold < cost) {
            g.setColor(Color.RED);
            g.drawString("Ouro insuficiente", x, y + 60);
        }

        // Desenha o botão de upgrade
        boolean canAfford = this.gold >= cost;
        g.setColor(canAfford ? new Color(80, 180, 80) : new Color(90, 90, 90));
        g.fillRect(this.upgradeButton.x, this.upgradeButton.y, this.upgradeButton.width, this.upgradeButton.height);
        g.setColor(Color.BLACK);
        g.drawRect(this.upgradeButton.x, this.upgradeButton.y, this.upgradeButton.width, this.upgradeButton.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("LucidaSans", Font.BOLD, 14));
        g.drawString("Upgrade", this.upgradeButton.x + 12, this.upgradeButton.y + 22);
    }

    /**
     * Gerencia cliques nos botões da ActionBar
     * - Botão Menu: volta ao menu principal
     * - Botões de Torres: seleciona torre para construir (se tiver ouro)
     */
    public void mouseClicked(int x, int y) {
        for (MyButton b : this.towerButtons) {
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

        // Clique no botão de upgrade da torre selecionada
        if (this.displayedTower != null && this.upgradeButton.getBounds().contains(x, y)) {
            this.tryUpgradeDisplayedTower();
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
        // Reseta estados de hover dos botões de torre
        this.showTowerCost = false;

        for (MyButton b : this.towerButtons) {
            b.setMouseOver(false);
        }
        this.upgradeButton.setMouseOver(false);

        // Verifica qual botão está sob o mouse
        for (MyButton b : this.towerButtons) {
            if (b.getBounds().contains(x, y)) {
                b.setMouseOver(true);
                this.showTowerCost = true;    // Ativa tooltip
                this.towerCostType = b.getId(); // Define tipo para tooltip
                return;
            }
        }

        if (this.displayedTower != null && this.upgradeButton.getBounds().contains(x, y)) {
            this.upgradeButton.setMouseOver(true);
        }

    }

    public void mousePressed(int x, int y) {
        for (MyButton b : this.towerButtons) {
            if (b.getBounds().contains(x, y)) {
                b.setMousePressed(true);
                return;
            }
        }

        if (this.displayedTower != null && this.upgradeButton.getBounds().contains(x, y)) {
            this.upgradeButton.setMousePressed(true);
        }

    }

    public void mouseReleased(int x, int y) {
        for (MyButton b : this.towerButtons) {
            b.resetBooleans();
        }
        this.upgradeButton.resetBooleans();

        // After releasing, update hover state based on current mouse position
        // (this uses the x,y params so they are not reported as unused)
        this.mouseMoved(x, y);
    }

    /**
     * Desconta o custo da torre do ouro do jogador
     */
    public void payForTower(int towerType) {
        this.gold -= Towers.GetTowerCost(towerType);
    }

    /**
     * Tenta fazer upgrade da torre exibida, verificando ouro e limite de nível.
     */
    private void tryUpgradeDisplayedTower() {
        if (this.displayedTower == null || !this.displayedTower.canUpgrade()) {
            return;
        }

        int currentLevel = this.displayedTower.getLevel();
        int cost = Towers.GetUpgradeCost(this.displayedTower.getTowerType(), currentLevel);
        if (cost <= 0 || this.gold < cost) {
            return;
        }

        this.gold -= cost;
        this.displayedTower.upgrade();
    }

    /**
     * Adiciona ouro ao jogador (ao matar inimigos ou por renda passiva)
     */
    public void addGold(int getReward) {
        this.gold += getReward;
    }
}
