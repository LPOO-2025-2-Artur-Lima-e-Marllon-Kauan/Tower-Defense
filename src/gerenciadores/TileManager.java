package gerenciadores;

import level.LoadSave;
import objetos.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager  {

    public Tile GRAMA, AGUA, CAMINHO;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager(){
        carregarAtlas();
        criarTiles();
    }

    private void criarTiles() {
        tiles.add(GRAMA = new Tile(getSprite(8, 1)));  //ID 0
        tiles.add(AGUA = new Tile(getSprite(0, 6)));   //ID 1
        tiles.add(CAMINHO = new Tile(getSprite(9, 0))); //ID 2
    }

    private void carregarAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }
}
