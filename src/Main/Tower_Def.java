package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class Tower_Def extends JFrame implements Runnable {


    private Screen gameScreen;
    private BufferedImage img;

    private int updates;
    private long lastTimeUPS;

    private Thread gameThread;
    private final double FPS_SET=120.00;
    private final double UPS_SET=60.00;
    public Tower_Def() {


        importImg();

        // Program window
        setSize(640, 640);
        setTitle("Tower Def");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        gameScreen = new Screen(img);
        add(gameScreen); // add JPanel in JFrame
        setVisible(true);
    }

    private void importImg(  ) {
        InputStream is = getClass().getResourceAsStream("/Resources/sprite.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        updates++;

       // System.out.println("Game update!");

    }

    public static void main(String[] args) {
        Tower_Def tower_def = new Tower_Def();
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






        while(true){
            //render
        if (System.nanoTime() - lastFrame >= timerPerFrame) {
          lastFrame = System.nanoTime();
           repaint();
           frames++;
           //update
    }if(System.nanoTime()- lastUpdate > timerPerUpdate) {
                updateGame();
                lastUpdate=System.nanoTime();
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

}

