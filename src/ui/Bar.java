//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe base para barras de UI (ActionBar e Toolbar)
 * Fornece métodos comuns para desenhar feedback visual de botões
 */
public class Bar {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Retorna a posição Y (topo) da barra para uso externo
     */
    public int getY() {
        return this.y;
    }

    /**
     * Desenha feedback visual para botões (hover e pressed)
     * - Borda branca ao passar mouse
     * - Bordas internas ao pressionar
     */
    protected void drawButtonFeedback(Graphics g, MyButton b) {
        // Muda cor da borda baseado em hover
        if (b.isMouseOver()) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawRect(b.x, b.y, b.width, b.height);
        
        // Adiciona bordas internas se pressionado
        if (b.isMousePressed()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }

    }
}
