import EntidadesDoJogo.Inimigo;
import EntidadesDoJogo.Player;

import javax.swing.*;

public class InicioDeGame {


    public  InicioDeGame(){

        Player player= new Player();
        new Interface(player);
        new GameLoop(player);
        new Inimigo.Esqueleto();
    }

}
