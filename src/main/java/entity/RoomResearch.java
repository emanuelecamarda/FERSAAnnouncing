package entity;

import utils.Date;

import java.util.GregorianCalendar;

public class RoomResearch extends Research {
    private Integer roomersNumberMax;
    private Boolean privateBathroom;
    private Boolean onlyFemale;
    private Boolean onlyMale;

    public RoomResearch(String city, Double priceMin, Double priceMax, Double size, GregorianCalendar date,
                        Boolean favorite, User user, Integer roomersNumberMax, Boolean privateBathroom,
                        Boolean onlyFemale, Boolean onlyMale) {
        super(city, priceMin, priceMax, size, date, favorite, user);
        this.roomersNumberMax = roomersNumberMax;
        this.privateBathroom = privateBathroom;
        this.onlyFemale = onlyFemale;
        this.onlyMale = onlyMale;
    }

    public RoomResearch() {

    }

    public Integer getRoomersNumberMax() {
        return roomersNumberMax;
    }

    public void setRoomersNumberMax(Integer roomersNumberMax) {
        this.roomersNumberMax = roomersNumberMax;
    }

    public Boolean getPrivateBathroom() {
        return privateBathroom;
    }

    public void setPrivateBathroom(Boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }

    public Boolean getOnlyFemale() {
        return onlyFemale;
    }

    public void setOnlyFemale(Boolean onlyFemale) {
        this.onlyFemale = onlyFemale;
    }

    public Boolean getOnlyMale() {
        return onlyMale;
    }

    public void setOnlyMale(Boolean onlyMale) {
        this.onlyMale = onlyMale;
    }

    @Override
    public String toString() {
        return "----- RoomResearch -----\n" +
                "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nsorting: " + this.sorting +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size + "\n";
    }
}
