package presenters.data;

import frames.InfoFrame;
import panels.BasePanel;
import panels.MainMenuPanel;
import panels.data.CompleteOrdersPanel;
import panels.info.InfoPanel;
import presenters.BasePresenter;

import java.awt.*;
import java.sql.SQLException;

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
        }
    }

    public void openOrdersFrame() {
        try {
            openFrame(new InfoFrame("Заказы", new Dimension(600, 400)), new CompleteOrdersPanel(container));
        } catch (SQLException throwables) {
            onError("Невозможно открыть заказы");
        }
    }

    public void exit() {
        api.disconnect();
        openPanel(new MainMenuPanel(container));
    }

}
