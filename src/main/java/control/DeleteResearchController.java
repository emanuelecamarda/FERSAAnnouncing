/**
 * Edit by EC
 */
package control;

import dao.ApartmentResearchDao;
import dao.RoomResearchDao;
import entity.ApartmentResearch;
import entity.Research;
import exception.EntityNotExistException;

public class DeleteResearchController {

    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private RoomResearchDao roomResearchDao = new RoomResearchDao();

    public Boolean deleteResearch(Research research) {

        try {
            if (research.getClass().equals(ApartmentResearch.class)) {
                return apartmentResearchDao.delete(research.getID());
            } else {
                return roomResearchDao.delete(research.getID());
            }

        } catch (EntityNotExistException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
