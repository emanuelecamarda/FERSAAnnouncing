package dao;

import entity.ApartmentResearch;
import entity.User;
import exception.EntityAlreadyExistException;
import factory.ResearchFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class ApartmentResearchDaoTest {

    enum Type {CREATE, FIND, DELETE, FINDALL};

    private Type type;
    private ApartmentResearch apartmentResearch;
    private User user;
    private ApartmentResearchDao apartmentResearchDao;
    private UserDao userDao;

    @Parameterized.Parameters
    public static Collection<Object[]> GetTestParameters() {
        return Arrays.asList(new Object[][] {
                // {Type, city, priceMin, priceMax, size, date, favorite, sorting, localsMin, localsMax, furnished,
                // bathroomMin, bedsNumberMin, bedsNumberMax}
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, 3,
                        Boolean.TRUE, 1, 2, 5},
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", null, 3,
                        Boolean.TRUE, 1, 2, 5},
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, null,
                        Boolean.TRUE, 1, 2, 5},
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, 3,
                        Boolean.TRUE, null, 2, 5},
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, 3,
                        Boolean.TRUE, 1, null, 5},
                {Type.CREATE, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, 3,
                        Boolean.TRUE, 1, 2, null},
                {Type.FIND, "Roma", 200.0, 600.0, 50.0, new GregorianCalendar(), Boolean.TRUE, "moreRecent", 1, 3,
                        Boolean.TRUE, 1, 2, 5},
        });
    }

    public ApartmentResearchDaoTest(Type type, String city, Double priceMin, Double priceMax, Double size,
                                    GregorianCalendar date, Boolean favorite, String sorting, Integer localsMin,
                                    Integer localsMax, Boolean furnished, Integer bathroomNumberMin,
                                    Integer bedsNumberMin, Integer bedsNumberMax) {

        this.apartmentResearchDao = new ApartmentResearchDao();
        this.userDao = new UserDao();
        if (userDao.findByNickname("nickname") == null) {
            try {
                this.user = userDao.create("nickname", "name", "surname", "email@gmail.com",
                        "password", 'f');
            } catch (EntityAlreadyExistException e) {
                e.printStackTrace();
            }
        }
        else
            this.user = userDao.findByNickname("nickname");
        System.out.println(this.user);
        this.apartmentResearch = ResearchFactory.getApartmentResearch(null, city, priceMin, priceMax, size,
                date, favorite, this.user, sorting, localsMin, localsMax, furnished, bathroomNumberMin,
                bedsNumberMin, bedsNumberMax);
        this.type = type;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        UserDao userDao = new UserDao();
        if (userDao.findByNickname("nickname") != null)
            userDao.delete("nickname");
    }

    @Test
    public void create() {
        Assume.assumeTrue(type == Type.CREATE);
        ApartmentResearch apartmentResearchCreate = apartmentResearchDao.create(apartmentResearch);
        assertEquals(apartmentResearchCreate, apartmentResearch);
    }

    @Test
    public void findByID() {
        Assume.assumeTrue(type == Type.FIND);
        ApartmentResearch apartmentResearchCreate = apartmentResearchDao.create(apartmentResearch);
        assertEquals(apartmentResearchDao.findByID(apartmentResearchCreate.getID()), apartmentResearch);
    }

    @Test
    public void delete() {
    }

    @Test
    public void getMaxID() {
    }

    @Test
    public void findFavorite() {
    }

    @Test
    public void findRecent() {
    }

    @Test
    public void findAll() {
    }
}