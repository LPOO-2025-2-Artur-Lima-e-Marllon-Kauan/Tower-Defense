package cenas;

import Main.Tower_Def;

import java.awt.*;

public class Settings extends CenasDoJogo implements MetodosCenas{

    public Settings(Tower_Def tower_def){
        super(tower_def);
    }

    @Override
    public void renderizador(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,640,640);
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
