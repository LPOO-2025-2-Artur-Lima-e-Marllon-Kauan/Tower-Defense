package managers;

import eventos.Wave;
import scenes.Playing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60;
    private int enemySpawnTIck = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;

    public WaveManager(Playing playing){
        this.playing = playing;
        criarWaves();
    }

    public void update(){
        if(enemySpawnTIck < enemySpawnTickLimit)
            enemySpawnTIck++;
    }

    public int getNextEnemy(){
        enemySpawnTIck = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void criarWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,1))));
    }

    public ArrayList<Wave>  getWaves(){
        return waves;
    }

    public boolean isThereMoreWaves(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTIck >= enemySpawnTickLimit;
    }
}
