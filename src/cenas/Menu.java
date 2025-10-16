package cenas;

import Interface.Botao;
import Main.Tower_Def;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import static Main.GameStates.*;

public class Menu extends CenasDoJogo implements MetodosCenas{

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private Random random;


    private Botao bPlaying, bSettings, bSair;

     public Menu(Tower_Def tower_def) {
        super(tower_def);
        random = new Random();
        importImg();
        loadSprites();
        initBotoes();
    }

    private void initBotoes() {
         int l = 150;
         int a = l/3;
         int x = 640/2 - l/2;
         int y = 150;
         int yOffset = 100;

        bPlaying = new Botao("jogar", x, y, l, a);
        bSettings = new Botao("configurações", x, y + yOffset, l, a);
        bSair = new Botao("Sair", x, y + yOffset * 2, l, a);
    }

    @Override
    public void renderizador(Graphics g) {

         desenharBotao(g);
    }

    @Override
    public void mouseClicando(int x, int y) {
        if(bPlaying.getBordas().contains(x, y)){
            SetGameState(PLAYING);
        }
    }

    @Override
    public void mouseMovendo(int x, int y) {
        bPlaying.setMouseOver(false);
        if(bPlaying.getBordas().contains(x, y)){
            bPlaying.setMouseOver(true);
        }
        bSettings.setMouseOver(false);
        if(bSettings.getBordas().contains(x, y)){
            bSettings.setMouseOver(true);
        }
        bSair.setMouseOver(false);
        if(bSair.getBordas().contains(x, y)){
            bSair.setMouseOver(true);
        }
    }

    @Override
    public void mousePressionado(int x, int y) {
        if(bPlaying.getBordas().contains(x, y)){
            bPlaying.setMousePressionado(true);
        }
        else if(bSettings.getBordas().contains(x, y)){
            bSettings.setMousePressionado(true);
        }
        else if(bSair.getBordas().contains(x, y)){
            bSair.setMousePressionado(true);
        }
    }

    @Override
    public void mouseSolto(int x, int y) {
        resetarBotoes();
    }

    private void resetarBotoes() {
         bPlaying.setMousePressionado(false);
         bSettings.setMousePressionado(false);
         bSair.setMousePressionado(false);
    }

    private void desenharBotao(Graphics g){
        bPlaying.desenhar(g);
        bSettings.desenhar(g);
        bSair.desenhar(g);
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
