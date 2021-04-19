package panels.data;

import frames.data.TableFrame;
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
                try {
                    tablesPresenter.openFrame(new TableFrame("Поставляемые"), new DataTablePanel("Поставляемые", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton dealersBtn = new JButton("Поставщики");
        dealersBtn.setFont(font);
        dealersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Поставщики"), new DataTablePanel("Поставщики", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton goodsTypeBtn = new JButton("Типы товаров");
        goodsTypeBtn.setFont(font);
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Типы товаров"), new DataTablePanel("Типы_товаров", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton feeBtn = new JButton("Пошлина");
        feeBtn.setFont(font);
        feeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Пошлина"), new DataTablePanel("Пошлина", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton deliversBtn = new JButton("Поставки");
        deliversBtn.setFont(font);
        deliversBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Поставки"), new DataTablePanel("Поставки", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton ordersBtn = new JButton("Заказы");
        ordersBtn.setFont(font);
        ordersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Заказы"), new DataTablePanel("Заказы", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton sellsBtn = new JButton("Продажи");
        sellsBtn.setFont(font);
        sellsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Продажи"), new DataTablePanel("Продажи", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton cellBtn = new JButton("Ячейки");
        cellBtn.setFont(font);
        cellBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    tablesPresenter.openFrame(new TableFrame("Ячейки"), new DataTablePanel("Ячейки", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tablesPresenter.onError("Невозможно открыть таблицу");
                }
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
