package panels.data;

import customTables.DataTable;
import frames.data.AddFrame;
import presenters.data.DataTablePresenter;
import utils.ColumnNameType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class DataTablePanel extends JPanel {

    private final DataTablePresenter dataTablePresenter;
    public DataTable dataTable = null;
    private final String tableName;
    private Vector<ColumnNameType> columnNameTypes;
    private JScrollPane dataTableScrollPane;

    private final Font fontBig = new Font(Font.SERIF, Font.BOLD, 15);

    public DataTablePanel(String tableName) throws SQLException {
        this.tableName = tableName;
        dataTablePresenter = new DataTablePresenter(this, tableName);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (!dataTablePresenter.getAllDataFrom(null, null, tableName)) {
            throw new SQLException("Таблица не существует");
        }
        initViews(tableName);
    }

    private void initViews(String tableName) {
        JPanel optionPanel = new JPanel(new FlowLayout());
        optionPanel.setMaximumSize(new Dimension(500, 50));

        JComboBox<String> sortingComboBox = new JComboBox<>(new String[]{"no", "asc", "desc"});
        JComboBox<String> sortingByComboBox = dataTablePresenter.setSortingByData(columnNameTypes);
        sortingComboBox.setFont(fontBig);
        sortingByComboBox.setFont(fontBig);
        optionPanel.add(sortingComboBox);
        optionPanel.add(sortingByComboBox);

        JButton refreshBtn = new JButton("Обновить");
        refreshBtn.setFont(fontBig);
        refreshBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.getAllDataFrom(Objects.requireNonNull(sortingComboBox.getSelectedItem()).toString(),
                        Objects.requireNonNull(sortingByComboBox.getSelectedItem()).toString(),
                        tableName);
            }
        });

        JButton addBtn = new JButton("Добавить");
        addBtn.setFont(fontBig);
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.openAddFrame(columnNameTypes);
            }
        });

        JButton deleteBtn = new JButton("Удалить");
        deleteBtn.setFont(fontBig);
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.deleteSelectedRows(dataTable.getSelectedRows());
            }
        });

        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(tableName);
        label.setFont(fontBig);
        namePanel.add(label);

        optionPanel.add(refreshBtn);
        optionPanel.add(addBtn);
        optionPanel.add(deleteBtn);
        add(optionPanel);
        add(namePanel);
        add(dataTableScrollPane);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<ColumnNameType> columnNameTypes) {
        Vector<String> vectorColumnNames = new Vector<>();
        for (ColumnNameType columnNameType: columnNameTypes) {
            vectorColumnNames.add(columnNameType.getName());
        }
        if (dataTable == null) {
            this.columnNameTypes = columnNameTypes;
            dataTable = new DataTable(data, vectorColumnNames, dataTablePresenter);
            dataTable.getTableHeader().setReorderingAllowed(false);
            dataTableScrollPane = new JScrollPane(dataTable);
        } else {
            DefaultTableModel model = (DefaultTableModel)dataTable.getModel();
            model.getDataVector().removeAllElements();
            model.setDataVector(data, vectorColumnNames);
            model.fireTableDataChanged();
        }
    }

    public JComboBox<String> setSortingByComboBox(String[] data) {
        return new JComboBox<>(data);
    }

    public void openAddFrame(Vector<ColumnNameType> columnNameTypes) {
        AddFrame addFrame = new AddFrame(tableName, columnNameTypes);
    }

}
