//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import helpz.Constants.Towers;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import main.GameStates;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {
    private Playing playing;
    private MyButton bMenu;
    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        this.initButtons();
    }

    private void initButtons() {
        this.bMenu = new MyButton("Menu", 2, 642, 100, 30);
        this.towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int)((float)w * 1.1F);

        for(int i = 0; i < this.towerButtons.length; ++i) {
            this.towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }

    }

    private void drawButtons(Graphics g) {
        this.bMenu.draw(g);

        MyButton[] var5;
        for(MyButton b : var5 = this.towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(this.playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, (ImageObserver)null);
            this.drawButtonFeedback(g, b);
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(this.x, this.y, this.width, this.height);
        this.drawButtons(g);
        this.drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if (this.displayedTower != null) {
            g.setColor(Color.gray);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(this.playing.getTowerManager().getTowerImgs()[this.displayedTower.getTowerType()], 420, 650, 50, 50, (ImageObserver)null);
            g.setFont(new Font("LucidaSans", 1, 15));
            g.drawString(Towers.GetName(this.displayedTower.getTowerType()), 490, 660);
            g.drawString("ID: " + this.displayedTower.getId(), 490, 675);
            this.drawDisplayedTowerBorder(g);
            this.drawDisplayedTowerRange(g);
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

    public void mouseClicked(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.MENU);
        } else {
            MyButton[] var6;
            for(MyButton b : var6 = this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    this.selectedTower = new Tower(0, 0, -1, b.getId());
                    this.playing.setSelectedTower(this.selectedTower);
                    return;
                }
            }
        }

    }

    public void mouseMoved(int x, int y) {
        this.bMenu.setMouseOver(false);

        MyButton[] var6;
        for(MyButton b : var6 = this.towerButtons) {
            b.setMouseOver(false);
        }

        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMouseOver(true);
        } else {
            for(MyButton b : var6 = this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMousePressed(true);
        } else {
            MyButton[] var6;
            for(MyButton b : var6 = this.towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }

    }

    public void mouseReleased(int x, int y) {
        this.bMenu.resetBooleans();

        MyButton[] var6;
        for(MyButton b : var6 = this.towerButtons) {
            b.resetBooleans();
        }

    }
}
