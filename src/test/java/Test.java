import dao.ApartmentResearchDao;
import dao.UserDao;
import entity.ApartmentResearch;
import entity.User;

import java.util.GregorianCalendar;

public class Test {


    public static void main(String args[]) {
        UserDao userDao = new UserDao();
        User u = userDao.findByNicknameAndPassword("bella", "bella");
        User v = userDao.create("ciao", "hello ","bye" , "aaabbb" , "abcd" , 'm');
        System.out.println(u);
        System.out.println(v);

        ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
        GregorianCalendar date = new GregorianCalendar();
        ApartmentResearch apartmentResearch = new ApartmentResearch("Roma", 200.0, 800.0,
                50.0, date, Boolean.TRUE, v, 1, "moreRecent", 1, 3, Boolean.FALSE,
                1, 2, 4);
        apartmentResearchDao.create(apartmentResearch);
        System.out.println(apartmentResearchDao.findByID(1));
    }
}
