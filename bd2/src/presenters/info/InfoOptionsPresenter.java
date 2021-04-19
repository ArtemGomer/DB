package presenters.info;

import com.sun.javaws.exceptions.InvalidArgumentException;
import frames.InfoFrame;
import panels.info.InfoOptionsPanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;

import java.awt.*;
import java.sql.SQLException;

public final class InfoOptionsPresenter extends BasePresenter {

    public InfoOptionsPresenter(InfoOptionsPanel panel, Container container){
        super(panel, container);
    }

    public void openTable(String name) {
        try {
            openFrame(new InfoFrame(name, new Dimension(600, 400)), new InfoPanel(name, container));
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        } catch (InvalidArgumentException ignored) {
        }
    }

}
