package EntidadesDoJogo;

import javax.swing.*;

public class Inimigo {
public int dano;
public int vida;
public int velocidade;
public int posicaox,posicaoy;
public JLabel sprite;
public ImageIcon iconeOriginal;
public ImageIcon iconeRedimensionado;
     public static class Esqueleto extends Inimigo{

         public Esqueleto() {
             iconeOriginal = new ImageIcon(getClass().getResource("/imagens/Dead (1).png"));
             iconeRedimensionado = new ImageIcon(iconeOriginal.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

             this.dano = 1;
             this.vida = 2;
             this.velocidade = 2;
             this.posicaox = 200;
             this.posicaoy = 0;
             sprite = new JLabel(iconeRedimensionado);
             sprite.setBounds(posicaox, posicaoy,   iconeRedimensionado.getIconWidth(), iconeRedimensionado.getIconHeight());


         }

     }

public static class Goblin extends Inimigo{
         public Goblin() {
             iconeOriginal = new ImageIcon(getClass().getResource("/imagens/combie.png"));
             iconeRedimensionado = new ImageIcon(iconeOriginal.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

             this.dano = 2;
             this.vida = 5;
             this.velocidade = 1;
             this.posicaox = 200;
             this.posicaoy = 0;
         }
    }

    public void addToPane(JPanel panel) {
        panel.add(sprite);
    }
    public void updatePosition() {
        posicaoy += velocidade;
        sprite.setLocation(posicaox, posicaoy);
    }

}


