package cenas;

import Main.Tower_Def;

public class CenasDoJogo {

    private Tower_Def tower_def;
    public CenasDoJogo(Tower_Def tower_def){
        this.tower_def = tower_def;
    }

    public Tower_Def getTower_def(){
        return tower_def;
    }

}
