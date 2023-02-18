package sys.map;

import java.awt.*;
import java.awt.event.*;

import sys.function.BorderMake;
import sys.function.Game;
import sys.function.PngMake;

public class MapButton extends AbstractButton {

    public MapButton(MapManager manager, int y, int x) {
        super(manager, y, x);

        myButton.setPreferredSize(new Dimension( Game.PNG_WIDTH,Game.PNG_HEIGHT));
        myButton.setBackground(Color.lightGray);
        myButton.setBorder(BorderMake.RaisedBorder());
    }

    private void Combination() {
        manager.Combination(y, x);
    }

    @Override
    public void Open() {
        if (type != Game.OPEN) {
            if (type == Game.FLG) {
                manager.FLG_COUNT++;
                manager.setFlgLabel(); 
            }
            type = Game.OPEN;
            myButton.setIcon(PngMake.Make(Game.PNG_WIDTH, Game.PNG_HEIGHT, "./lib/" + out + ".png"));
            myButton.setContentAreaFilled(false);
            myButton.setBackground(Color.white);
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (e.getButton() == 3 && type != Game.OPEN) {
            if (type == Game.CLOSE) {
                myButton.setIcon(PngMake.Make(Game.PNG_WIDTH, Game.PNG_HEIGHT, FLG_PNG_NAME));
                myButton.setBackground(Color.white);
                manager.FLG_COUNT--;
                type = Game.FLG;
                
            } else if (type == Game.FLG) {
                myButton.setBackground(Color.lightGray);
                myButton.setIcon(null);
                manager.FLG_COUNT++;
                type = Game.CLOSE;
            }
            manager.setFlgLabel();
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton && type != Game.FLG) {
            if (out == Game.BOMB) {
                manager.GameOver();
            } else {
                Combination();
            }
        }
    }

    @Override
    public boolean isOpen() {
        return type == Game.OPEN ? false: true;
    }
}
