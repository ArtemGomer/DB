package panels.data;

import presenters.data.CustomerOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerOptionsPanel extends JPanel {

    private final CustomerOptionsPresenter optionsPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);

    public CustomerOptionsPanel(Container container) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        optionsPresenter = new CustomerOptionsPresenter(container, this);
        initViews();
    }

    private void initViews() {

        JButton makeOrderBtn = new JButton("Сделать заказ");
        makeOrderBtn.setFont(font);
        makeOrderBtn.setMaximumSize(new Dimension(350, 40));
        makeOrderBtn.setAlignmentX(CENTER_ALIGNMENT);
        makeOrderBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                optionsPresenter.openAddOrderFrame();
            }
        });

        JButton allOrdersBtn = new JButton("Посмотреть заказы");
        allOrdersBtn.setFont(font);
        allOrdersBtn.setMaximumSize(new Dimension(350, 40));
        allOrdersBtn.setAlignmentX(CENTER_ALIGNMENT);
        allOrdersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                optionsPresenter.openOrdersTable();
            }
        });

        JButton infoBtn = new JButton("Информация о магазине");
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

        add(makeOrderBtn);
        add(allOrdersBtn);
        add(infoBtn);
        add(disconnectBtn);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}

