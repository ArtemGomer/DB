package presenters.info;

import frames.info.InfoFrame;
import panels.info.InfoOptionsPanel;

public class InfoOptionsPresenter {

    private final InfoOptionsPanel panel;

    public InfoOptionsPresenter(InfoOptionsPanel panel){
        this.panel = panel;
    }

    public void openInfoFrame(String tableName) {
        try {
            InfoFrame frame = new InfoFrame(tableName);
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Can not open table");
        }

    }

    public void onError(String message) {
        panel.showMessageDialog(message);
    }

}
