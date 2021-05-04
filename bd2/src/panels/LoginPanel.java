package panels;

import frames.InfoFrame;
import presenters.LoginPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends BasePanel {

    private final LoginPresenter loginPresenter;

    public LoginPanel(Container container) {
        super(container);
        loginPresenter = new LoginPresenter(this, container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    @Override
    protected void initViews() {

        JLabel label = new JLabel("Вход");
        label.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        JTextField loginTextField = new JTextField();
        loginTextField.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        loginTextField.setAlignmentX(CENTER_ALIGNMENT);
        loginTextField.setMaximumSize(new Dimension(400, 50));

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        passwordTextField.setAlignmentX(CENTER_ALIGNMENT);
        passwordTextField.setMaximumSize(new Dimension(400, 50));

        JButton loginBtn = new JButton("Войти");
        loginBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(400, 50));
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loginPresenter.openUser(loginTextField.getText(), new String(passwordTextField.getPassword()));
            }
        });

        JButton registerBtn = new JButton("Регистрация");
        registerBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(400, 50));
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loginPresenter.openFrame(new InfoFrame("Регистрация", new Dimension(500, 300)), new RegisterPanel(container));
            }
        });

        add(label);
        add(loginTextField);
        add(passwordTextField);
        add(loginBtn);
        add(registerBtn);

    }
}
