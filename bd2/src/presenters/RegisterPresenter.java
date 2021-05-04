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
            int er = api.addUser(login, password, role);
            if (er == 0) {
                onError("Не вышло добавить пользователя!");
            } else {
                onSuccess("Регистрация прошла успешно!");
            }
        } catch (SQLException ex) {
            onError("Не вышло добавить пользователя!");
        }
    }
}
