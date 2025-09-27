package EntidadesDoJogo;

import javax.swing.*;

public class Inimigo {
public int dano;
public int vida;
public int velocidade;
public int posicaox,posicaoy;
public JLabel sprite;
public ImageIcon iconeOriginal;  //Imagem original
public ImageIcon iconeRedimensionado;    //Imagem remodelada
     public static class Esqueleto extends Inimigo{

         public Esqueleto() {

             //Passa a sprite e atualiza o tamanho dela:
             iconeOriginal = new ImageIcon(getClass().getResource("/imagens/Dead (1).png"));
             iconeRedimensionado = new ImageIcon(iconeOriginal.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            //Transforma a imagem salvada em componente visual da janela.
             sprite = new JLabel(iconeRedimensionado);
             sprite.setBounds(posicaox, posicaoy,   iconeRedimensionado.getIconWidth(), iconeRedimensionado.getIconHeight());

             this.dano = 1;
             this.vida = 2;
             this.velocidade = 3;
             this.posicaox = 0;
             this.posicaoy = 0;



         }

     }

public static class Goblin extends Inimigo{
         public Goblin() {
             this.dano = 2;
             this.vida = 5;
             this.velocidade = 3;
             this.posicaox = -560;
             this.posicaoy = -300;



             //Passa a sprite e atualiza o tamanho dela:
             iconeOriginal = new ImageIcon(getClass().getResource("/imagens/combie.png"));
             iconeRedimensionado = new ImageIcon(iconeOriginal.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
             //Transforma a imagem salvada em componente visual da janela.
             sprite = new JLabel(iconeRedimensionado);
             sprite.setBounds(posicaox, posicaoy,   iconeRedimensionado.getIconWidth(), iconeRedimensionado.getIconHeight());


         }
    }

    public void addToPane(JPanel panel) {
        panel.add(sprite);
    }
    //Metodo que atualiza a posição em Y.
    public void updatePosition() {
        posicaoy += velocidade; // 1Incrementa a posição Y do inimigo
        sprite.setLocation(posicaox, posicaoy);// Move o JLabel para a nova posição
    }

    public void resetPosition() {
        // Reseta a posição para renascer.
        if (this instanceof Inimigo.Esqueleto) {
            this.posicaox = 0;
            this.posicaoy = 0;
        } else if (this instanceof Inimigo.Goblin) {
            this.posicaox = -560;
            this.posicaoy = -300;
        }

        // Atualiza visualmente o sprite
        this.sprite.setLocation(this.posicaox, this.posicaoy);
    }


}


