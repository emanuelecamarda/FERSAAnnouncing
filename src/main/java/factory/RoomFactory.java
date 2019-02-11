package factory;

import entity.Announce;
import entity.RoomAnnounce;
import entity.User;

import java.util.GregorianCalendar;
import java.util.List;

public class RoomFactory {

    private RoomFactory() {}

    public static Announce getRoom (int ID, String city, String address, Double price, String description, double size, boolean available,
                                    GregorianCalendar date, User user, int roomersNumber, boolean privateBathroom, Integer apartmentID){

        RoomAnnounce roomAnnounce = new RoomAnnounce(ID ,city, address, price, description, size, available, date,
                user, roomersNumber, privateBathroom, apartmentID);
        return roomAnnounce;
    }
}
