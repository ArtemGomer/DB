package presenters.data;

import frames.data.AddFrame;
import panels.MainMenuPanel;
import panels.data.AddPanel;
import panels.data.CustomerOptionsPanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import java.awt.*;
import java.sql.Types;
import java.util.Arrays;
import java.util.Vector;

public final class CustomerOptionsPresenter extends BasePresenter {

    public CustomerOptionsPresenter(Container container, CustomerOptionsPanel optionsPanel) {
        super(optionsPanel, container);
    }

    public void openAddOrderFrame() {
        Vector<ColumnNameType> fields = new Vector<>(Arrays.asList(
                new ColumnNameType("id", Types.INTEGER),
                new ColumnNameType("имя", Types.VARCHAR),
                new ColumnNameType("тип", Types.VARCHAR),
                new ColumnNameType("количество", Types.INTEGER),
                new ColumnNameType("дата_заказа", Types.TIMESTAMP)
        ));
        AddFrame frame = new AddFrame();
        openFrame(frame, new AddPanel("Заказы", fields, frame.getContentPane()));
    }

    public void exit() {
        api.disconnect();
        openPanel(new MainMenuPanel(container));
    }

}
