package presenters.data;

import frames.BaseFrame;
import frames.InfoFrame;
import panels.MainMenuPanel;
import panels.data.AddPanel;
import panels.data.CustomerOptionsPanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import java.awt.*;
import java.sql.SQLException;
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
        BaseFrame frame = new InfoFrame("Заказы", new Dimension(400, 300));
        openFrame(frame, new AddPanel("Заказы", fields, frame.getContentPane()));
    }

    public void openOrdersFrame() {
        try {
            openFrame(new InfoFrame("Заказы", new Dimension(600, 400)), new InfoPanel("Заказы", container));
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Невозможно посмотреть заказы");
        }
    }

    public void exit() {
        api.disconnect();
        openPanel(new MainMenuPanel(container));
    }

}
