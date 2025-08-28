import EntidadesDoJogo.Player;

public class InicioDeGame {


    public  InicioDeGame(){
        Player player= new Player();
        new GameLoop(player);

    }

}
