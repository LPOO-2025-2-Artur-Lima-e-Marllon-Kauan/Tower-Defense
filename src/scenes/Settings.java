//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import java.awt.Color;
import java.awt.Graphics;
import Main.Game;
import Main.GameStates;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods {
    private MyButton bMenu;

    public Settings(Game game) {
        super(game);
        this.initButtons();
    }

    private void initButtons() {
        this.bMenu = new MyButton("Menu", 2, 2, 100, 30);
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 640, 640);
        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        this.bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.MENU);
        }

    }

    public void mouseMoved(int x, int y) {
        this.bMenu.setMouseOver(false);
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMouseOver(true);
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMousePressed(true);
        }

    }

    public void mouseReleased(int x, int y) {
        this.resetButtons();
    }

    private void resetButtons() {
        this.bMenu.resetBooleans();
    }
}
