package cenas;

import Main.Tower_Def;
import gerenciadores.TileManager;
import level.ConstrutorDeNivel;

import java.awt.*;

public class Playing extends CenasDoJogo implements MetodosCenas{

    private int[][] nivel;
    private TileManager tileManager;

    public Playing(Tower_Def tower_def){
        super(tower_def);

        nivel = ConstrutorDeNivel.getNivelData();
        tileManager = new TileManager();
    }

    @Override
    public void renderizador(Graphics g) {

        for(int y = 0; y < nivel.length; y++){
            for(int x = 0; x < nivel[y].length; x++){
                int id = nivel[y][x];
                g.drawImage(tileManager.getSprite(id), x* 32, y * 32,  null);
            }
        }
    }

    @Override
    public void mouseClicando(int x, int y) {

    }

    @Override
    public void mouseMovendo(int x, int y) {

    }

    @Override
    public void mousePressionado(int x, int y) {

    }

    @Override
    public void mouseSolto(int x, int y) {

    }
}
