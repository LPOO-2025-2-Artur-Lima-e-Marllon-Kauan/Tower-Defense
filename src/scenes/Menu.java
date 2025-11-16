//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Game;
import main.GameStates;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {
    // Botão único para iniciar o jogo
    private MyButton bPlaying;

    public Menu(Game game) {
        super(game);
        this.initButtons();
    }

    private void initButtons() {
        int w = 180;
        int h = 60;
        int x = 320 - w / 2; // centralizado horizontalmente (largura total 640)
        int y = 400 - h / 2; // centralizado verticalmente (altura total 800)

        // Único botão "Play" na tela inicial
        this.bPlaying = new MyButton("Play", x, y, w, h);
    }

    public void render(Graphics g) {
        // Fundo simples da tela inicial
        g.setColor(new Color(15, 15, 25));
        g.fillRect(0, 0, 640, 800);

        // Título "Tower Defense" no topo
        g.setColor(new Color(230, 230, 230));
        g.setFont(new Font("LucidaSans", Font.BOLD, 40));
        String title = "Tower Defense";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, 320 - titleWidth / 2, 120);

        // Botão Play centralizado
        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        // Apenas o botão Play é desenhado na tela inicial
        this.bPlaying.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (this.bPlaying.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.PLAYING);
        }

    }

    public void mouseMoved(int x, int y) {
        // Apenas o botão Play existe na tela inicial
        this.bPlaying.setMouseOver(this.bPlaying.getBounds().contains(x, y));
    }

    public void mousePressed(int x, int y) {
        if (this.bPlaying.getBounds().contains(x, y)) {
            this.bPlaying.setMousePressed(true);
        }

    }

    public void mouseReleased(int x, int y) {
        this.resetButtons();
    }

    private void resetButtons() {
        this.bPlaying.resetBooleans();
    }

    public void mouseDragged(int x, int y) {
    }
}
