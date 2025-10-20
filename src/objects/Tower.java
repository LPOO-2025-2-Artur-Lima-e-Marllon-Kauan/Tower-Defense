//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objects;

import helpz.Constants.Towers;

public class Tower {
    private int x;
    private int y;
    private int id;
    private int towerType;
    private float dmg;
    private float range;
    private float cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        this.setDefaultDmg();
        this.setDefaultRange();
        this.setDefaultCooldown();
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

    public float getDmg() {
        return this.dmg;
    }

    public float getRange() {
        return this.range;
    }

    public float getCooldown() {
        return this.cooldown;
    }
}
