package frames.data;

import frames.BaseFrame;

import javax.swing.*;

public final class TableFrame extends BaseFrame {

    public TableFrame(String tableName) {
        super(tableName);
        initViews();
    }

    @Override
    protected void initViews() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

}
