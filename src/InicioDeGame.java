import EntidadesDoJogo.Inimigo;
import EntidadesDoJogo.Player;

import javax.swing.*;

public class InicioDeGame {


    public  InicioDeGame(){
        //Cria o player.
        Player player= new Player();
        //Cria a interface.
        new Interface(player);

    }

}
