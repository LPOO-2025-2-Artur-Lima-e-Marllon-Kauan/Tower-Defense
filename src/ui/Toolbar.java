//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.GameStates;
import objects.Tile;
import scenes.Editing;

/**
 * Toolbar do editor de mapas (modo Editing)
 * Permite selecionar tiles para pintar no mapa: grama, água, estradas, marcadores de path
 * Inclui botões para salvar e voltar ao menu
 */
public class Toolbar extends Bar {
    private Editing editing;
    private MyButton bMenu; // Botão voltar ao menu
    private MyButton bSave; // Botão salvar mapa
    private MyButton bPathStart; // Botão para marcar início do caminho
    private MyButton bPathEnd; // Botão para marcar fim do caminho
    private BufferedImage pathStart; // Sprite do marcador de início
    private BufferedImage pathEnd; // Sprite do marcador de fim
    private Tile selectedTile; // Tile atualmente selecionado para pintar
    private Map<MyButton, ArrayList<Tile>> map = new HashMap(); // Mapeia botões para variações de tiles
    private MyButton bGrass; // Botão de grama
    private MyButton bWater; // Botão de água
    private MyButton bRoadS; // Botão de estradas retas
    private MyButton bRoadC; // Botão de curvas de estrada
    private MyButton bWaterC; // Botão de cantos de água
    private MyButton bWaterB; // Botão de praias
    private MyButton bWaterI; // Botão de ilhas
    private MyButton currentButton; // Botão rotativo atual
    private int currentIndex = 0; // Índice da variação atual do tile

    public Toolbar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        this.initPathImgs();
        this.initButtons();
    }

    private void initPathImgs() {
        this.pathStart = LoadSave.getSpriteAtlas().getSubimage(224, 64, 32, 32);
        this.pathEnd = LoadSave.getSpriteAtlas().getSubimage(256, 64, 32, 32);
    }

    /**
     * Inicializa todos os botões da toolbar
     * Organização:
     * - Linha superior: Menu, Save, tiles básicos (grama, água), variações de tiles
     * - Linha inferior: PathStart e PathEnd
     */
    private void initButtons() {
        this.bMenu = new MyButton("Menu", 2, 642, 100, 30);
        this.bSave = new MyButton("Save", 2, 674, 100, 30);
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int)((float)w * 1.1F);
        int i = 0;
        
        // Botões de tiles simples
        this.bGrass = new MyButton("Grass", xStart, yStart, w, h, i++);
        this.bWater = new MyButton("Water", xStart + xOffset, yStart, w, h, i++);
        
        // Botões de tiles com variações (podem ser rotacionados com R)
        this.initMapButton(this.bRoadS, this.editing.getGame().getTileManager().getRoadsS(), xStart, yStart, xOffset, w, h, i++);
        this.initMapButton(this.bRoadC, this.editing.getGame().getTileManager().getRoadsC(), xStart, yStart, xOffset, w, h, i++);
        this.initMapButton(this.bWaterC, this.editing.getGame().getTileManager().getCorners(), xStart, yStart, xOffset, w, h, i++);
        this.initMapButton(this.bWaterB, this.editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, w, h, i++);
        this.initMapButton(this.bWaterI, this.editing.getGame().getTileManager().getIslands(), xStart, yStart, xOffset, w, h, i++);
        
        // Botões de marcadores de caminho
        this.bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, i++);
        this.bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, i++);
    }

    private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
        b = new MyButton("", x + xOff * id, y, w, h, id);
        this.map.put(b, list);
    }

    private void saveLevel() {
        this.editing.saveLevel();
    }

    /**
     * Rotaciona o tile selecionado (tecla R)
     * Percorre as variações disponíveis do tile atual
     * Ex: estrada reta pode ter 4 rotações diferentes
     */
    public void rotateSprite() {
        ++this.currentIndex;
        // Volta ao início se passar do último
        if (this.currentIndex >= ((ArrayList)this.map.get(this.currentButton)).size()) {
            this.currentIndex = 0;
        }

        this.selectedTile = (Tile)((ArrayList)this.map.get(this.currentButton)).get(this.currentIndex);
        this.editing.setSelectedTile(this.selectedTile);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(this.x, this.y, this.width, this.height);
        this.drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        this.bMenu.draw(g);
        this.bSave.draw(g);
        this.drawPathButton(g, this.bPathStart, this.pathStart);
        this.drawPathButton(g, this.bPathEnd, this.pathEnd);
        this.drawNormalButton(g, this.bGrass);
        this.drawNormalButton(g, this.bWater);
        this.drawSelectedTile(g);
        this.drawMapButtons(g);
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, (ImageObserver)null);
        this.drawButtonFeedback(g, b);
    }

    private void drawNormalButton(Graphics g, MyButton b) {
        g.drawImage(this.getButtImg(b.getId()), b.x, b.y, b.width, b.height, (ImageObserver)null);
        this.drawButtonFeedback(g, b);
    }

    private void drawMapButtons(Graphics g) {
        for(Map.Entry<MyButton, ArrayList<Tile>> entry : this.map.entrySet()) {
            MyButton b = (MyButton)entry.getKey();
            BufferedImage img = ((Tile)((ArrayList)entry.getValue()).get(0)).getSprite();
            g.drawImage(img, b.x, b.y, b.width, b.height, (ImageObserver)null);
            this.drawButtonFeedback(g, b);
        }

    }

    /**
     * Desenha prévia do tile selecionado no canto direito da toolbar
     * Mostra qual tile será pintado ao clicar no mapa
     */
    private void drawSelectedTile(Graphics g) {
        if (this.selectedTile != null) {
            g.drawImage(this.selectedTile.getSprite(), 550, 650, 50, 50, (ImageObserver)null);
            g.setColor(Color.black);
            g.drawRect(550, 650, 50, 50);
        }

    }

    public BufferedImage getButtImg(int id) {
        return this.editing.getGame().getTileManager().getSprite(id);
    }

    /**
     * Gerencia cliques nos botões da toolbar
     * - Menu: volta ao menu
     * - Save: salva o mapa atual
     * - Tiles: seleciona tile para pintar
     * - PathStart/End: marca início/fim do caminho dos inimigos
     */
    public void mouseClicked(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            GameStates.SetGameState(GameStates.MENU);
        } else if (this.bSave.getBounds().contains(x, y)) {
            this.saveLevel();
        } else {
            // Tiles simples
            if (this.bWater.getBounds().contains(x, y)) {
                this.selectedTile = this.editing.getGame().getTileManager().getTile(this.bWater.getId());
                this.editing.setSelectedTile(this.selectedTile);
                return;
            }

            if (this.bGrass.getBounds().contains(x, y)) {
                this.selectedTile = this.editing.getGame().getTileManager().getTile(this.bGrass.getId());
                this.editing.setSelectedTile(this.selectedTile);
                return;
            }

            // Marcadores de caminho (IDs especiais: -1 e -2)
            if (this.bPathStart.getBounds().contains(x, y)) {
                this.selectedTile = new Tile(this.pathStart, -1, -1);
                this.editing.setSelectedTile(this.selectedTile);
            } else if (this.bPathEnd.getBounds().contains(x, y)) {
                this.selectedTile = new Tile(this.pathEnd, -2, -2);
                this.editing.setSelectedTile(this.selectedTile);
            } else {
                // Tiles com variações (podem ser rotacionados)
                for(MyButton b : this.map.keySet()) {
                    if (b.getBounds().contains(x, y)) {
                        this.selectedTile = (Tile)((ArrayList)this.map.get(b)).get(0);
                        this.editing.setSelectedTile(this.selectedTile);
                        this.currentButton = b; // Guarda para rotação
                        this.currentIndex = 0;  // Reseta índice
                        return;
                    }
                }
            }
        }

    }

    public void mouseMoved(int x, int y) {
        this.bMenu.setMouseOver(false);
        this.bSave.setMouseOver(false);
        this.bWater.setMouseOver(false);
        this.bGrass.setMouseOver(false);
        this.bPathStart.setMouseOver(false);
        this.bPathEnd.setMouseOver(false);

        for(MyButton b : this.map.keySet()) {
            b.setMouseOver(false);
        }

        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMouseOver(true);
        } else if (this.bSave.getBounds().contains(x, y)) {
            this.bSave.setMouseOver(true);
        } else if (this.bWater.getBounds().contains(x, y)) {
            this.bWater.setMouseOver(true);
        } else if (this.bGrass.getBounds().contains(x, y)) {
            this.bGrass.setMouseOver(true);
        } else if (this.bPathStart.getBounds().contains(x, y)) {
            this.bPathStart.setMouseOver(true);
        } else if (this.bPathEnd.getBounds().contains(x, y)) {
            this.bPathEnd.setMouseOver(true);
        } else {
            for(MyButton b : this.map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }

    }

    public void mousePressed(int x, int y) {
        if (this.bMenu.getBounds().contains(x, y)) {
            this.bMenu.setMousePressed(true);
        } else if (this.bSave.getBounds().contains(x, y)) {
            this.bSave.setMousePressed(true);
        } else if (this.bWater.getBounds().contains(x, y)) {
            this.bWater.setMousePressed(true);
        } else if (this.bGrass.getBounds().contains(x, y)) {
            this.bGrass.setMousePressed(true);
        } else if (this.bPathStart.getBounds().contains(x, y)) {
            this.bPathStart.setMousePressed(true);
        } else if (this.bPathEnd.getBounds().contains(x, y)) {
            this.bPathEnd.setMousePressed(true);
        } else {
            for(MyButton b : this.map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }

    }

    public void mouseReleased(int x, int y) {
        this.bMenu.resetBooleans();
        this.bSave.resetBooleans();
        this.bGrass.resetBooleans();
        this.bWater.resetBooleans();
        this.bPathStart.resetBooleans();
        this.bPathEnd.resetBooleans();

        for(MyButton b : this.map.keySet()) {
            b.resetBooleans();
        }

    }

    public BufferedImage getStartPathImg() {
        return this.pathStart;
    }

    public BufferedImage getEndPathImg() {
        return this.pathEnd;
    }
}
