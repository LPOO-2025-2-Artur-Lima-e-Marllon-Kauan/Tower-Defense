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
}
