package sys.function;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PngMake {
    public static Icon Make(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static Icon Make(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        return new ImageIcon(image);
    }
}
