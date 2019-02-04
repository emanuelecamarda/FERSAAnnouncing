package factory;

import entity.Announce;
import entity.Apartment;

public class ApartmentFactory {

    private ApartmentFactory(){}

    public static Announce getApartment (int ID , String city , String address , Double price , String description , double size , boolean available , int locals ,
                                            boolean furnished , int bathroomNumber , int bedsNumber){

        Apartment apartment = new Apartment(ID ,city ,address , price , description , size , available ,locals , furnished , bathroomNumber ,
                                             bedsNumber);
        return apartment;
    }

}
