package panels.data;

import panels.BasePanel;
import presenters.data.AddPresenter;
import utils.ColumnNameType;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Types;
import java.util.Vector;

public final class AddPanel extends BasePanel {

    private final AddPresenter addPresenter;
    private final String tableName;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 20);
    private final Vector<ColumnNameType> columnNameTypes;

    public AddPanel(String tableName, Vector<ColumnNameType> columnNameTypes, Container container) {
        super(container);
        addPresenter = new AddPresenter(container, this);
        this.tableName = tableName;
        this.columnNameTypes = columnNameTypes;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    public void initViews() {
        for (int i = 1; i < columnNameTypes.size(); i++) {
            JPanel panel = new JPanel();
            panel.setMaximumSize(new Dimension(400, 50));
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            JLabel label = new JLabel(columnNameTypes.get(i).getName());
            label.setFont(font);
            label.setMaximumSize(new Dimension(200, 50));

            JTextField textField = null;
            if (columnNameTypes.get(i).getType() != Types.TIMESTAMP) {
                textField = new JTextField();
            } else {
                try {
                    MaskFormatter maskFormatter = new MaskFormatter("##-##-##");
                    textField = new JFormattedTextField(maskFormatter);
                } catch (Exception ignore) {
                }
            }
            textField.setFont(font);
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
                addPresenter.addData(tableName, columnNameTypes);
            }
        });

        panel.add(commitBtn);
        add(panel);
    }

}
