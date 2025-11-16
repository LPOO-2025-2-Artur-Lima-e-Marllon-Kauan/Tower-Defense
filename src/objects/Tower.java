//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objects;

import helpz.Constants.Towers;

/**
 * Representa uma torre no jogo
 * Tipos: 0=Cannon (lento, alto dano), 1=Archer (rápido, baixo dano), 2=Wizard (aplica lentidão)
 */
public class Tower {
    private int x; // Posição X em pixels
    private int y; // Posição Y em pixels
    private int id; // Identificador único
    private int towerType; // Tipo: 0=Cannon, 1=Archer, 2=Wizard, 3=Cospe Veneno
    private int cdTick; // Contador atual de cooldown
    private int dmg; // Dano por ataque
    private float range; // Alcance em pixels
    private float cooldown; // Tempo entre ataques (em ticks)
    private int level; // Nível atual da torre (1 a 3)

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        this.level = 1;
        this.setDefaultDmg();
        this.setDefaultRange();
        this.setDefaultCooldown();
    }

    /**
     * Atualiza a torre a cada frame
     * Incrementa contador de cooldown
     */
    public void update() {
        ++this.cdTick;
    }

    /**
     * Verifica se a torre pode atacar novamente
     */
    public boolean isCooldownOver() {
        return (float)this.cdTick >= this.cooldown;
    }

    /**
     * Reinicia o cooldown após um ataque
     */
    public void resetCooldown() {
        this.cdTick = 0;
    }

    private void setDefaultCooldown() {
        this.cooldown = Towers.GetDefaultCooldown(this.towerType);
    }

    private void setDefaultRange() {
        this.range = Towers.GetDefaultRange(this.towerType);
    }

    private void setDefaultDmg() {
        this.dmg = Towers.GetStartDmg(this.towerType);
    }

    /**
     * Aumenta o nível da torre (até 3) e melhora seus atributos.
     * - Dano aumenta 40% por nível
     * - Alcance aumenta 20% por nível
     * - Cooldown reduz 15% por nível (atira mais rápido)
     */
    public void upgrade() {
        if (this.level >= 3) {
            return;
        }

        this.level++;
        this.dmg = Math.round(this.dmg * 1.4f);
        this.range *= 1.2F;
        this.cooldown *= 0.85F;
    }

    public boolean canUpgrade() {
        return this.level < 3;
    }

    public int getLevel() {
        return this.level;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return this.towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getDmg() {
        return this.dmg;
    }

    public float getRange() {
        return this.range;
    }

    public float getCooldown() {
        return this.cooldown;
    }
}
