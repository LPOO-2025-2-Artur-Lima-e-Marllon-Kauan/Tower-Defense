//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import events.Wave;
import java.util.ArrayList;
import java.util.Arrays;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList();
    private int enemySpawnTickLimit = 60;
    private int enemySpawnTick;
    private int enemyIndex;
    private int waveIndex;
    private int waveTickLimit;
    private int waveTick;
    private boolean waveStartTimer;
    private boolean waveTickTimerOver;

    public WaveManager(Playing playing) {
        this.enemySpawnTick = this.enemySpawnTickLimit;
        this.waveTickLimit = 300;
        this.waveTick = 0;
        this.playing = playing;
        this.createWaves();
    }

    public void update() {
        if (this.enemySpawnTick < this.enemySpawnTickLimit) {
            ++this.enemySpawnTick;
        }

        if (this.waveStartTimer) {
            ++this.waveTick;
            if (this.waveTick >= this.waveTickLimit) {
                this.waveTickTimerOver = true;
            }
        }

    }

    public void increaseWaveIndex() {
        ++this.waveIndex;
        this.waveTickTimerOver = false;
        this.waveStartTimer = false;
    }

    public boolean isWaveTimerOver() {
        return this.waveTickTimerOver;
    }

    public void startWaveTimer() {
        this.waveStartTimer = true;
    }

    public int getNextEnemy() {
        this.enemySpawnTick = 0;
        return (Integer)((Wave)this.waves.get(this.waveIndex)).getEnemyList().get(this.enemyIndex++);
    }

    private void createWaves() {
        this.waves.add(new Wave(new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 1))));
        this.waves.add(new Wave(new ArrayList(Arrays.asList(2, 0, 0, 0, 0, 0, 0, 0, 0, 1))));
    }

    public ArrayList<Wave> getWaves() {
        return this.waves;
    }

    public boolean isTimeForNewEnemy() {
        return this.enemySpawnTick >= this.enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave() {
        return this.enemyIndex < ((Wave)this.waves.get(this.waveIndex)).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return this.waveIndex + 1 < this.waves.size();
    }

    public void resetEnemyIndex() {
        this.enemyIndex = 0;
    }

    public int getWaveIndex() {
        return this.waveIndex;
    }

    public float getTimeLeft() {
        float ticksLeft = (float)(this.waveTickLimit - this.waveTick);
        return ticksLeft / 60.0F;
    }

    public boolean isWaveTimerStarted() {
        return this.waveStartTimer;
    }
}
