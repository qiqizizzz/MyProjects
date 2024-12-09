package snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame=new JFrame("贪吃蛇游戏");
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
