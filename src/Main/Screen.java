package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel {
    private Random random;
    private BufferedImage img;


    private double timerPerFrame;
    private long lastFrame;


    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Screen(BufferedImage img) {
        this.img = img;
        loadSprites();
        random = new Random();

        timerPerFrame = 100000000.0/60.0;

    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa o painel

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(getRNDInt()), x * 32, y * 32, null);
            }
        }

if(System.nanoTime() - lastFrame >= timerPerFrame ) {
lastFrame = System.nanoTime();
repaint();
}else{

}


    }



    private int getRNDInt() {
        return random.nextInt(100);
    }

    private Color getRndColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
