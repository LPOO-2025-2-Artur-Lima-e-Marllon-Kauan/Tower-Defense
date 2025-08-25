public class Main {
    public static void main(String[] args) {
        Mapa mapa = new Mapa(15,15);

        position pos = new position(0, 0);

        Inimigo inimigo= new Inimigo(10);

        Player player= new Player(10);

        inicioGame(player,pos,inimigo, mapa);

        new Interface();


    }
    public static void inimigoandando(position pos, Mapa mapa, Player player){
        while(pos.x<mapa.Largura) {
            System.out.println("X: " + pos.x + ", Y: " + pos.y);
            pos.x=pos.x+1;

        }
        player.life=player.life-1;
        pos.x=0;
        pos.y=0;
    }
    public static void inicioGame(Player player, position pos, Inimigo inimigo, Mapa mapa){
        while (player.life!=0){
            inimigoandando(pos, mapa,player);

        }

        System.out.println("FIM DE GAME, SUA VIDA:"+ player.life);

    }

}
