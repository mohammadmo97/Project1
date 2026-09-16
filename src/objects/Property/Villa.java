package objects.Property;

import java.util.UUID;


public class Villa extends Property {
    private int yardArea;
    private int floors;

    public Villa(String id, int area, int bedrooms, int floors, int region, String ownerId, String mostajer, int bathrooms, int yardArea) {
        super(id, area, bedrooms, floors, region, ownerId, mostajer, bathrooms );

        this.floors=floors;
        this.yardArea=yardArea;
    }

    public long price(){
        long yardPricePerMeter=15000000;
        long floorPremium=20000000;

        return basePrice() +((long) this.yardArea *yardPricePerMeter) +((long) this.floors *floorPremium);
    }

    public int getYardArea(){return yardArea;}
    public int getFloors(){return floors;}

    String villaID=UUID.randomUUID().toString();
    public String getVillaID(){return "2" + UUID.randomUUID();}

    public void showDetails() {
        System.out.println("~~~~~ جزئیات ویلا ~~~~~");
        System.out.println("شناسه: "+getId());
        System.out.println("مالک: " +getOwnerId());
        System.out.println("منطقه: "+getRegion());
        System.out.println("متراژ: "+ getArea());
        System.out.println("تعداد اتاقخواب: "+getBedrooms());
        System.out.println("طبقه: "+getFloors());
        System.out.println("متراژ حیاط: "+ yardArea);
        System.out.println("قیمت نهایی: "+price());
        System.out.println("اجاره ماهیانه: "+monthlyPrice());
    }
}
