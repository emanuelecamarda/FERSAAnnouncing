package entity;

import utils.Date;

import java.util.GregorianCalendar;

public class ApartmentResearch extends Research {
    private int localsMin;
    private int localsMax;
    private boolean furnished;
    private int bathroomNumberMin;
    private int bedsNumberMin;
    private int bedsNumberMax;

    public ApartmentResearch(String city, Double priceMin, Double priceMax, Double size, GregorianCalendar date,
                             Boolean favorite, User user, int ID, String sorting, int localsMin, int localsMax,
                             boolean furnished, int bathroomNumberMin, int bedsNumberMin, int bedsNumberMax) {
        super(city, priceMin, priceMax, size, date, favorite, user, ID);
        this.setSorting(sorting);
        this.localsMin = localsMin;
        this.localsMax = localsMax;
        this.furnished = furnished;
        this.bathroomNumberMin = bathroomNumberMin;
        this.bedsNumberMin = bedsNumberMin;
        this.bedsNumberMax = bedsNumberMax;
    }

    public ApartmentResearch() {
        super();
    }

    public int getLocalsMin() {
        return localsMin;
    }

    public void setLocalsMin(int localsMin) {
        this.localsMin = localsMin;
    }

    public int getLocalsMax() {
        return localsMax;
    }

    public void setLocalsMax(int localsMax) {
        this.localsMax = localsMax;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public int getBathroomNumberMin() {
        return bathroomNumberMin;
    }

    public void setBathroomNumberMin(int bathroomNumberMin) {
        this.bathroomNumberMin = bathroomNumberMin;
    }

    public int getBedsNumberMin() {
        return bedsNumberMin;
    }

    public void setBedsNumberMin(int bedsNumberMin) {
        this.bedsNumberMin = bedsNumberMin;
    }

    public int getBedsNumberMax() {
        return bedsNumberMax;
    }

    public void setBedsNumberMax(int bedsNumberMax) {
        this.bedsNumberMax = bedsNumberMax;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nsorting: " + this.sorting +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size;
    }
}
