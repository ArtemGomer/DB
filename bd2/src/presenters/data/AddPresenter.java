package presenters.data;

import panels.data.AddPanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public final class AddPresenter extends BasePresenter {

    private final Vector<JTextField> textFields;

    public AddPresenter(Container container, AddPanel addPanel) {
        super(addPanel, container);
        textFields = new Vector<>();
    }

    public void addTextField(JTextField textField) {
        textFields.add(textField);
    }

    public void addData(String tableName, Vector<ColumnNameType> columnNameTypes) {
        try {
            Vector<String> data = new Vector<>();
            for (JTextField textField : textFields) {
                data.add(textField.getText());
            }
            int number = api.addDataTo(tableName, columnNameTypes, data);
            if (number == 0) {
                onError("Невозможно добавить элемент");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно добавить элемент");
        }
    }

}
