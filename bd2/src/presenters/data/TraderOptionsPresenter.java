package presenters.data;

import com.sun.javaws.exceptions.InvalidArgumentException;
import frames.BaseFrame;
import frames.InfoFrame;
import panels.BasePanel;
import panels.MainMenuPanel;
import panels.data.AddPanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Vector;

public final class TraderOptionsPresenter extends BasePresenter {

    public TraderOptionsPresenter(Container container, BasePanel optionsPanel) {
        super(optionsPanel, container);
    }

    public void openCellsFrame() {
        try {
            openFrame(new InfoFrame("Ячейки", new Dimension(600, 400)), new InfoPanel("Ячейки", container));
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        } catch (InvalidArgumentException ignored) {
        }
    }

    public void exit() {
        api.disconnect();
        openPanel(new MainMenuPanel(container));
    }

}
