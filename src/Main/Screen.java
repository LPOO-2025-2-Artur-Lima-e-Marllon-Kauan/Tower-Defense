package Main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel {

    private Tower_Def tower_def;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;
    private double timerPerFrame;
    private long lastFrame;


    public Screen(Tower_Def tower_def) {
        this.tower_def = tower_def;

        setPanelSize();




        timerPerFrame = 100000000.0/60.0;

    }

    public void initInputs(){
        myMouseListener = new MyMouseListener(tower_def);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa o painel
        /*if(System.nanoTime() - lastFrame >= timerPerFrame ) {
        lastFrame = System.nanoTime();
        repaint();
        }else{
        }*/
        tower_def.getRenderizador().renderizador(g);


    }

}
