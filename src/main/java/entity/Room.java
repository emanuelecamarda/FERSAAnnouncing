package entity;

import java.util.List;

public class Room extends Announce {
    private int roomersNumber;
    private boolean privateBathroom;
    private List[] roomers;

    public Room(int ID, String city, String address, Double price, String description, double size, boolean available, int roomersNumber, boolean privateBathroom, List[] roomers) {
        super(ID, city, address, price, description, size, available);
        this.roomersNumber = roomersNumber;
        this.privateBathroom = privateBathroom;
        this.roomers = roomers;
    }

    public Room(int roomersNumber, boolean privateBathroom, List[] roomers) {
        this.roomersNumber = roomersNumber;
        this.privateBathroom = privateBathroom;
        this.roomers = roomers;
    }

    public Room() {

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
}
