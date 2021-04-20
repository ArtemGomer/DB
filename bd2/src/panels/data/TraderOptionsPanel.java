package panels.data;

import com.sun.javaws.exceptions.InvalidArgumentException;
import frames.InfoFrame;
import panels.BasePanel;
import panels.info.InfoCustomerOptionsPanel;
import panels.info.InfoPanel;
import panels.info.InfoTraderOptionsPanel;
import presenters.data.CustomerOptionsPresenter;
import presenters.data.TraderOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public final class TraderOptionsPanel extends BasePanel {

    private final TraderOptionsPresenter optionsPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);

    public TraderOptionsPanel(Container container) {
        super(container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        optionsPresenter = new TraderOptionsPresenter(container, this);
        initViews();
    }

    protected void initViews() {

        JButton makeOrderBtn = new JButton("Выполнить заказ");
        makeOrderBtn.setFont(font);
        makeOrderBtn.setMaximumSize(new Dimension(350, 40));
        makeOrderBtn.setAlignmentX(CENTER_ALIGNMENT);
        makeOrderBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                optionsPresenter.openAddOrderFrame();
            }
        });

        JButton allCellsBtn = new JButton("Посмотреть склад");
        allCellsBtn.setFont(font);
        allCellsBtn.setMaximumSize(new Dimension(350, 40));
        allCellsBtn.setAlignmentX(CENTER_ALIGNMENT);
        allCellsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                optionsPresenter.openCellsFrame();
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
                optionsPresenter.openFrame(new InfoFrame("Информация", new Dimension(700, 800)), new InfoTraderOptionsPanel(container));
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
        add(allCellsBtn);
        add(infoBtn);
        add(disconnectBtn);
    }
}

