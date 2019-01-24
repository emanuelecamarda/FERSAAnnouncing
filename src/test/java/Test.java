import dao.UserDao;
import entity.User;

public class Test {


    public static void main(String args[]) {
        UserDao userDao = new UserDao();
        User u = userDao.findByNicknameAndPassword("bella", "bella");
        System.out.println(u);
    }
}
