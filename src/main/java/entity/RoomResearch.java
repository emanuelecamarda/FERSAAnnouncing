package entity;

import java.util.GregorianCalendar;

public class RoomResearch extends Research {
    private int roomersNumberMax;
    private boolean privateBathroom;
    private boolean onlyFemale;
    private boolean onlyMale;

    public RoomResearch(String city, Double priceMin, Double priceMax, Double size, GregorianCalendar date,
                        Boolean favorite, User user, int ID, int roomersNumberMax, boolean privateBathroom,
                        boolean onlyFemale, boolean onlyMale) {
        super(city, priceMin, priceMax, size, date, favorite, user, ID);
        this.roomersNumberMax = roomersNumberMax;
        this.privateBathroom = privateBathroom;
        this.onlyFemale = onlyFemale;
        this.onlyMale = onlyMale;
    }

    public RoomResearch() {

    }

    public int getRoomersNumberMax() {
        return roomersNumberMax;
    }

    public void setRoomersNumberMax(int roomersNumberMax) {
        this.roomersNumberMax = roomersNumberMax;
    }

    public boolean isPrivateBathroom() {
        return privateBathroom;
    }

    public void setPrivateBathroom(boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }

    public boolean isOnlyFemale() {
        return onlyFemale;
    }

    public void setOnlyFemale(boolean onlyFemale) {
        this.onlyFemale = onlyFemale;
    }

    public boolean isOnlyMale() {
        return onlyMale;
    }

    public void setOnlyMale(boolean onlyMale) {
        this.onlyMale = onlyMale;
    }
}
