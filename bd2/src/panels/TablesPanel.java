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
        setLayout(new GridLayout(4, 1));
        initViews();
    }

    private void initViews() {

        JButton deliveredGoodsBtn = new JButton("Delivered goods");
        deliveredGoodsBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        deliveredGoodsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Delivered_goods");
            }
        });

        JButton goodsTypeBtn = new JButton("Dealers");
        goodsTypeBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Dealers");
            }
        });

        JButton createTablesBtn = new JButton("Recreate tables");
        createTablesBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        createTablesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.recreateTables();
//                tablesPresenter.openDataTable("Goods_type");
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.exit();
            }
        });

        add(deliveredGoodsBtn);
        add(goodsTypeBtn);
        add(createTablesBtn);
        add(exitBtn);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }
}
