/**
 * Edit by EC.
 */

package factory;

import entity.*;

import java.util.GregorianCalendar;

public class ResearchFactory {

    private ResearchFactory() {}

    public static Research getResearch(String type) {
        if (type.equalsIgnoreCase("ApartmentResearch")) return new ApartmentResearch();
        if (type.equalsIgnoreCase("RoomResearch")) return new RoomResearch();
        return null;
    }

    public static ApartmentResearch getApartmentResearch(Integer ID, String city, Double priceMin, Double priceMax, Double size,
                                                         GregorianCalendar date, Boolean favorite, User user, String sorting,
                                                         Integer localsMin, Integer localsMax, Boolean furnished,
                                                         Integer bathroomNumberMin, Integer bedsNumberMin,
                                                         Integer bedsNumberMax) {
        ApartmentResearch apartmentResearch = new ApartmentResearch(city, priceMin, priceMax, size, date, favorite,
                user, localsMin, localsMax, furnished, bathroomNumberMin, bedsNumberMin, bedsNumberMax);
        apartmentResearch.setSorting(sorting);
        apartmentResearch.setID(ID);
        return apartmentResearch;
    }

    public static RoomResearch getRoomResearch(Integer ID, String city, Double priceMin, Double priceMax, Double size,
                                               GregorianCalendar date, Boolean favorite, User user, String sorting,
                                               Integer roomersNumberMax, Boolean privateBathroom, Boolean onlyFemale,
                                               Boolean onlyMale) {
        RoomResearch roomResearch = new RoomResearch(city, priceMin, priceMax, size, date, favorite, user,
                roomersNumberMax, privateBathroom, onlyFemale, onlyMale);
        roomResearch.setSorting(sorting);
        roomResearch.setID(ID);
        return roomResearch;
    }
}
