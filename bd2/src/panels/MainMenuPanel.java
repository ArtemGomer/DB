package panels;

import presenters.MainMenuPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class MainMenuPanel extends BasePanel {

    MainMenuPresenter mainMenuPresenter;
    private JLabel connecting;
    private JButton connectToServerBtn;
    private JButton connectToLocalhostBtn;

    public MainMenuPanel(Container container) {
        super(container);
        mainMenuPresenter = new MainMenuPresenter(container, this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    protected void initViews() {

        JLabel usernameLabel = new JLabel("Имя пользователя");
        usernameLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
        usernameLabel.setMaximumSize(new Dimension(400, 50));

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        passwordLabel.setMaximumSize(new Dimension(400, 50));

        connectToServerBtn = new JButton("Подключиться к серверу");
        connectToServerBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        connectToServerBtn.setAlignmentX(CENTER_ALIGNMENT);
        connectToServerBtn.setMaximumSize(new Dimension(400, 50));

        JLabel infoLabel = new JLabel("Введите данные для localhost");
        infoLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoLabel.setMaximumSize(new Dimension(400, 50));

        JTextField usernameTxt = new JTextField("GOMER");
        usernameTxt.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        usernameTxt.setAlignmentX(CENTER_ALIGNMENT);
        usernameTxt.setMaximumSize(new Dimension(400, 50));

        JPasswordField passwordTxt = new JPasswordField("artemgonka2000");
        passwordTxt.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        passwordTxt.setAlignmentX(CENTER_ALIGNMENT);
        passwordTxt.setMaximumSize(new Dimension(400, 50));

        connectToLocalhostBtn = new JButton("Подключиться к localhost");
        connectToLocalhostBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        connectToLocalhostBtn.setAlignmentX(CENTER_ALIGNMENT);
        connectToLocalhostBtn.setMaximumSize(new Dimension(400, 50));

        connectToServerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainMenuPresenter.connectToServer("84.237.50.81",
                        "1521",
                        usernameTxt.getText(),
                        new String(passwordTxt.getPassword()));
            }
        });

        usernameTxt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                usernameTxt.setText("");
            }
        });

        passwordTxt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTxt.setText("");
            }
        });

        connecting = new JLabel("Подключение...");
        connecting.setFont(new Font(Font.SERIF, Font.BOLD, 35));
        connecting.setForeground(Color.LIGHT_GRAY);
        connecting.setAlignmentX(CENTER_ALIGNMENT);
        connecting.setVisible(false);

        add(usernameLabel);
        add(usernameTxt);
        add(passwordLabel);
        add(passwordTxt);
        add(connectToServerBtn);
        add(connecting);
    }

    public void setIsConnecting(boolean connecting) {
        this.connecting.setVisible(connecting);
        this.connectToLocalhostBtn.setEnabled(!connecting);
        this.connectToServerBtn.setEnabled(!connecting);
    }

}
