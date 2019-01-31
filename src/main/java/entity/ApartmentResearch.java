package entity;

import utils.Date;

import java.util.GregorianCalendar;

public class ApartmentResearch extends Research {
    private Integer localsMin;
    private Integer localsMax;
    private Boolean furnished;
    private Integer bathroomNumberMin;
    private Integer bedsNumberMin;
    private Integer bedsNumberMax;

    public ApartmentResearch(String city, Double priceMin, Double priceMax, Double size, GregorianCalendar date,
                             Boolean favorite, User user, Integer ID, Integer localsMin, Integer localsMax,
                             Boolean furnished, Integer bathroomNumberMin, Integer bedsNumberMin,
                             Integer bedsNumberMax) {
        super(city, priceMin, priceMax, size, date, favorite, user, ID);
        this.localsMin = localsMin;
        this.localsMax = localsMax;
        this.furnished = furnished;
        this.bathroomNumberMin = bathroomNumberMin;
        this.bedsNumberMin = bedsNumberMin;
        this.bedsNumberMax = bedsNumberMax;
    }

    public ApartmentResearch(String city, Double priceMin, Double priceMax, Double size, GregorianCalendar date,
                             Boolean favorite, User user, Integer localsMin, Integer localsMax, Boolean furnished,
                             Integer bathroomNumberMin, Integer bedsNumberMin, Integer bedsNumberMax) {
        super(city, priceMin, priceMax, size, date, favorite, user);
        this.localsMin = localsMin;
        this.localsMax = localsMax;
        this.furnished = furnished;
        this.bathroomNumberMin = bathroomNumberMin;
        this.bedsNumberMin = bedsNumberMin;
        this.bedsNumberMax = bedsNumberMax;
    }

    public ApartmentResearch() {
    }

    public Integer getLocalsMin() {
        return localsMin;
    }

    public void setLocalsMin(Integer localsMin) {
        this.localsMin = localsMin;
    }

    public Integer getLocalsMax() {
        return localsMax;
    }

    public void setLocalsMax(Integer localsMax) {
        this.localsMax = localsMax;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Integer getBathroomNumberMin() {
        return bathroomNumberMin;
    }

    public void setBathroomNumberMin(Integer bathroomNumberMin) {
        this.bathroomNumberMin = bathroomNumberMin;
    }

    public Integer getBedsNumberMin() {
        return bedsNumberMin;
    }

    public void setBedsNumberMin(Integer bedsNumberMin) {
        this.bedsNumberMin = bedsNumberMin;
    }

    public Integer getBedsNumberMax() {
        return bedsNumberMax;
    }

    public void setBedsNumberMax(Integer bedsNumberMax) {
        this.bedsNumberMax = bedsNumberMax;
    }

    @Override
    public String toString() {
        return "----- ApartmentResearch -----\n" +
                "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nsorting: " + this.sorting +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size + "\n";
    }
}
