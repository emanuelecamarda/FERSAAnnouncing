package control;

import bean.ApartmentResearchBean;
import bean.RoomResearchBean;
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

    public void newRoomResearch(RoomResearchBean roomResearchBean, User userLogged) throws CreationFailedException {
        // create a new istance of RoomResearch
        GregorianCalendar date = new GregorianCalendar();
        RoomResearch roomResearch = ResearchFactory.getRoomResearch(null, roomResearchBean.getCity(),
                roomResearchBean.getPriceMin(), roomResearchBean.getPriceMax(), roomResearchBean.getSize(), date,
                roomResearchBean.getFavorite(), userLogged, String.valueOf(roomResearchBean.getSorting()),
                roomResearchBean.getRoomersNumberMax(), roomResearchBean.getPrivateBathroom(),
                roomResearchBean.getOnlyFemale(), roomResearchBean.getOnlyMale());

        // save the new research on DB
        RoomResearch savedResearch = roomResearchDao.create(roomResearch);
        if (savedResearch == null)
            throw new CreationFailedException();

        return;
    }
}
