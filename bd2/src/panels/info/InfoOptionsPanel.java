package panels.info;

import frames.info.MyInfoFrame;
import panels.BasePanel;
import presenters.info.InfoOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class InfoOptionsPanel extends BasePanel {

    private final InfoOptionsPresenter presenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 30);

    public InfoOptionsPanel(Container container) {
        super(container);
        this.presenter = new InfoOptionsPresenter(this, container);
        setLayout(new GridLayout(4, 4));
        initViews();
    }

    protected void initViews() {

        JButton dealersInfoBtn = new JButton("Поставщики по категориям");
        dealersInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        dealersInfoBtn.setFont(font);
        dealersInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    presenter.openFrame(new MyInfoFrame("Поставщики по категориям", new Dimension(600, 400)), new InfoPanel("Поставщики", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    presenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton detailsInfoBtn = new JButton("Детали по категориям");
        detailsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        detailsInfoBtn.setFont(font);
        detailsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    presenter.openFrame(new MyInfoFrame("Детали по категориям", new Dimension(600, 400)), new InfoPanel("Поставляемые", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    presenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton sellsInfoBtn = new JButton("Продажи по типу");
        sellsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        sellsInfoBtn.setFont(font);
        sellsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    presenter.openFrame(new MyInfoFrame("Продажи по типу", new Dimension(600, 400)), new InfoPanel("Продажи", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    presenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        JButton cellsInfoBtn = new JButton("Все ячейки склада");
        cellsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        cellsInfoBtn.setFont(font);
        cellsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    presenter.openFrame(new MyInfoFrame("Все ячейки склада", new Dimension(600, 400)), new InfoPanel("Ячейки", container));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    presenter.onError("Невозможно открыть таблицу");
                }
            }
        });

        add(dealersInfoBtn);
        add(detailsInfoBtn);
        add(sellsInfoBtn);
        add(cellsInfoBtn);

    }

}
