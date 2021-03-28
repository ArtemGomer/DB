package panels;

import presenters.TablesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablesPanel extends JPanel {

    private final TablesPresenter tablesPresenter;

    public TablesPanel(Container container) {
        tablesPresenter = new TablesPresenter(container, this);
        initViews();
    }

    private void initViews() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel tablesPanel = new JPanel(new GridLayout(2, 2));

        JButton deliveredGoodsBtn = new JButton("Delivered goods");
        deliveredGoodsBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        deliveredGoodsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Delivered_goods");
            }
        });

        JButton dealersBtn = new JButton("Dealers");
        dealersBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        dealersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Dealers");
            }
        });

        JButton goodsTypeBtn = new JButton("Goods_type");
        goodsTypeBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Goods_type");
            }
        });

        JButton feeBtn = new JButton("Fee");
        feeBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        feeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Fee");
            }
        });

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JButton recreateTablesBtn = new JButton("Recreate tables");
        recreateTablesBtn.setAlignmentX(CENTER_ALIGNMENT);
        recreateTablesBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        recreateTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.recreateTables();
//                tablesPresenter.openDataTable("Goods_type");
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setAlignmentX(CENTER_ALIGNMENT);
        exitBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        exitBtn.addMouseListener(new MouseAdapter() {
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

        optionsPanel.add(recreateTablesBtn);
        optionsPanel.add(exitBtn);

        mainPanel.add(tablesPanel);
        mainPanel.add(optionsPanel);

        add(mainPanel);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }
}
