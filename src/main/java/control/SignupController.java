package control;

import dao.UserDao;

public class SignupController {

    private UserDao userDao = new UserDao();

    public SignupController(){}

    public void signup(String nickname, String nome , String cognome , String email , String password ,
                       Character gender) {
        userDao.create(nickname, nome, cognome, email, password, gender);
    }

}
