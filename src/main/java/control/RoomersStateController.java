package control;

import dao.RoomDao;
import entity.ConcreteRoomAnnounceObserver;
import entity.RoomAnnounce;
import sun.awt.SunHints;

import java.util.List;

public class RoomersStateController {

    private static RoomersStateController instance = null;
    private static RoomDao roomDao = null;

    private RoomersStateController() {}

    public static RoomersStateController getInstance() {
        if (instance == null) {
            instance = new RoomersStateController();
            roomDao = new RoomDao();
        }
        return instance;
    }

    public void updateState(Integer roomID, Integer newValue) {
        roomDao.addRoomersNumber(roomDao.findByID(roomID), newValue);
    }
}
