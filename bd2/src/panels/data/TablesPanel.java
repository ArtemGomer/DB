package panels.data;

import panels.BasePanel;
import presenters.data.TablesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class TablesPanel extends BasePanel {

    private final TablesPresenter tablesPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 25);

    public TablesPanel(Container container) {
        super(container);
        setLayout(new GridLayout(4, 2));
        tablesPresenter = new TablesPresenter(this, container);
        initViews();
    }

    protected void initViews() {

        JButton deliveredGoodsBtn = new JButton("Поставляемые товары");
        deliveredGoodsBtn.setFont(font);
        deliveredGoodsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Поставляемые");
            }
        });

        JButton dealersBtn = new JButton("Поставщики");
        dealersBtn.setFont(font);
        dealersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Поставщики");
            }
        });

        JButton goodsTypeBtn = new JButton("Типы товаров");
        goodsTypeBtn.setFont(font);
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Типы_товаров");
            }
        });

        JButton feeBtn = new JButton("Пошлина");
        feeBtn.setFont(font);
        feeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Пошлина");
            }
        });

        JButton deliversBtn = new JButton("Поставки");
        deliversBtn.setFont(font);
        deliversBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Поставки");
            }
        });

        JButton ordersBtn = new JButton("Заказы");
        ordersBtn.setFont(font);
        ordersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Заказы");
            }
        });

        JButton sellsBtn = new JButton("Продажи");
        sellsBtn.setFont(font);
        sellsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Продажи");
            }
        });

        JButton cellBtn = new JButton("Ячейки");
        cellBtn.setFont(font);
        cellBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openTable("Ячейки");
            }
        });

        add(deliveredGoodsBtn);
        add(dealersBtn);
        add(goodsTypeBtn);
        add(feeBtn);
        add(deliversBtn);
        add(sellsBtn);
        add(ordersBtn);
        add(cellBtn);
    }

}
