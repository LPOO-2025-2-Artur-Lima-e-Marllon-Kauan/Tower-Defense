//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Main.Game;
import Main.GameStates;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList();
    private MyButton bPlaying;
    private MyButton bSettings;
    private MyButton bQuit;

    public Menu(Game game) {
        super(game);
        this.importImg();
        this.loadSprites();
        this.initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 320 - w / 2;
        int y = 150;
        int yOffset = 100;
        this.bPlaying = new MyButton("Play", x, y, w, h);
        this.bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        this.bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    public void render(Graphics g) {
        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        this.bPlaying.draw(g);
        this.bSettings.draw(g);
        this.bQuit.draw(g);
    }

    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("/Resources/sprite.png");

        try {
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadSprites() {
        for(int y = 0; y < 10; ++y) {
            for(int x = 0; x < 10; ++x) {
                this.sprites.add(this.img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }

    }

    public void mouseClicked(int x, int y) {
        if (this.bPlaying.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.PLAYING);
        } else if (this.bSettings.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.SETTINGS);
        } else if (this.bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }

    }

    public void mouseMoved(int x, int y) {
        this.bPlaying.setMouseOver(false);
        this.bSettings.setMouseOver(false);
        this.bQuit.setMouseOver(false);
        if (this.bPlaying.getBounds().contains(x, y)) {
            this.bPlaying.setMouseOver(true);
        } else if (this.bSettings.getBounds().contains(x, y)) {
            this.bSettings.setMouseOver(true);
        } else if (this.bQuit.getBounds().contains(x, y)) {
            this.bQuit.setMouseOver(true);
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bPlaying.getBounds().contains(x, y)) {
            this.bPlaying.setMousePressed(true);
        } else if (this.bSettings.getBounds().contains(x, y)) {
            this.bSettings.setMousePressed(true);
        } else if (this.bQuit.getBounds().contains(x, y)) {
            this.bQuit.setMousePressed(true);
        }

    }

    public void mouseReleased(int x, int y) {
        this.resetButtons();
    }

    private void resetButtons() {
        this.bPlaying.resetBooleans();
        this.bSettings.resetBooleans();
        this.bQuit.resetBooleans();
    }
}
