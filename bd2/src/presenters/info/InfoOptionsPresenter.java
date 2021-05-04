package presenters.info;

import frames.InfoFrame;
import panels.BasePanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;

import java.awt.*;
import java.sql.SQLException;

public final class InfoOptionsPresenter extends BasePresenter {

    public InfoOptionsPresenter(BasePanel panel, Container container){
        super(panel, container);
    }

    public void openTable(String name) {
        try {
            openFrame(new InfoFrame(name, new Dimension(600, 400)), new InfoPanel(name, container));
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        }
    }

}
