package entity;

import utils.Date;

import java.util.GregorianCalendar;

public class ApartmentAnnounce extends Announce {
    private int locals;
    private boolean furnished;
    private int bathroomNumber;
    private int bedsNumber;


    public ApartmentAnnounce(int ID, String city, String address, Double price, String description, double size, boolean available, GregorianCalendar date, User user, int locals, boolean furnished, int bathroomNumber, int bedsNumber) {
        super(ID, city, address, price, description, size, available, date, user);
        this.locals = locals;
        this.furnished = furnished;
        this.bathroomNumber = bathroomNumber;
        this.bedsNumber = bedsNumber;
    }

    public ApartmentAnnounce(int locals, boolean furnished, int bathroomNumber, int bedsNumber) {
        this.locals = locals;
        this.furnished = furnished;
        this.bathroomNumber = bathroomNumber;
        this.bedsNumber = bedsNumber;
    }

    public ApartmentAnnounce() {}


    public int getLocals() {
        return locals;
    }

    public void setLocals(int locals) {
        this.locals = locals;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public int getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(int bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    @Override
    public String toString() {
        return "----- ApartmentAnnounce -----\n" +
                "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size +
                "\nprice: " + this.price + "\n";
    }
}

