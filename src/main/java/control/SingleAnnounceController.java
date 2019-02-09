package control;

import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import dao.RoomDao;
import dao.RoomResearchDao;
import entity.Announce;
import entity.ApartmentAnnounce;
import entity.RoomAnnounce;

public class SingleAnnounceController {

    private RoomResearchDao roomResearchDao = new RoomResearchDao();
    private RoomDao roomDao = new RoomDao();
    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private ApartmentDao apartmentDao = new ApartmentDao();


    public static ApartmentAnnounce ShowSelectedAAnnounce(int selectedAnnounce){

        ApartmentDao apartmentDao = new ApartmentDao();
        ApartmentAnnounce apartmentAnnounce = apartmentDao.findByID(selectedAnnounce);

        return apartmentAnnounce;

    }

    public static RoomAnnounce ShowSelectedRAnnounce(int selectedAnnounce){

        RoomDao roomDao = new RoomDao();
        RoomAnnounce roomAnnounce = roomDao.findByID(selectedAnnounce);

        return roomAnnounce;
    }
}
