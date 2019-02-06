package control;

import dao.RoomDao;
import dao.RoomResearchDao;
import entity.*;
import exception.CreationFailedException;
import factory.ResearchFactory;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class RoomResearchController {

    private RoomResearchDao roomResearchDao = new RoomResearchDao();
    private RoomDao roomDao = new RoomDao();

    public List<RoomAnnounce> newRoomResearch(String city, Double priceMin, Double priceMax, Double size, Boolean favorite,
                                              User user, String sorting, Boolean privateBathroom, Integer roomersNumberMax,
                                              Boolean onlyFemale, Boolean onlyMale) throws CreationFailedException {
        // create a new istance of RoomResearch
        GregorianCalendar date = new GregorianCalendar();
        RoomResearch roomResearch = ResearchFactory.getRoomResearch(null, city, priceMin, priceMax,
                size, date, favorite, user, sorting, roomersNumberMax, privateBathroom, onlyFemale, onlyMale);

        // save the new research on DB
        RoomResearch savedResearch = roomResearchDao.create(roomResearch);
        if (savedResearch == null)
            throw new CreationFailedException();

        List<RoomAnnounce> roomAnnounces = roomDao.findByCondition(roomResearch);
        if (roomResearch.getOnlyFemale()) {
            for (RoomAnnounce roomAnnounce : roomAnnounces) {
                if (roomAnnounce.getUser().getGender().equals(Gender.male))
                    roomAnnounces.remove(roomAnnounce);
            }
        }
        if (roomResearch.getOnlyMale()) {
            for (RoomAnnounce roomAnnounce : roomAnnounces) {
                if (roomAnnounce.getUser().getGender().equals(Gender.female))
                    roomAnnounces.remove(roomAnnounce);
            }
        }

        if (roomResearch.getSorting().equals(Sorting.moreRecent))
            Collections.sort(roomAnnounces, Collections.reverseOrder());
        if (roomResearch.getSorting().equals(Sorting.lessRecent))
            Collections.sort(roomAnnounces);
        return roomAnnounces;
    }
}
