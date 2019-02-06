package entity;

import utils.Date;

import java.util.GregorianCalendar;
import java.util.List;

public class RoomAnnounce extends Announce {
    private int roomersNumber;
    private boolean privateBathroom;
    private List[] roomers;

    public RoomAnnounce(int ID, String city, String address, Double price, String description, double size, boolean available, GregorianCalendar date, User user, int roomersNumber, boolean privateBathroom, List[] roomers) {
        super(ID, city, address, price, description, size, available, date, user);
        this.roomersNumber = roomersNumber;
        this.privateBathroom = privateBathroom;
        this.roomers = roomers;
    }

    public RoomAnnounce(int roomersNumber, boolean privateBathroom, List[] roomers) {
        this.roomersNumber = roomersNumber;
        this.privateBathroom = privateBathroom;
        this.roomers = roomers;
    }

    public RoomAnnounce() {

    }

    public int getRoomersNumber() {
        return roomersNumber;
    }

    public void setRoomersNumber(int roomersNumber) {
        this.roomersNumber = roomersNumber;
    }

    public boolean isPrivateBathroom() {
        return privateBathroom;
    }

    public void setPrivateBathroom(boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }

    public List[] getRoomers() {
        return roomers;
    }

    public void setRoomers(List[] roomers) {
        this.roomers = roomers;
    }

    @Override
    public String toString() {
        return "----- RoomAnnounce -----\n" +
                "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size +
                "\nprice: " + this.price + "\n";
    }
}
