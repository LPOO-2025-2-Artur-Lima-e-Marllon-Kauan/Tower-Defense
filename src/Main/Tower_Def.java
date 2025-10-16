package Main;

import cenas.Menu;
import cenas.Playing;
import cenas.Settings;
import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class Tower_Def extends JFrame implements Runnable {


    private Screen gameScreen;


    private int updates;
    private long lastTimeUPS;

    private Thread gameThread;
    private final double FPS_SET=120.00;
    private final double UPS_SET=60.00;


    private Renderizador renderizador;
    private Menu menu;
    private Playing playing;
    private Settings settings;



    public Tower_Def() {

        // Program window
        setTitle("Tower Def");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initClasses();

        add(gameScreen); // add JPanel in JFrame
        pack();
        setVisible(true);
    }

    private void initClasses() {
        renderizador = new Renderizador(this);
        gameScreen = new Screen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);

    }



    private void start(){
gameThread=new Thread(this){};

gameThread.start();




    }

    private void callUPS(){
if(System.currentTimeMillis() - lastTimeUPS>=1000) {
    System.out.println("UPS:"+updates);
    updates = 0;
    lastTimeUPS = System.currentTimeMillis();


}

    }
    private void updateGame() {
        //updates++;

       // System.out.println("Game update!");

    }

    public static void main(String[] args) {
        Tower_Def tower_def = new Tower_Def();
        tower_def.gameScreen.initInputs();
        tower_def.start();


    }
@Override
    public void run() {
        double timerPerFrame=1_000_000_000.0 / FPS_SET;
        double timerPerUpdate=1_000_000_000.0 / UPS_SET;

        long lastFrame=System.nanoTime();
        long lastUpdate=System.nanoTime();
        long lastTimeCheck=System.currentTimeMillis();

        int frames=0;
        int updates=0;



        long now;


        while(true){
            now = System.nanoTime();
            //render
        if (now - lastFrame >= timerPerFrame) {
          lastFrame = now;
           repaint();
           frames++;
           //update
    }if(now- lastUpdate > timerPerUpdate) {
                updateGame();
                lastUpdate=now;
                updates++;
            }
        if(System.currentTimeMillis()-lastTimeCheck>=1000){
            System.out.println("FPS:"+frames +"| UPS"+updates);
            frames=0;
            updates=0;
            lastTimeCheck=System.currentTimeMillis();
        }

        }
    }

    public Renderizador getRenderizador(){
        return renderizador;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

}

