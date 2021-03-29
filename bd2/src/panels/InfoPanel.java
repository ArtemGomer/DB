package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoPanel extends JPanel {

    private final Font font = new Font(Font.SERIF, Font.BOLD, 30);

    public InfoPanel() {
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
                super.mouseClicked(e);
            }
        });

        JButton detailsInfoBtn = new JButton("Details info");
        detailsInfoBtn.setAlignmentX(CENTER_ALIGNMENT);
        detailsInfoBtn.setFont(font);
        detailsInfoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        add(dealersInfoBtn);
        add(detailsInfoBtn);

    }

}
