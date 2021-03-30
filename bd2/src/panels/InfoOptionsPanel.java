package panels;

import presenters.InfoOptionsPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class InfoOptionsPanel extends JPanel {

    private final InfoOptionsPresenter presenter;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 30);

    public InfoOptionsPanel() {
        this.presenter = new InfoOptionsPresenter(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    public void initViews() {

        JButton dealersInfoBtn = new JButton("Dealers info");
        dealersInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        dealersInfoBtn.setFont(font);
        dealersInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.openInfoFrame("Dealers");
            }
        });

        JButton detailsInfoBtn = new JButton("Details info");
        detailsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        detailsInfoBtn.setFont(font);
        detailsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.openInfoFrame("Delivered_goods");
            }
        });

        add(dealersInfoBtn);
        add(detailsInfoBtn);

    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

}
