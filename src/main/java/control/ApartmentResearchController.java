/**
 * Edit by EC
 */

package control;

import bean.ApartmentResearchBean;
import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import entity.ApartmentAnnounce;
import entity.ApartmentResearch;
import entity.Sorting;
import entity.User;
import exception.CreationFailedException;
import factory.ResearchFactory;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class ApartmentResearchController {

    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private ApartmentDao apartmentDao = new ApartmentDao();

    public void newApartmentResearch(ApartmentResearchBean apartmentResearchBean, User userLogged)
            throws CreationFailedException {
        // create a new istance of ApartmentResearch
        GregorianCalendar date = new GregorianCalendar();
        ApartmentResearch apartmentResearch = ResearchFactory.getApartmentResearch(null,
                apartmentResearchBean.getCity(), apartmentResearchBean.getPriceMin(),
                apartmentResearchBean.getPriceMax(), apartmentResearchBean.getSize(), date,
                apartmentResearchBean.getFavorite(), userLogged, String.valueOf(apartmentResearchBean.getSorting()),
                apartmentResearchBean.getLocalsMin(), apartmentResearchBean.getLocalsMax(),
                apartmentResearchBean.getFurnished(), apartmentResearchBean.getBathroomNumberMin(),
                apartmentResearchBean.getBedsNumberMin(),apartmentResearchBean.getBedsNumberMax());

        // save the new research on DB
        ApartmentResearch savedResearch = apartmentResearchDao.create(apartmentResearch);
        if (savedResearch == null)
            throw new CreationFailedException();

        return;
    }
}
