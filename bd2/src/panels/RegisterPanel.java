package panels;

import presenters.RegisterPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class RegisterPanel extends BasePanel{

    private final RegisterPresenter registerPresenter;

    public RegisterPanel(Container container) {
        super(container);
        registerPresenter = new RegisterPresenter(this, container);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    @Override
    protected void initViews(){
        JLabel label = new JLabel("Регистрация");
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

        JComboBox<String> rolesComboBox = new JComboBox<>(new String[]{"Админ", "Продавец", "Посетитель"});
        rolesComboBox.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        rolesComboBox.setAlignmentX(CENTER_ALIGNMENT);
        rolesComboBox.setMaximumSize(new Dimension(400, 50));

        JButton registerBtn = new JButton("Регистрация");
        registerBtn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(400, 50));
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerPresenter.addUser(loginTextField.getText(), new String(passwordTextField.getPassword()), Objects.requireNonNull(rolesComboBox.getSelectedItem()).toString());
            }
        });

        add(label);
        add(loginTextField);
        add(passwordTextField);
        add(rolesComboBox);
        add(registerBtn);
    }
}
