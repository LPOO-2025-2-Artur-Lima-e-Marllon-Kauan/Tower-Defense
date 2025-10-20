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
    public ArrayList<Tile> roadsS = new ArrayList();
    public ArrayList<Tile> roadsC = new ArrayList();
    public ArrayList<Tile> corners = new ArrayList();
    public ArrayList<Tile> beaches = new ArrayList();
    public ArrayList<Tile> islands = new ArrayList();

    public TileManager() {
        this.loadAtalas();
        this.createTiles();
    }

    private void createTiles() {
        int id = 0;
        this.tiles.add(this.GRASS = new Tile(this.getSprite(9, 0), id++, 1));
        this.tiles.add(this.WATER = new Tile(this.getAniSprites(0, 0), id++, 0));
        this.roadsS.add(this.ROAD_LR = new Tile(this.getSprite(8, 0), id++, 2));
        this.roadsS.add(this.ROAD_TB = new Tile(ImgFix.getRotImg(this.getSprite(8, 0), 90), id++, 2));
        this.roadsC.add(this.ROAD_B_TO_R = new Tile(this.getSprite(7, 0), id++, 2));
        this.roadsC.add(this.ROAD_L_TO_B = new Tile(ImgFix.getRotImg(this.getSprite(7, 0), 90), id++, 2));
        this.roadsC.add(this.ROAD_L_TO_T = new Tile(ImgFix.getRotImg(this.getSprite(7, 0), 180), id++, 2));
        this.roadsC.add(this.ROAD_T_TO_R = new Tile(ImgFix.getRotImg(this.getSprite(7, 0), 270), id++, 2));
        this.corners.add(this.BL_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(5, 0), 0), id++, 0));
        this.corners.add(this.TL_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(5, 0), 90), id++, 0));
        this.corners.add(this.TR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(5, 0), 180), id++, 0));
        this.corners.add(this.BR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(5, 0), 270), id++, 0));
        this.beaches.add(this.T_WATER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(6, 0), 0), id++, 0));
        this.beaches.add(this.R_WATER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(6, 0), 90), id++, 0));
        this.beaches.add(this.B_WATER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(6, 0), 180), id++, 0));
        this.beaches.add(this.L_WATER = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(6, 0), 270), id++, 0));
        this.islands.add(this.TL_ISLE = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(4, 0), 0), id++, 0));
        this.islands.add(this.TR_ISLE = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(4, 0), 90), id++, 0));
        this.islands.add(this.BR_ISLE = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(4, 0), 180), id++, 0));
        this.islands.add(this.BL_ISLE = new Tile(ImgFix.getBuildRotImg(this.getAniSprites(0, 0), this.getSprite(4, 0), 270), id++, 0));
        this.tiles.addAll(this.roadsS);
        this.tiles.addAll(this.roadsC);
        this.tiles.addAll(this.corners);
        this.tiles.addAll(this.beaches);
        this.tiles.addAll(this.islands);
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

    public BufferedImage getAniSprite(int id, int animationIndex) {
        return ((Tile)this.tiles.get(id)).getSprite(animationIndex);
    }

    private BufferedImage[] getAniSprites(int xCord, int yCord) {
        BufferedImage[] arr = new BufferedImage[4];

        for(int i = 0; i < 4; ++i) {
            arr[i] = this.getSprite(xCord + i, yCord);
        }

        return arr;
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return this.atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    public boolean isSpriteAnimation(int spriteID) {
        return ((Tile)this.tiles.get(spriteID)).isAnimation();
    }

    public ArrayList<Tile> getRoadsS() {
        return this.roadsS;
    }

    public ArrayList<Tile> getRoadsC() {
        return this.roadsC;
    }

    public ArrayList<Tile> getCorners() {
        return this.corners;
    }

    public ArrayList<Tile> getBeaches() {
        return this.beaches;
    }

    public ArrayList<Tile> getIslands() {
        return this.islands;
    }
}
