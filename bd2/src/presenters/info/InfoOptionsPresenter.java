package presenters.info;

import frames.info.MyInfoFrame;
import panels.info.InfoOptionsPanel;
import panels.info.InfoPanel;

import java.awt.*;

public class InfoOptionsPresenter {

    private final InfoOptionsPanel panel;

    public InfoOptionsPresenter(InfoOptionsPanel panel){
        this.panel = panel;
    }

    public void openInfoFrame(String tableName) {
        try {
            MyInfoFrame myInfoFrame = new MyInfoFrame(tableName, new Dimension(600, 400), new InfoPanel(tableName));
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно открыть таблицу");
        }

    }

    public void onError(String message) {
        panel.showMessageDialog(message);
    }

}
