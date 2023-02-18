package sys.map;

import javax.swing.JButton;
import java.awt.event.*;
import sys.function.Game;

public abstract class AbstractButton extends MouseAdapter implements ActionListener {
    protected int y;
    protected int x;
    protected int out;
    protected int type = Game.CLOSE;
    protected JButton myButton;
    protected MapManager manager;
    protected final String FLG_PNG_NAME = "./lib/FLG.png";

    public AbstractButton(MapManager manager, int y, int x) {
        this.manager = manager;
        this.y = y;
        this.x = x;

        myButton = new JButton("");
        myButton.addMouseListener(this);
        myButton.addActionListener(this);
    }

    
    public JButton getMyButton() {
        return myButton;
    }
    
    public void setOut(int out) {
        this.out = out;
    }
    
    public int getOut() {
        return out;
    }
    
    public void setType(int type) {
        this.type = type;
    }

    public abstract void Open();
    public abstract boolean isOpen();
}
