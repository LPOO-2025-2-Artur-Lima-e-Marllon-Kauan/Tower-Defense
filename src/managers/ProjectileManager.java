//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import enemies.Enemy;
import helpz.LoadSave;
import helpz.Constants.Projectiles;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        this.importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        this.proj_imgs = new BufferedImage[3];

        for(int i = 0; i < 3; ++i) {
            this.proj_imgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
        }

    }

    public void newProjectile(Tower t, Enemy e) {
        int type = this.getProjType(t);
        int xDist = (int)((float)t.getX() - e.getX());
        int yDist = (int)((float)t.getY() - e.getY());
        int totDist = Math.abs(xDist) + Math.abs(yDist);
        float xPer = (float)Math.abs(xDist) / (float)totDist;
        float xSpeed = xPer * Projectiles.GetSpeed(type);
        float ySpeed = Projectiles.GetSpeed(type) - xSpeed;
        if ((float)t.getX() > e.getX()) {
            xSpeed *= -1.0F;
        }

        if ((float)t.getY() > e.getY()) {
            ySpeed *= -1.0F;
        }

        float arcValue = (float)Math.atan((double)((float)yDist / (float)xDist));
        float rotate = (float)Math.toDegrees((double)arcValue);
        if (xDist < 0) {
            rotate += 180.0F;
        }

        this.projectiles.add(new Projectile((float)(t.getX() + 16), (float)(t.getY() + 16), xSpeed, ySpeed, t.getDmg(), rotate, this.proj_id++, type));
    }

    public void update() {
        for(Projectile p : this.projectiles) {
            if (p.isActive()) {
                p.move();
                if (this.isProjHittingEnemy(p)) {
                    p.setActive(false);
                }
            }
        }

    }

    private boolean isProjHittingEnemy(Projectile p) {
        for(Enemy e : this.playing.getEnemyManger().getEnemies()) {
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }

        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for(Projectile p : this.projectiles) {
            if (p.isActive()) {
                g2d.translate((double)p.getPos().x, (double)p.getPos().y);
                g2d.rotate(Math.toRadians((double)p.getRotation()));
                g2d.drawImage(this.proj_imgs[p.getProjectileType()], -16, -16, (ImageObserver)null);
                g2d.rotate(-Math.toRadians((double)p.getRotation()));
                g2d.translate((double)(-p.getPos().x), (double)(-p.getPos().y));
            }
        }

    }

    private int getProjType(Tower t) {
        switch (t.getTowerType()) {
            case 0 -> {
                return 1;
            }
            case 1 -> {
                return 0;
            }
            case 2 -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }
}
