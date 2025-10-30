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

/**
 * Classe principal do jogo Tower Defense
 * Gerencia o game loop, renderização e os diferentes estados do jogo (Menu, Playing, Editing, Settings)
 * Implementa Runnable para executar em uma thread separada
 */
public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;
    // FPS (Frames Per Second) - Taxa de atualização de frames: 120 por segundo
    private final double FPS_SET = (double)120.0F;
    // UPS (Updates Per Second) - Taxa de atualização da lógica do jogo: 60 por segundo
    private final double UPS_SET = (double)60.0F;
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;

    /**
     * Construtor do jogo - inicializa a janela e todos os componentes
     */
    public Game() {
        this.initClasses();
        this.createDefaultLevel();
        this.setDefaultCloseOperation(3); // Fecha a aplicação ao fechar a janela
        this.setLocationRelativeTo((Component)null); // Centraliza a janela na tela
        this.setResizable(false); // Impede redimensionamento da janela
        this.add(this.gameScreen);
        this.pack(); // Ajusta o tamanho da janela ao conteúdo
        this.setVisible(true);
    }

    /**
     * Cria um level padrão vazio (20x20 tiles = 400 tiles)
     * Inicializa todos os tiles com valor 0 (água)
     */
    private void createDefaultLevel() {
        int[] arr = new int[400];

        for(int i = 0; i < arr.length; ++i) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel("new_level", arr);
    }

    /**
     * Inicializa todas as classes e cenas do jogo
     * Ordem de inicialização é importante: TileManager e Render primeiro
     */
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

    /**
     * Atualiza a lógica do jogo baseado no estado atual
     * Apenas PLAYING e EDIT precisam de updates constantes
     */
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

    /**
     * Game Loop Principal
     * Separa a renderização (FPS) da atualização de lógica (UPS)
     * timePerFrame = 1 bilhão / 120 = ~8.3 milhões de nanosegundos entre frames
     * timePerUpdate = 1 bilhão / 60 = ~16.6 milhões de nanosegundos entre updates
     */
    public void run() {
        double timePerFrame = 8333333.333333333; // Tempo em nanosegundos para 120 FPS
        double timePerUpdate = 1.6666666666666666E7; // Tempo em nanosegundos para 60 UPS
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while(true) {
            long now = System.nanoTime();
            
            // Atualiza frame se já passou tempo suficiente (120 FPS)
            if ((double)(now - lastFrame) >= timePerFrame) {
                this.repaint(); // Chama paintComponent do GameScreen
                lastFrame = now;
                ++frames;
            }

            // Atualiza lógica do jogo se já passou tempo suficiente (60 UPS)
            if ((double)(now - lastUpdate) >= timePerUpdate) {
                this.updateGame();
                lastUpdate = now;
                ++updates;
            }

            // A cada segundo, exibe FPS e UPS no console
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
