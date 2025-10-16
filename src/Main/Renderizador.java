package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Renderizador {

    private Tower_Def tower_def;

    public Renderizador(Tower_Def tower_def){
        this.tower_def = tower_def;
    }

    public void renderizador(Graphics g){
        switch(GameStates.gameStates){

            case MENU:
                tower_def.getMenu().renderizador(g);
                break;
            case PLAYING:
                tower_def.getPlaying().renderizador(g);
                break;
            case SETTINGS:
                tower_def.getSettings().renderizador(g);
                break;


        }

    }




}
