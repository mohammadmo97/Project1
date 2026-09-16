package objects.Property;

import objects.User;


public abstract class Property  {

    private String id;
    private int area;
    private int bedrooms;
    private int floors;
    private int region;
    private String ownerId;
    private String mostajer;
    private int bathrooms;

    private static long bppm=50_000_000; // base price per meter
    private static double rent_rate=0.004;

    public Property(String id, int area, int bedrooms, int floors, int region, String ownerId, String mostajer, int bathrooms){
        this.id=id;
        this.area=area;
        this.bedrooms=bedrooms;
        this.floors=floors;
        this.region=region;
        this.ownerId=ownerId;
        this.mostajer=mostajer;
        this.bathrooms= bathrooms;

    }

    public long basePrice(){
        double coefficient;

        if(this.region==1){
            coefficient =1.8;
        }else if(this.region==2){
            coefficient=1.4;
        }else if(this.region==3){
            coefficient=1.1;
        }else if(this.region==4) {
            coefficient= 0.8;
        }else{
            coefficient=1;
        }

        return (long) (this.area*bppm*coefficient);
    }

    public abstract long price();
    public abstract void showDetails();


    public String getId(){return id;}
    public int getArea(){return area;}
    public int getBedrooms(){return bedrooms;}
    public int getFloors(){return floors;}
    public int getRegion(){return region;}
    public String getOwnerId(){return ownerId;}
    public String getMostajer(){return mostajer;}
    public int getBathrooms(){return bathrooms;}
    public void setMostajer(String ownerId){this.mostajer=ownerId;}
    public void setOwnerId(User owner){this.ownerId=owner.getId();}


    public long monthlyPrice(){return (long) (price()*rent_rate);}

    private  boolean isRented=false;
    public boolean isRented(){return isRented;}
    public  void setRented(boolean rented){ isRented=rented;}
 }