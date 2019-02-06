package control;

import dao.ApartmentResearchDao;
import dao.RoomResearchDao;
import entity.Research;
import entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecentController {

    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private RoomResearchDao roomResearchDao = new RoomResearchDao();

    public List<Research> recentResearch(User user) {
        List<Research> researches = new ArrayList<>();
        researches.addAll(apartmentResearchDao.findRecent(user));
        researches.addAll(roomResearchDao.findRecent(user));
        Collections.sort(researches, Collections.reverseOrder());
        return researches;
    }
}
