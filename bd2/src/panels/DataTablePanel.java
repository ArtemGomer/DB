package panels;

import customTables.DataTable;
import frames.AddFrame;
import presenters.DataTablePresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Vector;

public class DataTablePanel extends JPanel {

    private final DataTablePresenter dataTablePresenter;
    public DataTable dataTable = null;
    private final String tableName;
    private Vector<String> columnNames;
    private JScrollPane dataTableScrollPane;

    public DataTablePanel(Container container, String tableName) {
        this.tableName = tableName;
        dataTablePresenter = new DataTablePresenter(container, this, tableName);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dataTablePresenter.getAllDataFrom(null, null, tableName);
        initViews(tableName);
    }

    private void initViews(String tableName) {
        JPanel optionPanel = new JPanel(new FlowLayout());
        optionPanel.setMaximumSize(new Dimension(500, 50));

        JComboBox<String> sortingComboBox = new JComboBox<>(new String[]{"no", "asc", "desc"});
        JComboBox<String> sortingByComboBox = dataTablePresenter.setSortingByData(columnNames);
        sortingComboBox.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        sortingByComboBox.setFont(new Font(Font.SERIF, Font.BOLD, 10));
        optionPanel.add(sortingComboBox);
        optionPanel.add(sortingByComboBox);

        JButton findBtn = new JButton("Find");
        findBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        findBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.getAllDataFrom(Objects.requireNonNull(sortingComboBox.getSelectedItem()).toString(),
                        Objects.requireNonNull(sortingByComboBox.getSelectedItem()).toString(),
                        tableName);
            }
        });

        JButton addBtn = new JButton("Add");
        addBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.openAddFrame(columnNames);
            }
        });

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.deleteSelectedRows(dataTable.getSelectedRows());
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataTablePresenter.back();
            }
        });

        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(tableName);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        namePanel.add(label);

        optionPanel.add(findBtn);
        optionPanel.add(addBtn);
        optionPanel.add(deleteBtn);
        optionPanel.add(backBtn);
        add(optionPanel);
        add(namePanel);
        add(dataTableScrollPane);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<String> columns) {
        if (dataTable == null) {
            columnNames = columns;
            dataTable = new DataTable(data, columnNames, dataTablePresenter);
            dataTable.getTableHeader().setReorderingAllowed(false);
            dataTableScrollPane = new JScrollPane(dataTable);
        } else {
            DefaultTableModel model = (DefaultTableModel)dataTable.getModel();
            model.getDataVector().removeAllElements();
            model.setDataVector(data, columns);
            model.fireTableDataChanged();
        }
    }

    public JComboBox<String> setSortingByComboBox(String[] data) {
        return new JComboBox<>(data);
    }

    public void openAddFrame(Vector<String> columnNames) {
        AddFrame addFrame = new AddFrame(tableName, columnNames, dataTablePresenter);
    }

}
