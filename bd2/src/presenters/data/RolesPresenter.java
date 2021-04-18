package presenters.data;

import javax.swing.*;
import java.awt.*;

public class RolesPresenter {

    private final Container container;

    public RolesPresenter(Container container) {
        this.container = container;
    }

    public void openPanel(JPanel panel) {
        container.removeAll();
        container.add(panel);
        container.revalidate();
        panel.requestFocus();
    }
}
