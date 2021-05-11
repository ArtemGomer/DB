package presenters;

import panels.BasePanel;

import java.awt.*;
import java.sql.SQLException;

public class RegisterPresenter extends BasePresenter {

    public RegisterPresenter(BasePanel panel, Container container) {
        super(panel, container);
    }

    public void addUser(String login, String password, String role) {
        try {
            api.addUser(login, password, role);
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Не вышло добавить пользователя!");
        }
    }
}
