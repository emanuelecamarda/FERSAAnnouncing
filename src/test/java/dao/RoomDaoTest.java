package dao;


import entity.ApartmentAnnounce;
import entity.RoomAnnounce;
import entity.RoomResearch;
import entity.User;
import exception.EntityAlreadyExistException;
import exception.EntityNotExistException;
import factory.ApartmentFactory;
import factory.RoomFactory;
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
public class RoomDaoTest {

    enum Type {CREATE, OTHER, FAVORITE}

    private RoomDaoTest.Type type;
    private RoomAnnounce roomAnnounce;
    private RoomDao roomDao;
    private User user;
    private UserDao userDao;

    private RoomResearchDao roomResearchDao;
    private RoomResearch roomResearch;

    @Parameterized.Parameters
    public static Collection<Object[]> GetTestParameters() {
        return Arrays.asList(new Object[][] {
                // {Type ,ID, city, address, price, description, size, available, date, user , roomersNumber, privateBathroom, apartmentID }
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 3
                        ,Boolean.TRUE ,5},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 3,
                        Boolean.TRUE, 5},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 0,
                        Boolean.TRUE, 5},
                {Type.CREATE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 1,
                        Boolean.TRUE, 5},
                {Type.OTHER, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 0,
                        Boolean.TRUE, 5},
                {Type.OTHER, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 2,
                        Boolean.TRUE, 5},
                {Type.FAVORITE, 1 ,"Roma", "via ", 600.0, "una descrizione", 50 ,Boolean.TRUE,new GregorianCalendar(), new User().getNickname(), 0,
                        Boolean.TRUE, 5},

        });
    }

    public RoomDaoTest(RoomDaoTest.Type type , int ID, String city, String address, Double price, String description, double size,
                            boolean available, GregorianCalendar date, User user, int roomersNumber,
                            boolean privateBathroom, Integer apatmentID){

        this.roomDao=new RoomDao();
        this.type = type;
        this.userDao = new UserDao();
        this.user = UserFactory.getUser("nickname", "name", "surname",
                "email@gmail.com","password", 'f');
        this.roomAnnounce = RoomFactory.getRoomAnnounce(ID, city, address, price, description, size, available, date, this.user, roomersNumber, privateBathroom, apatmentID);
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
        Assume.assumeTrue(type == RoomDaoTest.Type.CREATE);
        RoomAnnounce roomAnnounceCreate = roomDao.create(roomAnnounce);
        try {
            roomDao.delete(roomAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(roomAnnounceCreate, roomAnnounce);
    }

    @Test
    public void findByID() {
        Assume.assumeTrue(type == RoomDaoTest.Type.OTHER);
        RoomAnnounce roomAnnounceCreate = roomDao.create(roomAnnounce);
        assertEquals(roomDao.findByID(roomAnnounceCreate.getID()), roomAnnounce);
        try {
            roomDao.delete(roomAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void delete() {
        Assume.assumeTrue(type == RoomDaoTest.Type.OTHER);
        RoomAnnounce roomAnnounceCreate = roomDao.create(roomAnnounce);
        try {
            roomDao.delete(roomAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(roomDao.findByID(roomAnnounceCreate.getID()), null);
    }

    @Test
    public void findByCondition(){
        Assume.assumeTrue(type == RoomDaoTest.Type.OTHER);
        RoomAnnounce roomAnnounceCreate = roomDao.create(roomAnnounce);
        List<RoomAnnounce> roomAnnouncesList = roomDao.findByCondition(roomResearch);
        try {
            roomDao.delete(roomAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
        boolean check = roomAnnouncesList.contains(roomAnnounceCreate);
        assertEquals(true, check);

    }

    @Test
    public void getMaxID() {
        Assume.assumeTrue(type == RoomDaoTest.Type.OTHER);
        roomDao.create(roomAnnounce);
        Integer oldMaxID = roomDao.getMaxID();
        roomDao.create(roomAnnounce);
        assertEquals(oldMaxID + 1, roomDao.getMaxID().longValue());
        try {
            roomDao.delete(oldMaxID);
            roomDao.delete(oldMaxID + 1);
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        Assume.assumeTrue(type == RoomDaoTest.Type.OTHER);
        RoomAnnounce roomAnnounceCreate = roomDao.create(roomAnnounce);
        boolean check = roomDao.findAll().contains(roomAnnounceCreate);
        assertEquals(true, check);
        try {
            roomDao.delete(roomAnnounceCreate.getID());
        } catch (EntityNotExistException e) {
            e.printStackTrace();
        }
    }

}
