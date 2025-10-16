package Interface;

import java.awt.*;

public class Botao {

    private int x, y, largura, altura;
    private String texto;
    private Rectangle bordas;
    private boolean mouseOver, mousePressionado;

    public Botao(String texto, int x, int y, int largura, int altura){
        this.texto = texto;
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;

        initBordas();
    }

    private void initBordas(){
        this.bordas = new Rectangle(x, y, largura, altura);
    }

    public void desenhar(Graphics g){
        //corpo do botão
        desenharCorpo(g);

        //borda do botão
        desenharBorda(g);

        //texto do botão
        desenharTexto(g);
    }

    private void desenharBorda(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, largura, altura);
        if(mousePressionado){
            g.drawRect(x + 1, y + 1, largura - 2, altura - 2);
            g.drawRect(x + 2, y + 2, largura - 4, altura - 4);
        }


    }

    private void desenharCorpo(Graphics g) {
        if(mouseOver)
            g.setColor(Color.gray);
        else
            g.setColor(Color.white);
        g.fillRect(x,y, largura, altura);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public void setMousePressionado(boolean mousePressionado){
        this.mousePressionado = mousePressionado;
    }

    private void desenharTexto(Graphics g) {
        int l = g.getFontMetrics().stringWidth(texto);
        int a = g.getFontMetrics().getHeight();
        g.drawString(texto, x - l / 2 + largura / 2, y + a / 2 + altura / 2);

    }

    public Rectangle getBordas(){
        return bordas;
    }
}