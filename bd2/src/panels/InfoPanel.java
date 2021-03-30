package panels;

import customTables.DataTable;
import presenters.InfoPresenter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class InfoPanel extends JPanel {

    private final InfoPresenter presenter;
    private final String tableName;

    public InfoPanel(String tableName) throws SQLException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.presenter = new InfoPresenter(this);
        this.tableName = tableName;
        initViews();
    }

    public void initViews() throws SQLException {
        presenter.setTable(tableName);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<String> columnNames) {
        System.out.println(columnNames);
        DataTable dataTable = new DataTable(data, columnNames, null);
        dataTable.setEditable(false);
        dataTable.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(dataTable));
    }

    public void openTypeChooser(Vector<String> items) throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>(items);
        int pressed = JOptionPane.showConfirmDialog(null, comboBox, "Choose type", JOptionPane.DEFAULT_OPTION);
        if (pressed == JOptionPane.OK_OPTION) {
            presenter.setDealersTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString(), tableName);
        }
    }

}
