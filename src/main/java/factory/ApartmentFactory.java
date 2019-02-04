package factory;

import entity.Announce;
import entity.Apartment;
import entity.User;
import sun.util.calendar.Gregorian;

import java.util.GregorianCalendar;

public class ApartmentFactory {

    private ApartmentFactory(){}

    public static Announce getApartment (int ID , String city , String address , Double price , String description , double size , boolean available , GregorianCalendar date, User user, int locals ,
                                         boolean furnished , int bathroomNumber , int bedsNumber){

        Apartment apartment = new Apartment(ID ,city ,address , price , description , size , available , date , user ,locals , furnished , bathroomNumber ,
                                             bedsNumber);
        return apartment;
    }

}
