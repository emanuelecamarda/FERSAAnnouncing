package control;

import dao.UserDao;
import entity.User;
import exception.EntityNotExistException;

public class LoginController {

    private UserDao userdao = new UserDao();

    public LoginController() {}

    public User login(String nickname, String password) throws EntityNotExistException {
        return userdao.findByNicknameAndPassword(nickname, password);

    }
}
