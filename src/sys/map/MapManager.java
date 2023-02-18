package sys.map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.PlayFrame;
import sys.function.BorderMake;
import sys.function.Game;

import java.awt.*;
import java.util.Random;

public class MapManager extends AbstractMap {
    private Random random = new Random();
    private JLabel flgLabel = new JLabel();
    private JPanel FieldPane = new JPanel();
    private PlayFrame frame;

    public MapManager(int width, int bomb, PlayFrame frame) { 
        super(bomb, width);
        this.frame = frame;

        setFlgLabel();
    }

    public boolean isClear() {
        int open = 0;
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (!Map[y][x].isOpen()) {
                    open++;
                }
            }
        }

        return width*width - open == bomb ? true : false;
    }

    public void FullOpen() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                Map[y][x].setType(Game.FLG);
                if (Map[y][x].getOut() == Game.BOMB) {
                    Map[y][x].Open();
                }
            }
        }
    }

    @Override
    public void Combination(int y, int x) {
        if (1 <= Map[y][x].getOut() ) Map[y][x].Open();
        else if (Map[y][x].getOut() == 0) {
            Map[y][x].Open();
            if (0 <= y-1    && Map[y-1][x].isOpen()) {Combination(y-1,x); } // 上
            if (y+1 < width && Map[y+1][x].isOpen()) {Combination(y+1,x); } // 下
            if (x+1 < width && Map[y][x+1].isOpen()) {Combination(y,x+1); } // 右
            if (0 <= x-1    && Map[y][x-1].isOpen()) {Combination(y,x-1); } // 左

            if (0 <= y-1    && 0 <= x-1    && Map[y-1][x-1].isOpen()) {Combination(y-1,x-1);}
            if (y+1 < width && 0 <= x-1    && Map[y+1][x-1].isOpen()) {Combination(y+1,x-1);}
            if (0 <= y-1    && x+1 < width && Map[y-1][x+1].isOpen()) {Combination(y-1,x+1);}
            if (y+1 < width && x+1 < width && Map[y+1][x+1].isOpen()) {Combination(y+1,x+1);}
        }
        else return;
    }

    @Override
    public void Search(int y, int x) {
        int cnt = 0;

        if (0<=y-1 && 0<=x-1)      {if (Map[y-1][x-1].getOut() == Game.BOMB) cnt++;}
        if (0<=y-1)                {if (Map[y-1][x].getOut()   == Game.BOMB) cnt++;}
        if (0<=y-1 && x+1<width)   {if (Map[y-1][x+1].getOut() == Game.BOMB) cnt++;}
        
        if (0<=x-1)                {if (Map[y][x-1].getOut()   == Game.BOMB) cnt++;}
        if (x+1<width)             {if (Map[y][x+1].getOut()   == Game.BOMB) cnt++;}
    
        if (y+1<width && 0<=x-1)   {if (Map[y+1][x-1].getOut() == Game.BOMB) cnt++;}
        if (y+1<width)             {if (Map[y+1][x].getOut()   == Game.BOMB) cnt++;}
        if (y+1<width && x+1<width){if (Map[y+1][x+1].getOut() == Game.BOMB) cnt++;}
    
        Map[y][x].setOut(cnt);
    }

    @Override
    public JPanel makeMap() {
        int bomb_count = bomb;

        FieldPane.setLayout(new GridLayout(width, width));
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                Map[y][x] = new MapButton(this, y, x);
                FieldPane.add(Map[y][x].getMyButton());
            }
        }

        while (bomb_count > 0) {
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < width; x++) {
                    if (random.nextInt(30) > 28 && bomb_count > 0) {
                        Map[y][x].setOut(Game.BOMB);
                        bomb_count--;
                    }
                }
            }
        }

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (Map[y][x].getOut() != Game.BOMB) Search(y, x);
            }
        }

        FieldPane.setBorder(BorderMake.centerTitleBorder("---------- Minefield ----------", new Color(255,0,0,0)));
        return FieldPane;
    }

    public JLabel getFlgLabel() {
        return flgLabel;
    }

    public void setFlgLabel() {
        flgLabel.setText(String.valueOf(FLG_COUNT));
    }

    public void GameOver() {
        FullOpen();
        frame.setTimeFlg(false);
    }
}