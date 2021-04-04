import frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", Color.WHITE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        MainFrame mainFrame = new MainFrame();
    }
}
