package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Game;
import main.GameStates;
import ui.MyButton;

/**
 * Cena de Game Over
 * Mantém o mesmo estilo visual da tela inicial (Menu)
 * Mostra mensagem "Game Over" e um botão para reiniciar o jogo
 */
public class GameOver extends GameScene implements SceneMethods {
    private MyButton bRestart;

    public GameOver(Game game) {
        super(game);
        this.initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = 60;
        int x = 320 - w / 2; // centralizado horizontalmente
        int y = 420 - h / 2; // levemente abaixo do centro

        this.bRestart = new MyButton("Reiniciar", x, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        // Fundo igual ao Menu
        g.setColor(new Color(15, 15, 25));
        g.fillRect(0, 0, 640, 800);

        // Título principal "Tower Defense" (mesmo estilo do Menu)
        g.setColor(new Color(230, 230, 230));
        g.setFont(new Font("LucidaSans", Font.BOLD, 40));
        String title = "Tower Defense";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, 320 - titleWidth / 2, 120);

        // Mensagem "Game Over" logo abaixo do título
        g.setFont(new Font("LucidaSans", Font.BOLD, 32));
        String gameOverText = "Fim de Jogo";
        int goWidth = g.getFontMetrics().stringWidth(gameOverText);
        g.drawString(gameOverText, 320 - goWidth / 2, 200);

        // Botão de reiniciar
        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        this.bRestart.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (this.bRestart.getBounds().contains(x, y)) {
            // Reinicia o estado de jogo e volta para PLAYING
            this.game.resetPlaying();
            GameStates.SetGameState(GameStates.PLAYING);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        this.bRestart.setMouseOver(this.bRestart.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        if (this.bRestart.getBounds().contains(x, y)) {
            this.bRestart.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        this.bRestart.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // Não há comportamento especial para arrastar na tela de Game Over
    }
}
