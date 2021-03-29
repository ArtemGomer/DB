package panels;

import presenters.TablesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablesPanel extends JPanel {

    private final TablesPresenter tablesPresenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 30);

    public TablesPanel(Container container) {
        tablesPresenter = new TablesPresenter(container, this);
        initViews();
    }

    private void initViews() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel tablesPanel = new JPanel(new GridLayout(4, 2));

        JButton deliveredGoodsBtn = new JButton("Delivered goods");
        deliveredGoodsBtn.setFont(font);
        deliveredGoodsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Delivered_goods");
            }
        });

        JButton dealersBtn = new JButton("Dealers");
        dealersBtn.setFont(font);
        dealersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Dealers");
            }
        });

        JButton goodsTypeBtn = new JButton("Goods_type");
        goodsTypeBtn.setFont(font);
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Goods_type");
            }
        });

        JButton feeBtn = new JButton("Fee");
        feeBtn.setFont(font);
        feeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Fee");
            }
        });

        JButton deliversBtn = new JButton("Delivers");
        deliversBtn.setFont(font);
        deliversBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Delivers");
            }
        });

        JButton ordersBtn = new JButton("Orders");
        ordersBtn.setFont(font);
        ordersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Orders");
            }
        });

        JButton cellBtn = new JButton("Cells");
        cellBtn.setFont(font);
        cellBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Cells");
            }
        });

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JButton recreateTablesBtn = new JButton("Recreate tables");
        recreateTablesBtn.setAlignmentX(CENTER_ALIGNMENT);
        recreateTablesBtn.setFont(font);
        recreateTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.recreateTables();
//                tablesPresenter.openDataTable("Goods_type");
            }
        });

        JButton disconnectBtn = new JButton("Disconnect");
        disconnectBtn.setAlignmentX(CENTER_ALIGNMENT);
        disconnectBtn.setFont(font);
        disconnectBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.exit();
            }
        });

        tablesPanel.add(deliveredGoodsBtn);
        tablesPanel.add(dealersBtn);
        tablesPanel.add(goodsTypeBtn);
        tablesPanel.add(feeBtn);
        tablesPanel.add(deliversBtn);
        tablesPanel.add(ordersBtn);
        tablesPanel.add(cellBtn);

        optionsPanel.add(recreateTablesBtn);
        optionsPanel.add(disconnectBtn);

        mainPanel.add(tablesPanel);
        mainPanel.add(optionsPanel);

        add(mainPanel);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }
}
