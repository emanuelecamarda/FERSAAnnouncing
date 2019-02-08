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
import java.util.Collections;
import java.util.List;

public class FavoriteFacadeController {

    private ApartmentResearchController apartmentResearchController = new ApartmentResearchController();
    private RoomResearchController roomResearchController = new RoomResearchController();

    public List<Research> findFavoriteResearches(User user) {
        List<Research> researches = new ArrayList<>();
        researches.addAll(apartmentResearchController.findFavorite(user));
        researches.addAll(roomResearchController.findFavorite(user));
        Collections.sort(researches, Collections.reverseOrder());
        return researches;
    }
}
