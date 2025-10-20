//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import helpz.LoadSave;
import java.awt.Component;
import javax.swing.JFrame;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;
import objects.PathPoint;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;
    private final double FPS_SET = (double)120.0F;
    private final double UPS_SET = (double)60.0F;
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;

    public Game() {
        this.initClasses();
        this.createDefaultLevel();
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        this.add(this.gameScreen);
        this.pack();
        this.setVisible(true);
    }

    private void createDefaultLevel() {
        // Ensure file exists
        int[] placeholder = new int[400];
        for (int i = 0; i < placeholder.length; i++) placeholder[i] = 0; // Grass
        LoadSave.CreateLevel("new_level", placeholder);

        // Build a simple straight road path across row 10
        int[][] lvl = new int[20][20];
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                lvl[y][x] = 0; // GRASS id
            }
        }
        for (int x = 0; x < 20; x++) {
            lvl[10][x] = 2; // ROAD_LR id
        }

        // Save level with meaningful start/end on the road
        LoadSave.SaveLevel("new_level", lvl, new PathPoint(0, 10), new PathPoint(19, 10));
    }

    private void initClasses() {
        this.tileManager = new TileManager();
        this.render = new Render(this);
        this.gameScreen = new GameScreen(this);
        this.menu = new Menu(this);
        this.playing = new Playing(this);
        this.settings = new Settings(this);
        this.editing = new Editing(this);
    }

    private void start() {
        this.gameThread = new Thread(this) {
        };
        this.gameThread.start();
    }

    private void updateGame() {
        switch (GameStates.gameState) {
            case PLAYING:
                this.playing.update();
            case MENU:
            case SETTINGS:
            default:
                break;
            case EDIT:
                this.editing.update();
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    public void run() {
        double timePerFrame = 8333333.333333333;
        double timePerUpdate = 1.6666666666666666E7;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while(true) {
            long now = System.nanoTime();
            if ((double)(now - lastFrame) >= timePerFrame) {
                this.repaint();
                lastFrame = now;
                ++frames;
            }

            if ((double)(now - lastUpdate) >= timePerUpdate) {
                this.updateGame();
                lastUpdate = now;
                ++updates;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000L) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Render getRender() {
        return this.render;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Playing getPlaying() {
        return this.playing;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Editing getEditor() {
        return this.editing;
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }
}
