package presenters;

import panels.BasePanel;
import panels.data.AdminOptionsPanel;
import panels.data.CustomerOptionsPanel;
import panels.data.TraderOptionsPanel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPresenter extends BasePresenter {

    public LoginPresenter(BasePanel panel, Container container) {
        super(panel, container);
    }

    public void openUser(String login, String password) {
        if (!checkFields(login, password)) {
            onError("Заполните все поля!");
            return;
        }
        try {
            ResultSet user = api.getUser(login, password);
            if (!user.last()) {
                onError("Нет такого пользователя или пароль неверный!");
            } else {
                user.first();
                String role = user.getString(2);
                if (role.equalsIgnoreCase("Админ")) {
                    openPanel(new AdminOptionsPanel(container));
                } else if (role.equalsIgnoreCase("Продавец")) {
                    openPanel(new TraderOptionsPanel(container));
                } else {
                    openPanel(new CustomerOptionsPanel(container));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkFields(String login, String password) {
        return !login.isEmpty() && !password.isEmpty();
    }
}
