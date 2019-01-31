package control;

import dao.UserDao;
import entity.User;
import exception.EntityNotExistException;

public class LoginController {

    private static LoginController instance = null;
    private static UserDao userdao = null;

    private LoginController() {}

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
            userdao = new UserDao();
        }
        return instance;
    }

    public User login(String nickname, String password) throws EntityNotExistException {
        return userdao.findByNicknameAndPassword(nickname, password);

    }
}
