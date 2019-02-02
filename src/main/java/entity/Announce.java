package entity;

public abstract class Announce {
    protected int ID;
    protected String city;
    protected String address;
    protected Double price;
    protected String description;
    protected double size;
    protected boolean available;

    public Announce(int ID , String city , String address , Double price , String description , double size , boolean available) {
         this.ID = ID;
         this.city = city;
         this.address = address;
         this.price = price;
         this.description = description;
         this.size = size;
         this.available = available;

    }

    public Announce() {}

    public int getID(){ return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Double getprice() {
        return price;
    }
    public void setprice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }

    public boolean getavailable() {return  available;}
    public void  setAvailable(boolean available) {this.available = available;}
}
