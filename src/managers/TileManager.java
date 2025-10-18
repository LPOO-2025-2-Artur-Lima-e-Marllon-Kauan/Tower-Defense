//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package managers;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;

public class TileManager {
    public Tile GRASS;
    public Tile WATER;
    public Tile ROAD_LR;
    public Tile ROAD_TB;
    public Tile ROAD_B_TO_R;
    public Tile ROAD_L_TO_B;
    public Tile ROAD_L_TO_T;
    public Tile ROAD_T_TO_R;
    public Tile BL_WATER_CORNER;
    public Tile TL_WATER_CORNER;
    public Tile TR_WATER_CORNER;
    public Tile BR_WATER_CORNER;
    public Tile T_WATER;
    public Tile R_WATER;
    public Tile B_WATER;
    public Tile L_WATER;
    public Tile TL_ISLE;
    public Tile TR_ISLE;
    public Tile BR_ISLE;
    public Tile BL_ISLE;
    private BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList();

    public TileManager() {
        this.loadAtalas();
        this.createTiles();
    }

    private void createTiles() {
        int id = 0;
        this.tiles.add(this.GRASS = new Tile(this.getSprite(9, 0), id++, "Grass"));
        this.tiles.add(this.WATER = new Tile(this.getSprite(0, 0), id++, "Water"));
        this.tiles.add(this.ROAD_LR = new Tile(this.getSprite(8, 0), id++, "Road_LR"));
        this.tiles.add(this.BL_WATER_CORNER = new Tile(ImgFix.buildImg(this.getImgs(0, 0, 5, 0)), id++, "BL_Water_Corner"));
        this.tiles.add(this.TL_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getImgs(0, 0, 5, 0), 90, 1), id++, "TL_Water_Corner"));
        this.tiles.add(this.TR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getImgs(0, 0, 5, 0), 180, 1), id++, "TL_Water_Corner"));
        this.tiles.add(this.BR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getImgs(0, 0, 5, 0), 270, 1), id++, "TL_Water_Corner"));
        this.tiles.add(this.T_WATER = new Tile(ImgFix.buildImg(this.getImgs(0, 0, 6, 0)), id++, "T_Water"));
    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{this.getSprite(firstX, firstY), this.getSprite(secondX, secondY)};
    }

    private void loadAtalas() {
        this.atlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return (Tile)this.tiles.get(id);
    }

    public BufferedImage getSprite(int id) {
        return ((Tile)this.tiles.get(id)).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return this.atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }
}
