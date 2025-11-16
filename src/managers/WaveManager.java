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
        // 10 waves progressivas, aumentando quantidade, vida (via escala) e velocidade (via escala)
        // Tipos: 0=Orc, 1=Bat, 2=Knight, 3=Wolf

        // Wave 1 - apenas Orcs, bem simples
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ))));

        // Wave 2 - mais Orcs e alguns Bats rápidos
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1
        ))));

        // Wave 3 - mistura forte de Orc + Bat
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0
        ))));

        // Wave 4 - introduz Wolf (rápido)
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3
        ))));

        // Wave 5 - mais inimigos rápidos (Bat + Wolf)
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                1, 1, 1, 0, 0, 3, 1, 1, 0, 3, 1, 1, 0, 3, 1
        ))));

        // Wave 6 - introduz Knight (muita vida)
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                0, 0, 2, 0, 0, 3, 0, 2, 0, 3, 0, 2, 0, 3
        ))));

        // Wave 7 - Wolves constantes com suporte de Bats e um Knight final
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                3, 0, 1, 3, 0, 1, 3, 0, 1, 3, 0, 1, 2
        ))));

        // Wave 8 - Wave pesada com vários Knights e Wolves
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                3, 3, 1, 1, 0, 0, 3, 3, 1, 1, 0, 0, 2, 2
        ))));

        // Wave 9 - muita pressão de Wolves e Knights
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                3, 1, 3, 1, 3, 1, 3, 1, 2, 0, 2, 0, 2, 0, 2, 0
        ))));

        // Wave 10 - wave final muito difícil (muitos rápidos e tanques)
        this.waves.add(new Wave(new ArrayList(Arrays.asList(
                3, 3, 3, 1, 1, 1, 2, 2, 2, 0, 0, 0, 3, 3, 3, 1, 1, 1, 2, 2, 2
        ))));
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
