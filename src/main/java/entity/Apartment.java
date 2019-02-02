package entity;

public class Apartment extends Announce {
    private int locals;
    private boolean furnished;
    private int bathroomNumber;
    private int bedsNumber;

    public Apartment (int ID , String city , String address , Double price , String description , double size , boolean available , int locals ,
                      boolean furnished , int bathroomNumber , int bedsNumber) {

                           super(ID , city , address ,price ,description , size , available);
                           this.locals = locals;
                           this.furnished=furnished;
                           this.bathroomNumber=bathroomNumber;
                           this.bedsNumber=bedsNumber;

    }

    public Apartment() {}

    public int getlocals() {
        return locals;
    }
    public void setLocals(Integer localsMin) {
        this.locals = locals;
    }

    public boolean getfurnished() {return furnished;}
    public void  setfurnished(boolean furnished) {this.furnished=furnished;}

    public int getbathroomNumber(){return  bathroomNumber;}
    public void setbathroomNumber(int bathroomNumber) {this.bathroomNumber = bathroomNumber;}

    public int getbedsNumber(){return  bedsNumber;}
    public void setbedsNumber(int bedsNumber) {this.bedsNumber=bedsNumber;}
}

