package panels;

import presenters.MainMenuPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JPanel {

    MainMenuPresenter mainMenuPresenter;

    public MainMenuPanel(Container container) {
        mainMenuPresenter = new MainMenuPresenter(container, this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initViews();
    }

    private void initViews() {

        JButton connectToServerBtn = new JButton("connect to server");
        connectToServerBtn.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        connectToServerBtn.setAlignmentX(CENTER_ALIGNMENT);
        connectToServerBtn.setMaximumSize(new Dimension(400, 40));

        JLabel infoLabel = new JLabel("Enter your data to connect to localhost");
        infoLabel.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoLabel.setMaximumSize(new Dimension(400, 40));

        JTextField usernameTxt = new JTextField("Username");
        usernameTxt.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        usernameTxt.setAlignmentX(CENTER_ALIGNMENT);
        usernameTxt.setMaximumSize(new Dimension(400, 40));

        JPasswordField passwordTxt = new JPasswordField("Password");
        passwordTxt.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        passwordTxt.setAlignmentX(CENTER_ALIGNMENT);
        passwordTxt.setMaximumSize(new Dimension(400, 40));

        JButton connectToLocalhostBtn = new JButton("connect to localhost");
        connectToLocalhostBtn.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        connectToLocalhostBtn.setAlignmentX(CENTER_ALIGNMENT);
        connectToLocalhostBtn.setMaximumSize(new Dimension(400, 40));


        connectToServerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainMenuPresenter.connectToServer("84.237.50.81", "1521", "18204_Gomer", "artemgonka2000");
            }
        });

        connectToLocalhostBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainMenuPresenter.connectToServer("127.0.0.1", "1521", usernameTxt.getText(), new String(passwordTxt.getPassword()));
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

        add(connectToServerBtn);
        add(infoLabel);
        add(usernameTxt);
        add(passwordTxt);
        add(connectToLocalhostBtn);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
    }

}
