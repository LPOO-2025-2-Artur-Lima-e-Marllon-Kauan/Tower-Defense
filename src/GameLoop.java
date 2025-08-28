import EntidadesDoJogo.Player;

public class GameLoop {



    public  GameLoop (Player player){

        while (Player.isAlive(player.life)) {
            player.TakeDamage();

    }
}


    }