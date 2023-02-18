package sys.map;

import javax.swing.JPanel;

public abstract class AbstractMap {
    protected int bomb;
    protected int width;
    protected int FLG_COUNT;
    protected MapButton Map[][];

    public AbstractMap(int bomb, int width) {
        this.bomb = bomb;
        this.width = width;
        this.FLG_COUNT = bomb;
        this.Map = new MapButton[width][width];
    }

    public abstract void Combination(int y, int x);
    public abstract void Search(int y, int x);
    public abstract JPanel makeMap();
}
