package frames.data;

import frames.BaseFrame;
import panels.data.AddPanel;
import panels.BasePanel;
import utils.ColumnNameType;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public final class AddFrame extends BaseFrame {

    private final String tableName;
    private final Vector<ColumnNameType> columnNameTypes;

    public AddFrame(String tableName, Vector<ColumnNameType> columnNameTypes) {
        super("Добавить данные");
        this.tableName = tableName;
        this.columnNameTypes = columnNameTypes;

        initViews();
        setVisible(true);
    }

    @Override
    public void openPanel(BasePanel panel) {
        getContentPane().removeAll();
        getContentPane().add(new JScrollPane(panel));
        getContentPane().revalidate();
        panel.requestFocus();
    }

    @Override
    protected void initViews() {
        setSize(new Dimension(400, 300));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        openPanel(new AddPanel(tableName, columnNameTypes, getContentPane()));
    }
}
