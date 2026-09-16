package objects.Property;

import java.util.UUID;


public class Apartment extends Property {
    private int unitNumber;
    private int aparatmentFloors;
    private int unitsInApartment;

    public Apartment(String id, int area, int bedrooms, int floors, int region, String ownerId, String mostajer, int bathrooms, int unitNumber, int aparatmentFloors, int unitsInApartment) {
        super(id, area, bedrooms, floors, region, ownerId, mostajer, bathrooms );
        this.unitNumber=unitNumber;
        this.aparatmentFloors=aparatmentFloors;
        this.unitsInApartment=unitsInApartment;
    }


    public long price(){return (long) (basePrice() * (1 +(0.03*getBedrooms()))* (1 +(0.01*getFloors())));}

    public int getUnitNumber(){return unitNumber;}

    public int getApartmentFloors(){return aparatmentFloors;}

    public int getUnitsInApartment(){return unitsInApartment;}


    String apartmentID=UUID.randomUUID().toString();
    public String getApartmentID(){return "1" + UUID.randomUUID();}

    public void showDetails(){
        System.out.println("~~~~~ جزئیات آپارتمان ~~~~~");
        System.out.println("شناسه: " + getId());
        System.out.println("مالک: " + getOwnerId());
        System.out.println("منطقه: " + getRegion());
        System.out.println("متراژ: " + getArea());
        System.out.println("تعداد اتاقخواب: " + getBedrooms());
        System.out.println("طبقه: " + getFloors());
        System.out.println("شماره واحد: " + unitNumber);
        System.out.println("قیمت نهایی: " + price());
        System.out.println("اجاره ماهیانه: " + monthlyPrice());
    }
}
