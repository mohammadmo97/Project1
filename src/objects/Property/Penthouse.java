package objects.Property;

import java.util.UUID;


public class Penthouse extends Property {
    private int terrasArea;

    public Penthouse(String id, int area, int bedrooms, int floors, int region, String ownerId, String mostajer, int bathrooms, int terrasArea) {
        super(id, area, bedrooms, floors, region, ownerId, mostajer, bathrooms);

        this.terrasArea=terrasArea;
    }

    public long price(){
        double LuxuryCoefficient= 2;
        long TerracePricePerMeter= 20000000;

        return (long) ((basePrice()*LuxuryCoefficient)+  ((long) this.terrasArea *TerracePricePerMeter));
    }

    public int getTerrasArea() {return terrasArea;}

    String penthouseID=UUID.randomUUID().toString();
    public String getPenthouseID(){return "3" + UUID.randomUUID();}

    public void showDetails() {
        System.out.println("~~~~~ جزئیات پنت هاوس ~~~~~");
        System.out.println("شناسه: " + getId());
        System.out.println("مالک: " + getOwnerId());
        System.out.println("منطقه: " + getRegion());
        System.out.println("متراژ: " + getArea());
        System.out.println("تعداد اتاقخواب: " + getBedrooms());
        System.out.println("طبقه: " + getFloors());
        System.out.println("متراژ تراس: " + terrasArea);
        System.out.println("قیمت نهایی: " + price());
        System.out.println("اجاره ماهیانه: " + monthlyPrice());
    }
}
