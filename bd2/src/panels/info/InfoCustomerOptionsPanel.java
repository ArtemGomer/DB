package panels.info;

import panels.BasePanel;
import presenters.info.InfoOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class InfoCustomerOptionsPanel extends BasePanel {

    private final InfoOptionsPresenter presenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 20);

    public InfoCustomerOptionsPanel(Container container) {
        super(container);
        this.presenter = new InfoOptionsPresenter(this, container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    protected void initViews() {

        JButton dealersInfoBtn = new JButton("Поставщики по категориям");
        dealersInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        dealersInfoBtn.setMaximumSize(new Dimension(350, 40));
        dealersInfoBtn.setFont(font);
        dealersInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Поставщики");
            }
        });

        JButton detailsInfoBtn = new JButton("Детали по категориям");
        detailsInfoBtn.setMaximumSize(new Dimension(350, 40));
        detailsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        detailsInfoBtn.setFont(font);
        detailsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openTable("Поставляемые");
            }
        });


        add(dealersInfoBtn);
        add(detailsInfoBtn);

    }

}
