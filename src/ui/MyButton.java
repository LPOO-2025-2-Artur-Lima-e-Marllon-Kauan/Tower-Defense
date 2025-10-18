//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
    public int x;
    public int y;
    public int width;
    public int height;
    public int id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver;
    private boolean mousePressed;

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

    public void draw(Graphics g) {
        this.drawBody(g);
        this.drawBorder(g);
        this.drawText(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(this.x, this.y, this.width, this.height);
        if (this.mousePressed) {
            g.drawRect(this.x + 1, this.y + 1, this.width - 2, this.height - 2);
            g.drawRect(this.x + 2, this.y + 2, this.width - 4, this.height - 4);
        }

    }

    private void drawBody(Graphics g) {
        if (this.mouseOver) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(this.x, this.y, this.width, this.height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(this.text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(this.text, this.x - w / 2 + this.width / 2, this.y + h / 2 + this.height / 2);
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
