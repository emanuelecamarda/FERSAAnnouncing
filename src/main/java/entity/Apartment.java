package entity;

import utils.Date;

import java.util.GregorianCalendar;

public class Apartment extends Announce {
    private int locals;
    private boolean furnished;
    private int bathroomNumber;
    private int bedsNumber;

   /* public Apartment (int ID , String city , String address , Double price , String description , double size , boolean available , int locals ,
                      boolean furnished , int bathroomNumber , int bedsNumber) {

                           super(ID , city , address ,price ,description , size , available);
                           this.locals = locals;
                           this.furnished=furnished;
                           this.bathroomNumber=bathroomNumber;
                           this.bedsNumber=bedsNumber;

    }*/

    public Apartment(int ID, String city, String address, Double price, String description, double size, boolean available, GregorianCalendar date, User user, int locals, boolean furnished, int bathroomNumber, int bedsNumber) {
        super(ID, city, address, price, description, size, available, date, user);
        this.locals = locals;
        this.furnished = furnished;
        this.bathroomNumber = bathroomNumber;
        this.bedsNumber = bedsNumber;
    }

    public Apartment(int locals, boolean furnished, int bathroomNumber, int bedsNumber) {
        this.locals = locals;
        this.furnished = furnished;
        this.bathroomNumber = bathroomNumber;
        this.bedsNumber = bedsNumber;
    }

    public Apartment() {}

   /* public int getlocals() {
        return locals;
    }
    public void setLocals(Integer localsMin) {
        this.locals = locals;
    }*/

   /* public boolean getfurnished() {return furnished;}
    public void  setfurnished(boolean furnished) {this.furnished=furnished;}

    public int getbathroomNumber(){return  bathroomNumber;}
    public void setbathroomNumber(int bathroomNumber) {this.bathroomNumber = bathroomNumber;}

    public int getbedsNumber(){return  bedsNumber;}
    public void setbedsNumber(int bedsNumber) {this.bedsNumber=bedsNumber;}*/

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
        return "----- Apartment -----\n" +
                "ID: " + this.ID +
                "\ncity: " + this.city +
                "\ndate: "  + Date.gregorianCalendarToString(this.date) +
                "\nuser: " + this.user.getNickname() +
                "\nsize: " + this.size +
                "\nprice: " + this.price + "\n";
    }
}

