//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private int id;
    private int projectileType;
    private boolean active = true;

    public Projectile(float x, float y, int id, int projectileType) {
        this.pos = new Point2D.Float(x, y);
        this.id = id;
        this.projectileType = projectileType;
    }

    public void move(float x, float y) {
        Point2D.Float var10000 = this.pos;
        var10000.x += x;
        var10000 = this.pos;
        var10000.y += y;
    }

    public Point2D.Float getPos() {
        return this.pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return this.id;
    }

    public int getProjectileType() {
        return this.projectileType;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
