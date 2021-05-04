package presenters;

import database.DatabaseApi;
import frames.BaseFrame;
import panels.BasePanel;

import java.awt.*;

public abstract class BasePresenter {
    protected final BasePanel panel;
    protected final Container container;
    protected final DatabaseApi api;

    public BasePresenter(BasePanel panel, Container container) {
        this.panel = panel;
        this.container = container;
        this.api = DatabaseApi.getInstance();
    }

    public void openPanel(BasePanel panel) {
        container.removeAll();
        container.add(panel);
        container.revalidate();
        panel.requestFocus();
    }

    public void onError(String message) {
        panel.onError(message);
    }

    public void onSuccess(String message) {
        panel.onSuccess(message);
    }

    public void openFrame(BaseFrame frame, BasePanel panel) {
        try {
            frame.open(panel);
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно открыть окно");
        }
    }
}
