//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private int id;
    private String name;

    public Tile(BufferedImage sprite, int id, String name) {
        this.sprite = sprite;
        this.id = id;
        this.name = name;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
