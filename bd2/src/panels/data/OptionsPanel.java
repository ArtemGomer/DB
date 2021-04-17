package panels.data;

import presenters.data.OptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionsPanel extends JPanel {

    private final OptionsPresenter optionsPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);

    private JButton recreateTablesBtn;
    private JButton deleteDatabaseBtn;
    private JButton openTablesBtn;
    private JButton infoBtn;

    public OptionsPanel(Container container) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        optionsPresenter = new OptionsPresenter(container, this);
        initViews();
    }

    private void initViews() {

        openTablesBtn = new JButton("Открыть таблицы");
        openTablesBtn.setMaximumSize(new Dimension(350, 40));
        openTablesBtn.setAlignmentX(CENTER_ALIGNMENT);
        openTablesBtn.setFont(font);
        openTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                optionsPresenter.openTables();
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
                optionsPresenter.recreateTables();
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
                optionsPresenter.deleteDatabase();
            }
        });

        infoBtn = new JButton("Информация о магазине");
        infoBtn.setMaximumSize(new Dimension(350, 40));
        infoBtn.setFont(font);
        infoBtn.setAlignmentX(CENTER_ALIGNMENT);
        infoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                optionsPresenter.openInfoFrame();
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
                optionsPresenter.exit();
            }
        });

        add(openTablesBtn);
        add(infoBtn);
        add(recreateTablesBtn);
        add(deleteDatabaseBtn);
        add(disconnectBtn);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void setIsLoading(boolean isLoading) {
        openTablesBtn.setEnabled(!isLoading);
        recreateTablesBtn.setEnabled(!isLoading);
        deleteDatabaseBtn.setEnabled(!isLoading);
        infoBtn.setEnabled(!isLoading);
    }

}

