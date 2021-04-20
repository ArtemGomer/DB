package frames;

import javax.swing.*;
import java.awt.*;

public final class InfoFrame extends BaseFrame {

    private final Dimension dimension;

    public InfoFrame(String name, Dimension dimension) {
        super(name);
        this.dimension = dimension;
        initViews();
    }

    @Override
    protected void initViews() {
        setSize(dimension);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
}
