package presenters.data;

import frames.InfoFrame;
import panels.data.DataTablePanel;
import panels.data.TablesPanel;
import presenters.BasePresenter;

import java.awt.*;


public final class TablesPresenter extends BasePresenter {


    public TablesPresenter(TablesPanel tablesPanel, Container container) {
        super(tablesPanel, container);
    }

    public void openTable(String name) {
        try {
            openFrame(new InfoFrame(name, new Dimension(600, 400)), new DataTablePanel(name, container));
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        }
    }

}
