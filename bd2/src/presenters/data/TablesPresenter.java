package presenters.data;

import frames.data.TableFrame;
import panels.data.TablesPanel;


public class TablesPresenter {

    private final TablesPanel tablesPanel;

    public TablesPresenter(TablesPanel tablesPanel) {
        this.tablesPanel = tablesPanel;
    }

    public void onError(String message) {
        tablesPanel.showMessageDialog(message);
    }

    public void openDataTable(String tableName) {
        Runnable runnable = () -> {
            try {
                TableFrame tableFrame = new TableFrame(tableName);
            } catch (Exception ex) {
                onError("Невозможно открыть таблицу");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
