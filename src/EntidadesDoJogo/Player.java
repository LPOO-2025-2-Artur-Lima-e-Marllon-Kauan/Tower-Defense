package EntidadesDoJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public int life;
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura,largura;

    public Player(){
        life=4;
        this.x=100;
        this.y=100;

    }

//metodo para causar dano
public  void TakeDamage() {
    this.life -= 1;
    if(this.life < 0) this.life = 0;
}
        }


