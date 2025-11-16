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
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList();
    private ArrayList<Explosion> explosions = new ArrayList();
    private BufferedImage[] proj_imgs;
    private BufferedImage[] explo_imgs;
    private int proj_id = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        this.importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        this.proj_imgs = new BufferedImage[4];

        for(int i = 0; i < 3; ++i) {
            this.proj_imgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
        }

        // Projétil de veneno reutiliza o sprite de CHAINS para simplificar
        this.proj_imgs[3] = this.proj_imgs[1];

        this.importExplosion(atlas);
    }

    private void importExplosion(BufferedImage atlas) {
        this.explo_imgs = new BufferedImage[7];

        for(int i = 0; i < 7; ++i) {
            this.explo_imgs[i] = atlas.getSubimage(i * 32, 64, 32, 32);
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

        float rotate = 0.0F;
        if (type == 0) {
            float arcValue = (float)Math.atan((double)((float)yDist / (float)xDist));
            rotate = (float)Math.toDegrees((double)arcValue);
            if (xDist < 0) {
                rotate += 180.0F;
            }
        }

        this.projectiles.add(new Projectile((float)(t.getX() + 16), (float)(t.getY() + 16), xSpeed, ySpeed, t.getDmg(), rotate, this.proj_id++, type));
    }

    public void update() {
        for(Projectile p : this.projectiles) {
            if (p.isActive()) {
                p.move();
                if (this.isProjHittingEnemy(p)) {
                    p.setActive(false);
                    if (p.getProjectileType() == 2) {
                        this.explosions.add(new Explosion(p.getPos()));
                        this.explodeOnEnemies(p);
                    }
                }
            }
        }

        for(Explosion e : this.explosions) {
            if (e.getIndex() < 7) {
                e.update();
            }
        }

    }

    private void explodeOnEnemies(Projectile p) {
        for(Enemy e : this.playing.getEnemyManger().getEnemies()) {
            if (e.isAlive()) {
                float radius = 40.0F;
                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());
                float realDist = (float)Math.hypot((double)xDist, (double)yDist);
                if (realDist <= radius) {
                    e.hurt(p.getDmg());
                }
            }
        }

    }

    private boolean isProjHittingEnemy(Projectile p) {
        for(Enemy e : this.playing.getEnemyManger().getEnemies()) {
            if (e.isAlive() && e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                if (p.getProjectileType() == 1) {
                    e.slow();
                } else if (p.getProjectileType() == 3) {
                    e.applyPoison();
                }

                return true;
            }
        }

        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for(Projectile p : this.projectiles) {
            if (p.isActive()) {
                if (p.getProjectileType() == 0) {
                    g2d.translate((double)p.getPos().x, (double)p.getPos().y);
                    g2d.rotate(Math.toRadians((double)p.getRotation()));
                    g2d.drawImage(this.proj_imgs[p.getProjectileType()], -16, -16, (ImageObserver)null);
                    g2d.rotate(-Math.toRadians((double)p.getRotation()));
                    g2d.translate((double)(-p.getPos().x), (double)(-p.getPos().y));
                } else {
                    g2d.drawImage(this.proj_imgs[p.getProjectileType()], (int)p.getPos().x - 16, (int)p.getPos().y - 16, (ImageObserver)null);
                }
            }
        }

        this.drawExplosions(g2d);
    }

    private void drawExplosions(Graphics2D g2d) {
        for(Explosion e : this.explosions) {
            if (e.getIndex() < 7) {
                g2d.drawImage(this.explo_imgs[e.getIndex()], (int)e.getPos().x - 16, (int)e.getPos().y - 16, (ImageObserver)null);
            }
        }

    }

    private int getProjType(Tower t) {
        switch (t.getTowerType()) {
            case 0 -> {
                return 2;
            }
            case 1 -> {
                return 0;
            }
            case 2 -> {
                return 1;
            }
            case 3 -> {
                return 3; // Torre Cospe Veneno usa projétil de veneno
            }
            default -> {
                return 0;
            }
        }
    }

    public class Explosion {
        private Point2D.Float pos;
        private int exploTick;
        private int exploIndex;

        public Explosion(Point2D.Float pos) {
            this.pos = pos;
        }

        public void update() {
            ++this.exploTick;
            if (this.exploTick >= 6) {
                this.exploTick = 0;
                ++this.exploIndex;
            }

        }

        public int getIndex() {
            return this.exploIndex;
        }

        public Point2D.Float getPos() {
            return this.pos;
        }
    }
}
