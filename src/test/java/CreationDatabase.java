import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import dao.RoomResearchDao;
import dao.UserDao;
import entity.ApartmentAnnounce;
import entity.ApartmentResearch;
import entity.RoomResearch;
import entity.User;
import factory.ApartmentFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreationDatabase {

    public static void main(String args[]) {
        try {
            UserDao userDao = new UserDao();
            User u;
            if (userDao.findByNickname("emanuele") == null) {
                u = userDao.create("emanuele", "Emanuele ", "Camarda",
                        "emanuelecamarda@gmail.com", "emanuele", 'm');
            } else {
                u = userDao.findByNickname("emanuele");
            }

            ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
            GregorianCalendar date = new GregorianCalendar();
            ApartmentResearch apartmentResearch = new ApartmentResearch("Roma", 200.0, 800.0,
                    50.0, date, Boolean.TRUE, u, 1, 3, Boolean.TRUE,
                    1, 2, 4);
            apartmentResearch.setSorting("moreRecent");
            apartmentResearch = apartmentResearchDao.create(apartmentResearch);

            date.add(Calendar.DAY_OF_YEAR, -1);
            apartmentResearch = new ApartmentResearch("Roma", 150.0, 600.0,
                    30.0, date, Boolean.FALSE, u, 1, 3, Boolean.TRUE,
                    1, 2, 4);
            apartmentResearch.setSorting("lessRecent");
            apartmentResearch = apartmentResearchDao.create(apartmentResearch);

            date.add(Calendar.DAY_OF_YEAR, -2);
            apartmentResearch = new ApartmentResearch("Roma", 400.0, 1000.0,
                    80.0, date, Boolean.TRUE, u, 1, 3, Boolean.TRUE,
                    1, 2, 4);
            apartmentResearch.setSorting("lessBig");
            apartmentResearch = apartmentResearchDao.create(apartmentResearch);

            date.add(Calendar.DAY_OF_YEAR, -1);
            apartmentResearch = new ApartmentResearch("Roma", 350.0, 800.0,
                    30.0, date, Boolean.TRUE, u, 1, 3, Boolean.TRUE,
                    1, 2, 4);
            apartmentResearch.setSorting("lessExpensive");
            apartmentResearch = apartmentResearchDao.create(apartmentResearch);

            date.add(Calendar.DAY_OF_YEAR, -3);
            apartmentResearch = new ApartmentResearch("Roma", 150.0, 600.0,
                    30.0, date, Boolean.FALSE, u, 1, 3, Boolean.TRUE,
                    1, 2, 4);
            apartmentResearch.setSorting("moreExpensive");
            apartmentResearch = apartmentResearchDao.create(apartmentResearch);

            RoomResearchDao roomResearchDao = new RoomResearchDao();
            date = new GregorianCalendar();
            RoomResearch roomResearch = new RoomResearch("Roma", 200.0, 800.0, 50.0, date,
                    Boolean.TRUE, u, 3, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            roomResearch.setSorting("moreRecent");
            roomResearch = roomResearchDao.create(roomResearch);

            date.add(Calendar.DAY_OF_YEAR, -1);
            roomResearch = new RoomResearch("Roma", 250.0, 600.0, 60.0, date,
                    Boolean.TRUE, u, 3, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            roomResearch.setSorting("lessRecent");
            roomResearch = roomResearchDao.create(roomResearch);

            date.add(Calendar.DAY_OF_YEAR, -5);
            roomResearch = new RoomResearch("Roma", 350.0, 700.0, 55.0, date,
                    Boolean.TRUE, u, 3, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            roomResearch.setSorting("lessBig");
            roomResearch = roomResearchDao.create(roomResearch);

            date.add(Calendar.DAY_OF_YEAR, -3);
            roomResearch = new RoomResearch("Roma", 400.0, 900.0, 65.0, date,
                    Boolean.FALSE, u, 3, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            roomResearch.setSorting("lessExpensive");
            roomResearch = roomResearchDao.create(roomResearch);

            date.add(Calendar.DAY_OF_YEAR, -1);
            roomResearch = new RoomResearch("Roma", 280.0, 650.0, 45.0, date,
                    Boolean.TRUE, u, 3, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            roomResearch.setSorting("moreExpensive");
            roomResearch = roomResearchDao.create(roomResearch);

            User locator;
            if (userDao.findByNickname("locator") == null) {
                locator = userDao.create("locator", "Pippo", "De Pippis",
                        "pippodepippis@gmail.com", "locator", 'm');
            } else {
                locator = userDao.findByNickname("locator");
            }

            ApartmentDao apartmentDao = new ApartmentDao();
            date = new GregorianCalendar();
            ApartmentAnnounce apartmentAnnounce = (ApartmentAnnounce) ApartmentFactory.getApartment(1, "Roma",
                    "Via delle Marmore", 250.0, "An apartment", 15.0, Boolean.TRUE, date,
                    locator, 3, Boolean.TRUE, 1, 4);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
