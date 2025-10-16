package cenas;

import Main.Tower_Def;

import java.awt.*;

public class Playing extends CenasDoJogo implements MetodosCenas{

    public Playing(Tower_Def tower_def){
        super(tower_def);
    }

                   @Override
    public void renderizador(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0,0,640,640);
    }
}
