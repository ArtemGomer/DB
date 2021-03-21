package frames;

import panels.AddPanel;
import presenters.DataTablePresenter;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AddFrame extends JFrame {

    private final String tableName;
    private final DataTablePresenter dataTablePresenter;

    public AddFrame(String tableName, Vector<String> columnNames, DataTablePresenter dataTablePresenter) {
        this.tableName = tableName;
        this.dataTablePresenter = dataTablePresenter;
        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initViews(columnNames);
        setVisible(true);
    }

    private void initViews(Vector<String> columnNames) {
        AddPanel addPanel = new AddPanel(tableName, columnNames, dataTablePresenter);
        add(new JScrollPane(addPanel));
    }

}
