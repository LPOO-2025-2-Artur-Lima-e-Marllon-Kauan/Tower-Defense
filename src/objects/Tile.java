//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage[] sprite;
    private int id;
    private int tileType;

    public Tile(BufferedImage sprite, int id, int tileType) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.tileType = tileType;
    }

    public Tile(BufferedImage[] sprite, int id, int tileType) {
        this.sprite = sprite;
        this.id = id;
        this.tileType = tileType;
    }

    public int getTileType() {
        return this.tileType;
    }

    public BufferedImage getSprite(int animationIndex) {
        return this.sprite[animationIndex];
    }

    public BufferedImage getSprite() {
        return this.sprite[0];
    }

    public boolean isAnimation() {
        return this.sprite.length > 1;
    }

    public int getId() {
        return this.id;
    }
}
