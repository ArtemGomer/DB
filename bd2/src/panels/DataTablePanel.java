package panels;

import customTables.DataTable;
import presenters.DataTablePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Vector;

public class DataTablePanel extends JPanel {

    private final DataTablePresenter dataTablePresenter;
    private JScrollPane dataTable;
    private final String tableName;

    public DataTablePanel(Container container, String tableName) {
        this.tableName = tableName;
        dataTablePresenter = new DataTablePresenter(container, this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews(tableName);
    }

    private void initViews(String tableName) {
        JPanel optionPanel = new JPanel(new FlowLayout());
        optionPanel.setMaximumSize(new Dimension(500, 50));

        JLabel label = new JLabel(tableName);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        optionPanel.add(label);

        JComboBox<String> sortingComboBox = new JComboBox<>(new String[]{"no", "asc", "desc"});
        JComboBox<String> sortingByComboBox = dataTablePresenter.setSortingByData(tableName);
        sortingComboBox.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        sortingByComboBox.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        optionPanel.add(sortingComboBox);
        optionPanel.add(sortingByComboBox);

        JButton findBtn = new JButton("Find");
        findBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        findBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO queries
                super.mouseClicked(e);
                dataTablePresenter.getAllDataFrom(Objects.requireNonNull(sortingComboBox.getSelectedItem()).toString(),
                        Objects.requireNonNull(sortingByComboBox.getSelectedItem()).toString(),
                        tableName);
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

        optionPanel.add(findBtn);
        optionPanel.add(backBtn);
        add(optionPanel);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<String> columns) {
        if (dataTable != null) {
            remove(dataTable);
        }
        JTable table = new DataTable(tableName, data, columns, dataTablePresenter);
        table.getTableHeader().setReorderingAllowed(false);
        dataTable = new JScrollPane(table);
        add(dataTable);
        revalidate();
    }

    public JComboBox<String> setSortingByComboBox(String[] data) {
        return new JComboBox<>(data);
    }

}
