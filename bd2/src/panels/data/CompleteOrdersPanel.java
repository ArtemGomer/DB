package panels.data;

import panels.BasePanel;
import presenters.data.CompleteOrdersPresenter;
import tables.DataTable;
import utils.ColumnNameType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

public class CompleteOrdersPanel extends BasePanel {

    private final Font fontBig = new Font(Font.SERIF, Font.BOLD, 13);
    private final CompleteOrdersPresenter completeOrdersPresenter;
    public DataTable dataTable = null;
    private JScrollPane dataTableScrollPane;

    public CompleteOrdersPanel(Container container) throws SQLException {
        super(container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        completeOrdersPresenter = new CompleteOrdersPresenter(this, container);
        if (!completeOrdersPresenter.getAllDataFrom()) {
            throw new SQLException("Таблица не существует");
        }
        initViews();
    }

    @Override
    protected void initViews() {
        JPanel optionPanel = new JPanel(new FlowLayout());
        optionPanel.setMaximumSize(new Dimension(500, 50));

        JButton completeBtn = new JButton("Выполнить");
        completeBtn.setFont(fontBig);
        completeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 0));
                completeOrdersPresenter.completeOrder(Integer.parseInt((String) dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 0)));
            }
        });

        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Заказы");
        label.setFont(fontBig);
        namePanel.add(label);

        optionPanel.add(completeBtn);
        add(optionPanel);
        add(namePanel);
        add(dataTableScrollPane);
    }

    public void setDataTable(Vector<Vector<String>> data, Vector<ColumnNameType> columnNameTypes) {
        Vector<String> vectorColumnNames = new Vector<>();
        for (ColumnNameType columnNameType: columnNameTypes) {
            vectorColumnNames.add(columnNameType.getName());
        }
        if (dataTable == null) {
            dataTable = new DataTable(data, vectorColumnNames, completeOrdersPresenter);
            dataTable.setEditable(false);
            dataTable.getTableHeader().setReorderingAllowed(false);
            dataTableScrollPane = new JScrollPane(dataTable);
        } else {
            DefaultTableModel model = (DefaultTableModel)dataTable.getModel();
            model.getDataVector().removeAllElements();
            model.setDataVector(data, vectorColumnNames);
            model.fireTableDataChanged();
        }
    }

}
