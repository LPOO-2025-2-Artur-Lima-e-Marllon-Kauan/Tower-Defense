//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import helpz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;

public class TileManager {
    public Tile GRASS;
    public Tile WATER;
    public Tile ROAD;
    private BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList();

    public TileManager() {
        this.loadAtalas();
        this.createTiles();
    }

    private void createTiles() {
        this.tiles.add(this.GRASS = new Tile(this.getSprite(8, 1)));
        this.tiles.add(this.WATER = new Tile(this.getSprite(0, 6)));
        this.tiles.add(this.ROAD = new Tile(this.getSprite(9, 0)));
    }

    private void loadAtalas() {
        this.atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return ((Tile)this.tiles.get(id)).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return this.atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }
}
