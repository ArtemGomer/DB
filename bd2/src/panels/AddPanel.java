package panels;

import presenters.AddPresenter;
import presenters.DataTablePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AddPanel extends JPanel {

    private final AddPresenter addPresenter;
    private final String tableName;
    private final DataTablePresenter dataTablePresenter;

    public AddPanel(String tableName, Vector<String> columnNames, DataTablePresenter dataTablePresenter) {
        addPresenter = new AddPresenter(this);
        this.tableName = tableName;
        this.dataTablePresenter = dataTablePresenter;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews(columnNames);
    }

    public void initViews(Vector<String> columnNames) {
        for (String columnName: columnNames) {
            JPanel panel = new JPanel();
            panel.setMaximumSize(new Dimension(400, 50));
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            JLabel label = new JLabel(columnName);
            label.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            label.setMaximumSize(new Dimension(200, 50));

            JTextField textField = new JTextField();
            textField.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            textField.setMaximumSize(new Dimension(200, 50));

            panel.add(label);
            panel.add(textField);
            addPresenter.addTextField(textField);
            add(panel);
        }

        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(500, 220));
        panel.setLayout(new FlowLayout());

        JButton commitBtn = new JButton("commit");
        commitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addPresenter.addData(tableName, dataTablePresenter);
            }
        });

        panel.add(commitBtn);
        add(panel);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }



}
