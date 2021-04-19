package presenters.info;

import frames.data.TableFrame;
import panels.data.DataTablePanel;
import panels.info.InfoOptionsPanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;

import java.awt.*;

public final class InfoOptionsPresenter extends BasePresenter {

    public InfoOptionsPresenter(InfoOptionsPanel panel, Container container){
        super(panel, container);
    }

    public void openTable(String name) {
        try {
            openFrame(new TableFrame(name), new InfoPanel(name, container));
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        }
    }

}
