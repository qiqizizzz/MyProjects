package snake;

import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL headURL=Data.class.getResource("image/header.png");
    public static ImageIcon header=new ImageIcon(headURL);

    public static URL upURL=Data.class.getResource("image/up.png");
    public static URL downURL=Data.class.getResource("image/down.png");
    public static URL leftURL=Data.class.getResource("image/left.png");
    public static URL rightURL=Data.class.getResource("image/right.png");
    public static ImageIcon up=new ImageIcon(upURL);
    public static ImageIcon down=new ImageIcon(downURL);
    public static ImageIcon left=new ImageIcon(leftURL);
    public static ImageIcon right=new ImageIcon(rightURL);

    public static URL bodyURL=Data.class.getResource("image/body.png");
    public static ImageIcon body=new ImageIcon(bodyURL);

    public static URL foodURL=Data.class.getResource("image/food.png");
    public static ImageIcon food=new ImageIcon(foodURL);
}
