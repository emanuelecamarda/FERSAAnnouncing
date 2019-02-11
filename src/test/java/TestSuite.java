import dao.ApartmentResearchDaoTest;
import dao.RoomResearchDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        ApartmentResearchDaoTest.class,
        RoomResearchDaoTest.class,
})
public class TestSuite {
}
