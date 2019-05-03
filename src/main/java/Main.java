import gui.MainGui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainGui mainGui = new MainGui();
        mainGui.setMinimumSize(new Dimension(800, 600));
        mainGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGui.setVisible(true);
    }
}
