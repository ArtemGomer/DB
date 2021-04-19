package panels.info;

import tables.DataTable;
import panels.BasePanel;
import presenters.info.InfoPresenter;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Vector;

public class InfoPanel extends BasePanel {

    private final InfoPresenter presenter;
    private final String tableName;

    public InfoPanel(String tableName, Container container) throws SQLException {
        super(container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.presenter = new InfoPresenter(this, container);
        this.tableName = tableName;
        initViews();
    }

    public void initViews() throws SQLException {
        presenter.setTable(tableName);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<String> columnNames) {
        DataTable dataTable = new DataTable(data, columnNames, null);
        dataTable.setEditable(false);
        dataTable.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(dataTable));
    }

    public void openDealersAndDetailsTypeChooser(Vector<String> items) throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>(items);
        JOptionPane.showConfirmDialog(null, comboBox, "Выберите тип", JOptionPane.DEFAULT_OPTION);
        presenter.setOneItemTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString(), tableName);
    }

    public void openSellsChooser(Vector<String> items) throws SQLException {
        JComboBox<String> comboBox1 = new JComboBox<>(items);
        JFormattedTextField jFormattedTextField1 = new JFormattedTextField();
        jFormattedTextField1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(comboBox1);
        panel.add(jFormattedTextField1);
        JOptionPane.showConfirmDialog(null, panel, "Выберите", JOptionPane.DEFAULT_OPTION);
        presenter.setTwoItemsTable(Objects.requireNonNull(comboBox1.getSelectedItem()).toString(), jFormattedTextField1.getText(), tableName);
    }

}
