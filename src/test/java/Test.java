import dao.UserDao;
import entity.User;

public class Test {


    public static void main(String args[]) {
        UserDao userDao = new UserDao();
        User u = userDao.findByNicknameAndPassword("bella", "bella");
        User v = userDao.SignUp("ciao", "hello ","bye" , "aaabbb" , "abcd" , 'm');
        System.out.println(u);
        System.out.println(v);
    }
}
