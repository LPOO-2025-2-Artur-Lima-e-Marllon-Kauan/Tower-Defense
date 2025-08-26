package EntidadesDoJogo;

public class Inimigo {
public int dano;
public int vida;
public int velocidade;
public int posicaox;
public int posicaoy;

     public static class Esqueleto extends Inimigo{

         public Esqueleto() {
             this.dano = 1;
             this.vida = 2;
             this.velocidade = 3;
             this.posicaox = 0;
             this.posicaoy = 2;
         }
     }

public static class Goblin extends Inimigo{
         public Goblin() {
             this.dano = 2;
             this.vida = 5;
             this.velocidade = 4;
             this.posicaox = 0;
             this.posicaoy = 0;
         }
    }
}
