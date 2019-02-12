package dao;


import entity.ApartmentAnnounce;
import entity.ApartmentResearch;
import entity.RoomAnnounce;
import entity.User;
import exception.EntityAlreadyExistException;
import exception.EntityNotExistException;
import factory.ApartmentFactory;
import factory.UserFactory;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class ApartmentDaoTest {

    enum Type {CREATE, OTHER, FAVORITE}

    private ApartmentDaoTest.Type type;
    private ApartmentAnnounce apartmentAnnounce;
    private ApartmentDao apartmentDao;
    private User user;
    private UserDao userDao;

    private ApartmentResearch apartmentResearch;
    private ApartmentResearchDao apartmentResearchDao;

    @Parameterized.Parameters
    public static Collection<Object[]> GetTestParameters() {
        return Arrays.asList(new Object[][] {
                // {Type ,ID, city, address, price, description, size, available, date, user ,locals ,furnished ,bathroomNumber ,bedsNumber }
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, 2},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, 2},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, null, 2},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, null},
                {Type.OTHER, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, 2},
                {Type.OTHER, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, 2},
                {Type.FAVORITE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 3, 2},

        });
    }

    public ApartmentDaoTest(Type type ,int ID, String city, String address, Double price, String description, double size,
                            boolean available, GregorianCalendar date, User user, int locals, boolean furnished,
                            int bathroomNumber, int bedsNumber){

        this.apartmentDao=new ApartmentDao();
        this.type = type;
        this.userDao = new UserDao();
        this.user = UserFactory.getUser("nickname", "name", "surname",
                "email@gmail.com","password", 'f');
        this.apartmentAnnounce = ApartmentFactory.getApartmentAnnounce(ID ,city ,address , price , description , size , available , date , this.user ,locals , furnished , bathroomNumber ,
                bedsNumber);
    }

    @Before
    public void setUp() {
        try {
            userDao.create(user.getNickname(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getGender().toString().toCharArray()[0]);
        } catch (EntityAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        userDao.delete(this.user.getNickname());
    }


    @Test
    public void create() {
        Assume.assumeTrue(type == ApartmentDaoTest.Type.CREATE);
        ApartmentAnnounce apartmentAnnounceCreate = apartmentDao.create(apartmentAnnounce);
        try {
            apartmentDao.delete(apartmentAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(apartmentAnnounceCreate, apartmentAnnounce);
    }

    @Test
    public void findByID() {
        Assume.assumeTrue(type == ApartmentDaoTest.Type.OTHER);
        ApartmentAnnounce apartmentAnnounceCreate = apartmentDao.create(apartmentAnnounce);
        assertEquals(apartmentDao.findByID(apartmentAnnounceCreate.getID()), apartmentAnnounce);
        try {
            apartmentDao.delete(apartmentAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        Assume.assumeTrue(type == ApartmentDaoTest.Type.OTHER);
        ApartmentAnnounce apartmentAnnounceCreate = apartmentDao.create(apartmentAnnounce);
        try {
            apartmentDao.delete(apartmentAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(apartmentDao.findByID(apartmentAnnounceCreate.getID()), null);
    }

    @Test
    public void findByCondition(){
        Assume.assumeTrue(type == ApartmentDaoTest.Type.OTHER);
        ApartmentAnnounce apartmentAnnounceCreate = apartmentDao.create(apartmentAnnounce);
        ApartmentResearch apartmentResearchCreate = apartmentResearchDao.create(apartmentResearch);
        List<ApartmentAnnounce> ApartmentAnnounceList = apartmentDao.findByCondition(apartmentResearch);
        try {
            apartmentDao.delete(apartmentAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(ApartmentAnnounceList, apartmentResearch);

    }

    @Test
    public void getMaxID() {
        Assume.assumeTrue(type == ApartmentDaoTest.Type.OTHER);
        apartmentDao.create(apartmentAnnounce);
        Integer oldMaxID = apartmentDao.getMaxID();
        apartmentDao.create(apartmentAnnounce);
        assertEquals(oldMaxID + 1, apartmentDao.getMaxID().longValue());
        try {
            apartmentDao.delete(oldMaxID);
            apartmentDao.delete(oldMaxID + 1);
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        Assume.assumeTrue(type == ApartmentDaoTest.Type.OTHER);
        ApartmentAnnounce apartmentAnnounceCreate = apartmentDao.create(apartmentAnnounce);
        boolean check = apartmentDao.findAll().contains(apartmentAnnounceCreate);
        assertEquals(true, check);
        try {
            apartmentDao.delete(apartmentAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }
}
