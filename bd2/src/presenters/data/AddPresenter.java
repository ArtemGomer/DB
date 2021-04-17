package presenters.data;

import database.DatabaseApi;
import panels.data.AddPanel;
import utils.ColumnNameType;

import javax.swing.*;
import java.util.Vector;

public class AddPresenter {

    private final DatabaseApi api;
    private final Vector<JTextField> textFields;
    private final AddPanel addPanel;

    public AddPresenter(AddPanel addPanel) {
        this.addPanel = addPanel;
        api = DatabaseApi.getInstance();
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

    public void onError(String message) {
        addPanel.showMessageDialog(message);
    }


}
