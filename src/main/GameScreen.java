//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        this.setPanelSize();
    }

    public void initInputs() {
        this.myMouseListener = new MyMouseListener(this.game);
        this.keyboardListener = new KeyboardListener();
        this.addMouseListener(this.myMouseListener);
        this.addMouseMotionListener(this.myMouseListener);
        this.addKeyListener(this.keyboardListener);
        this.requestFocus();
    }

    private void setPanelSize() {
        this.size = new Dimension(640, 740);
        this.setMinimumSize(this.size);
        this.setPreferredSize(this.size);
        this.setMaximumSize(this.size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.getRender().render(g);
    }
}
