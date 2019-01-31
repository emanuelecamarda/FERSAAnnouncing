package control;

import dao.UserDao;

public class SignupController {

    private static UserDao userDao = null;
    private static SignupController istance;

    private SignupController(){}

    public static SignupController getIstance() {
        if (istance == null) {
            istance = new SignupController();
            userDao = new UserDao();
        }
        return istance;
    }

    public void signup(String nickname, String nome , String cognome , String email , String password ,
                       Character gender) {
        userDao.create(nickname, nome, cognome, email, password, gender);
    }

}
