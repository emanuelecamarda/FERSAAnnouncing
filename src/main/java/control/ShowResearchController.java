package control;

import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import dao.RoomDao;
import dao.RoomResearchDao;
import entity.Announce;
import entity.Research;

import java.util.ArrayList;
import java.util.List;

public class ShowResearchController {

    private RoomResearchDao roomResearchDao = new RoomResearchDao();
    private RoomDao roomDao = new RoomDao();
    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private ApartmentDao apartmentDao = new ApartmentDao();

    public List<Announce> showCurrentResearch(Research research){

        List<Announce> currentAnnounce = new ArrayList<Announce>();
        currentAnnounce.findByCondition(research);
    }


}
