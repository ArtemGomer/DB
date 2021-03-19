package panels;

import presenters.TablesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablesPanel extends JPanel {

    private final TablesPresenter tablesPresenter;

    public TablesPanel(Container container) {
        tablesPresenter = new TablesPresenter(container);
        setLayout(new GridLayout(3, 1));
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

        JButton goodsTypeBtn = new JButton("Goods type");
        goodsTypeBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        goodsTypeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tablesPresenter.openDataTable("Goods_type");
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
        add(exitBtn);
    }
}
