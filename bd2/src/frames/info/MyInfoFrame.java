package frames.info;

import frames.BaseFrame;

import javax.swing.*;
import java.awt.*;

public final class MyInfoFrame extends BaseFrame {

    private final Dimension dimension;


    public MyInfoFrame(String name, Dimension dimension) {
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
