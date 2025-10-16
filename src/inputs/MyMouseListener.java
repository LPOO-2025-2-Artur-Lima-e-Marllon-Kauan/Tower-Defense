package inputs;

import Main.GameStates;
import Main.Tower_Def;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Tower_Def tower_def;
    public MyMouseListener(Tower_Def tower_def){
        this.tower_def = tower_def;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameStates){
                case PLAYING:

                    break;
                case MENU:
                    tower_def.getMenu().mouseClicando(e.getX(), e.getY());
                    break;
                case SETTINGS:

                    break;

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameStates){
            case PLAYING:

                break;
            case MENU:
                tower_def.getMenu().mousePressionado(e.getX(), e.getY());
                break;
            case SETTINGS:

                break;

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameStates){
            case PLAYING:

                break;
            case MENU:
                tower_def.getMenu().mouseSolto(e.getX(), e.getY());
                break;
            case SETTINGS:

                break;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameStates){
            case PLAYING:
                tower_def.getPlaying().mouseMovendo(e.getX(), e.getY());
                break;
            case MENU:
                tower_def.getMenu().mouseMovendo(e.getX(), e.getY());
                break;
            case SETTINGS:
                tower_def.getSettings().mouseMovendo(e.getX(), e.getY());
                break;

        }
    }
}
