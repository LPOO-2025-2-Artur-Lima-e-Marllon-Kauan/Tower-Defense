package cenas;

import Main.Tower_Def;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends CenasDoJogo implements MetodosCenas{

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private Random random;

     public Menu(Tower_Def tower_def) {
        super(tower_def);
        random = new Random();
        importImg();
        loadSprites();
    }

    @Override
    public void renderizador(Graphics g) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(getRNDInt()), x * 32, y * 32, null);
            }
        }
    }

    private void importImg(  ) {
        InputStream is = getClass().getResourceAsStream("/Resources/sprite.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }


    private int getRNDInt() {
        return random.nextInt(100);
    }


}
