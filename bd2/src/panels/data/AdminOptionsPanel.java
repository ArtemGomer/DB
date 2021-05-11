package panels.data;

import frames.InfoFrame;
import panels.BasePanel;
import panels.RegisterPanel;
import presenters.data.AdminOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class AdminOptionsPanel extends BasePanel {

    private final AdminOptionsPresenter adminOptionsPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);

    private JButton recreateTablesBtn;
    private JButton deleteDatabaseBtn;
    private JButton openTablesBtn;

    public AdminOptionsPanel(Container container) {
        super(container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        adminOptionsPresenter = new AdminOptionsPresenter(container, this);
        initViews();
    }

    protected void initViews() {

        openTablesBtn = new JButton("Открыть таблицы");
        openTablesBtn.setMaximumSize(new Dimension(350, 40));
        openTablesBtn.setAlignmentX(CENTER_ALIGNMENT);
        openTablesBtn.setFont(font);
        openTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminOptionsPresenter.openFrame(new InfoFrame("Таблицы", new Dimension(600, 400)), new TablesPanel(container));
            }
        });

        recreateTablesBtn = new JButton("Пересоздать БД");
        recreateTablesBtn.setMaximumSize(new Dimension(350, 40));
        recreateTablesBtn.setAlignmentX(CENTER_ALIGNMENT);
        recreateTablesBtn.setFont(font);
        recreateTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminOptionsPresenter.recreateTables();
            }
        });

        deleteDatabaseBtn = new JButton("Удалить БД");
        deleteDatabaseBtn.setMaximumSize(new Dimension(350, 40));
        deleteDatabaseBtn.setAlignmentX(CENTER_ALIGNMENT);
        deleteDatabaseBtn.setFont(font);
        deleteDatabaseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminOptionsPresenter.deleteDatabase();
            }
        });


        JButton disconnectBtn = new JButton("Отключиться");
        disconnectBtn.setMaximumSize(new Dimension(350, 40));
        disconnectBtn.setAlignmentX(CENTER_ALIGNMENT);
        disconnectBtn.setFont(font);
        disconnectBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminOptionsPresenter.exit();
            }
        });

        JButton registerBtn = new JButton("Добавить пользователя");
        registerBtn.setFont(font);
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(350, 40));
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminOptionsPresenter.openFrame(new InfoFrame("Регистрация", new Dimension(500, 300)), new RegisterPanel(container));
            }
        });

        add(openTablesBtn);
        add(recreateTablesBtn);
        add(deleteDatabaseBtn);
        add(registerBtn);
        add(disconnectBtn);
    }

    public void onError(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void setIsLoading(boolean isLoading) {
        openTablesBtn.setEnabled(!isLoading);
        recreateTablesBtn.setEnabled(!isLoading);
        deleteDatabaseBtn.setEnabled(!isLoading);
    }

}

