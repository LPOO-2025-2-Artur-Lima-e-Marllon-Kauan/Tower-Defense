//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Botão customizado para a UI
 * Possui estados visuais: normal, hover, pressed
 * Usado em ActionBar e Toolbar
 */
public class MyButton {
    public int x;
    public int y;
    public int width;
    public int height;
    public int id; // ID opcional para identificar o botão (ex: tipo de torre)
    private String text; // Texto exibido no botão
    private Rectangle bounds; // Área clicável do botão
    private boolean mouseOver; // Mouse está sobre o botão?
    private boolean mousePressed; // Botão está sendo pressionado?

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;
        this.initBounds();
    }

    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * Renderiza o botão completo
     * Ordem: corpo (background) -> borda -> texto
     */
    public void draw(Graphics g) {
        this.drawBody(g);
        this.drawBorder(g);
        this.drawText(g);
    }

    /**
     * Desenha borda do botão
     * Se pressionado, adiciona bordas internas para efeito 3D
     */
    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(this.x, this.y, this.width, this.height);
        
        // Efeito visual ao pressionar: múltiplas bordas internas
        if (this.mousePressed) {
            g.drawRect(this.x + 1, this.y + 1, this.width - 2, this.height - 2);
            g.drawRect(this.x + 2, this.y + 2, this.width - 4, this.height - 4);
        }

    }

    /**
     * Desenha o corpo (fundo) do botão
     * Muda de cor quando mouse passa por cima
     */
    private void drawBody(Graphics g) {
        if (this.mouseOver) {
            g.setColor(Color.gray); // Hover: cinza
        } else {
            g.setColor(Color.WHITE); // Normal: branco
        }

        g.fillRect(this.x, this.y, this.width, this.height);
    }

    /**
     * Desenha texto centralizado no botão
     */
    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(this.text);
        int h = g.getFontMetrics().getHeight();
        // Calcula posição para centralizar o texto
        g.drawString(this.text, this.x - w / 2 + this.width / 2, this.y + h / 4 + this.height / 2);
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMouseOver() {
        return this.mouseOver;
    }

    public boolean isMousePressed() {
        return this.mousePressed;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public int getId() {
        return this.id;
    }
}
