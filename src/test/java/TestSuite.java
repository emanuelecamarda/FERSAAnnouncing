import dao.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        ApartmentResearchDaoTest.class,
        RoomResearchDaoTest.class,
        UserDaoTest.class,
        ApartmentDaoTest.class,
        RoomDaoTest.class,
})
public class TestSuite {
}
