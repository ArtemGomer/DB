package presenters.data;

import frames.data.TableFrame;
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
            openFrame(new TableFrame(name), new DataTablePanel(name, container));
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        }
    }

}
