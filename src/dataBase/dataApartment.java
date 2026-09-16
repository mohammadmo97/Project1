package dataBase;

import objects.Property.Apartment;

public class dataApartment extends dataProperty {
    public String setApartmentInLine(Apartment a) {
        return setPropertyLine(a) + "," + a.getUnitNumber() + "," + a.getApartmentFloors() + "," + a.getUnitsInApartment();
    }

    public static Apartment getApartmentInLine(String line) {
        String[] f = line.split(",");
        return new Apartment(f[1], Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), f[6], f[7], Integer.parseInt(f[8]), Integer.parseInt(f[10]), Integer.parseInt(f[11]),Integer.parseInt(f[12]));
    }
}