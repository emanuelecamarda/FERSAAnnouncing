/**
 * Edit by EC
 */
package control;

import dao.ApartmentResearchDao;
import dao.RoomDao;
import dao.RoomResearchDao;
import entity.Announce;
import entity.Research;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class FavoriteController {

    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private RoomResearchDao roomResearchDao = new RoomResearchDao();

    public List<Research> findFavoriteResearches(User user) {
        List<Research> researches = new ArrayList<Research>();
        researches.addAll(apartmentResearchDao.findFavorite(user));
        researches.addAll(roomResearchDao.findFavorite(user));
        return researches;
    }
}
