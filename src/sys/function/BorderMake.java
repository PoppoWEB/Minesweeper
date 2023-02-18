package sys.function;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BorderMake {
    public static Border lowBorder() {
        return BorderFactory.createLoweredBevelBorder();
    }

    public static Border RaisedBorder() {
        return BorderFactory.createRaisedBevelBorder();
    }

    public static Border spaceBorder(int top, int left, int bottm, int right) {
        return BorderFactory.createEmptyBorder(top, left, bottm, right);
    }

    public static Border centerTitleBorder(String title, Color color) {
        return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color)
        , title,
        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
    }
}
