package frames;

import database.DatabaseApi;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MainFrame extends BaseFrame {
    public MainFrame() {
        super("Магазин деталей");
        initViews();
    }

    @Override
    protected void initViews() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseApi.getInstance().disconnect();
            }
        });
        setResizable(false);
    }

}
