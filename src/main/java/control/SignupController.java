package control;

import dao.UserDao;
import entity.User;
import exception.EntityAlreadyExistException;

public class SignupController {

    private UserDao userDao = new UserDao();

    public SignupController(){}

    public User signup(String nickname, String nome , String cognome , String email , String password ,
                       Character gender) throws EntityAlreadyExistException {
        return userDao.create(nickname, nome, cognome, email, password, gender);
    }

}
