package cenas;

import java.awt.*;

public interface MetodosCenas {

    public void renderizador(Graphics g);
    public void mouseClicando(int x, int y);
    public void mouseMovendo(int x, int y);
    public void mousePressionado(int x, int y);
    public void mouseSolto(int x, int y);

}
