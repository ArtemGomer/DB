package panels.info;

import panels.BasePanel;
import presenters.info.InfoOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class InfoTraderOptionsPanel extends BasePanel {

    private final InfoOptionsPresenter presenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 30);

    public InfoTraderOptionsPanel(Container container) {
        super(container);
        this.presenter = new InfoOptionsPresenter(this, container);
        setLayout(new GridLayout(4, 4));
        initViews();
    }

    protected void initViews() {

        JButton dealersInfoBtn = new JButton("Поставщики по категориям");
        dealersInfoBtn.setFont(font);
        dealersInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Поставщики");
            }
        });

        JButton detailsInfoBtn = new JButton("Детали по категориям");
        detailsInfoBtn.setFont(font);
        detailsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Поставляемые");
            }
        });

        JButton sellsInfoBtn = new JButton("Продажи по типу");
        sellsInfoBtn.setFont(font);
        sellsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Продажи");
            }
        });

        JButton cellsInfoBtn = new JButton("Все ячейки склада");
        cellsInfoBtn.setFont(font);
        cellsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Ячейки");
            }
        });

        add(dealersInfoBtn);
        add(detailsInfoBtn);
        add(sellsInfoBtn);
        add(cellsInfoBtn);

    }

}
