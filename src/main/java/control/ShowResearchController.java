package control;

import bean.ApartmentResearchBean;
import bean.ResearchBean;
import bean.RoomResearchBean;
import dao.ApartmentDao;
import dao.ApartmentResearchDao;
import dao.RoomDao;
import dao.RoomResearchDao;
import entity.*;
import factory.ResearchFactory;
import factory.RoomFactory;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ShowResearchController {

    private RoomResearchDao roomResearchDao = new RoomResearchDao();
    private RoomDao roomDao = new RoomDao();
    private ApartmentResearchDao apartmentResearchDao = new ApartmentResearchDao();
    private ApartmentDao apartmentDao = new ApartmentDao();

    public List<Announce> showCurrentResearch(ResearchBean researchBean , User loggedUser){

        List<Announce> announces = new ArrayList<Announce>();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        if (researchBean.getClass().equals(ApartmentResearchBean.class)){

            ApartmentResearch research = ResearchFactory.getApartmentResearch(null , researchBean.getCity() , researchBean.getPriceMin() , researchBean.getPriceMax() , researchBean.getSize() , gregorianCalendar , researchBean.getFavorite() , loggedUser ,researchBean.getSorting().toString() , ((ApartmentResearchBean) researchBean).getLocalsMin() , ((ApartmentResearchBean) researchBean).getLocalsMax() , ((ApartmentResearchBean) researchBean).getFurnished() , ((ApartmentResearchBean) researchBean).getBathroomNumberMin() , ((ApartmentResearchBean) researchBean).getBedsNumberMin() , ((ApartmentResearchBean) researchBean).getBedsNumberMax() );
            if (apartmentDao.findByCondition(research) != null)
                announces.addAll(apartmentDao.findByCondition(research));
        }
        else {

            RoomResearch research = ResearchFactory.getRoomResearch(null , researchBean.getCity() , researchBean.getPriceMin() , researchBean.getPriceMax() , researchBean.getSize() , gregorianCalendar , researchBean.getFavorite() , loggedUser , researchBean.getSorting().toString() , ((RoomResearchBean) researchBean).getRoomersNumberMax() , ((RoomResearchBean) researchBean).getPrivateBathroom() ,((RoomResearchBean) researchBean).getOnlyFemale() , ((RoomResearchBean) researchBean).getOnlyMale() );
            if (roomDao.findByCondition(research) != null)
                announces.addAll(roomDao.findByCondition(research));
        }
        return announces;


    }


}
