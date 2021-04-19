package panels.data;

import panels.BasePanel;
import presenters.data.RolesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class RolesPanel extends BasePanel {

    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);
    private final RolesPresenter rolesPresenter;

    public RolesPanel(Container container) {
        super(container);
        setLayout(new GridLayout(1, 2));
        this.rolesPresenter = new RolesPresenter(this, container);
        initViews();
    }

    protected void initViews() {
        JButton adminBtn = new JButton("Администратор");
        adminBtn.setFont(font);
        adminBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rolesPresenter.openPanel(new AdminOptionsPanel(container));
            }
        });

        JButton customerBtn = new JButton("Посетитель");
        customerBtn.setFont(font);
        customerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rolesPresenter.openPanel(new CustomerOptionsPanel(container));
            }
        });

        add(adminBtn);
        add(customerBtn);

    }

}
