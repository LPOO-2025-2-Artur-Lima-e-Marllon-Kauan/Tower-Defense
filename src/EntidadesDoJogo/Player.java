package EntidadesDoJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public float life;
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura,largura;

    public Player(){
        this.x=100;
        this.y=100;

    }
    public void load(){
        ImageIcon referencia = new ImageIcon("imagens\\Dead (1).png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update(){
        x +=dx;
        y+=dy;

    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo==KeyEvent.VK_UP){
            dy = -3;
        }
        if(codigo==KeyEvent.VK_DOWN){
            dy = 3;
        }
        if(codigo==KeyEvent.VK_LEFT){
            dx=-3;
        }
        if(codigo==KeyEvent.VK_RIGHT){
            dx=3;
        }

    }
    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo==KeyEvent.VK_UP){
            dy = 0;
        }
        if(codigo==KeyEvent.VK_DOWN){
            dy = 0;
        }
        if(codigo==KeyEvent.VK_LEFT){
            dx=0;
        }
        if(codigo==KeyEvent.VK_RIGHT){
            dx=0;
        }

    }
public Player(float life){
    this.life=life;

}
}
