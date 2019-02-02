/**
 * Edit by EC
 */

package control;

import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import entity.Apartment;
import entity.ApartmentResearch;
import entity.User;
import exception.CreationFailedException;
import factory.ResearchFactory;

import java.util.GregorianCalendar;
import java.util.List;

public class ApartmentResearchController {

    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private ApartmentDao apartmentDao = new ApartmentDao();

    public List<Apartment> newApartmentResearch(Integer ID, String city, Double priceMin, Double priceMax, Double size,
                                                Boolean favorite, User user, String sorting,
                                                Integer localsMin, Integer localsMax, Boolean furnished,
                                                Integer bathroomNumberMin, Integer bedsNumberMin,
                                                Integer bedsNumberMax) throws CreationFailedException {
        // create a new istance of ApartmentResearch
        GregorianCalendar date = new GregorianCalendar();
        ApartmentResearch apartmentResearch = ResearchFactory.getApartmentResearch(null, city, priceMin, priceMax,
                size, date, favorite, user, sorting, localsMin, localsMax, furnished, bathroomNumberMin, bedsNumberMin,
                bedsNumberMax);

        // save the new research on DB
        ApartmentResearch savedResearch = apartmentResearchDao.create(apartmentResearch);
        if (savedResearch == null)
            throw new CreationFailedException();

        List<Apartment> apartments = apartmentDao.findByCondition(apartmentResearch);
        return apartments;
    }
}
