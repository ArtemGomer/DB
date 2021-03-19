package panels;

import presenters.DataTablePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class DataTablePanel extends JPanel {

    private final DataTablePresenter dataTablePresenter;
    private JScrollPane dataTable;

    public DataTablePanel(Container container, String tableName) {
        //TODO different constructors
        dataTablePresenter = new DataTablePresenter(container, this);
        dataTable = new JScrollPane(new JTable());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews(tableName);
    }

    private void initViews(String tableName) {
        JPanel optionPanel = new JPanel(new FlowLayout());
        optionPanel.setMaximumSize(new Dimension(500, 50));

        JLabel label = new JLabel(tableName);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        optionPanel.add(label);

        //TODO items in JComboBox
        optionPanel.add(new JComboBox<>(new String[]{"item1", "item2"}));
        optionPanel.add(new JComboBox<>(new String[]{"item3", "item4"}));

        JButton findBtn = new JButton("Find");
        findBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        findBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO queries
                super.mouseClicked(e);
                dataTablePresenter.getAllDataFrom(tableName);
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
        add(dataTable);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<String> columns) {
        remove(dataTable);
        JTable table = new JTable(data, columns);
        table.getTableHeader().setReorderingAllowed(false);
        dataTable = new JScrollPane(new JTable(data, columns));
        add(dataTable);
        revalidate();
    }

}
