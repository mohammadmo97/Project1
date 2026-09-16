package menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import objects.Contract;
import objects.Property.Apartment;
import objects.Property.Villa;
import objects.Property.Penthouse;
import objects.Property.Property;
import objects.User;
import user.Manage;


public class MainMenu{
    private User currentUser;
   private  Manage manage;
   private Scanner sc=new Scanner(System.in);

    public MainMenu(User user,Manage manage){
        this.currentUser=user;
        this.manage=manage;
    }

    public void display(){
        while(true){
            System.out.println("~~~~~ به صفحه اصلی خوش آمدید ~~~~~");
            System.out.println( "کاربر " + currentUser.getUserName() + " خوش آمدید!");
            System.out.println("بودجه شما: " + currentUser.getBudget());
            System.out.println("===================");
            System.out.println("1_ دیدن مشخصات شما");
            System.out.println("2_ ثبت ملک خودتان");
            System.out.println("3_ جستجو ملک موردنظر");
            System.out.println("4_ خرید و اجاره");
            System.out.println("5_ قراردادهای من");
            System.out.println("6_ خروج از حساب کاربری");

            System.out.println("انتخاب شما: ");
            int choice=sc.nextInt();

            switch(choice){
                case 1:
                    showprofile();
                    break;
                case 2:
                    addNewProperty();
                    break;
                case 3:
                    searchProperties();
                    break;
                case 4:
                    transaction();
                    break;

                case 5:
                    showContracts(currentUser,manage);
                    break;

                case 6:
                    System.out.println("در حال بیرون اومدن از برنامه...");
                    return;
                default:
                    System.out.println("ارور(ERROR)");
                    break;
            }

        }
    }

    private void showprofile(){
        System.out.println("نام کاربری: " + currentUser.getUserName());
        System.out.println("آیدی شما: " + currentUser.getId());
        System.out.println("بودجه شما: " + currentUser.getBudget());
        System.out.println("===============");

        System.out.println("برای بازگشت enter بزنید!");
        sc.nextLine();
        sc.nextLine();

    }
    private void addNewProperty(){
        addProperty();

        System.out.println("برای بازگشت enter بزنید!");
        sc.nextLine();
        sc.nextLine();
    }


   private void transaction(){
       ArrayList<Property> all= manage.getAllProperties();

       if(all.isEmpty()){
           System.out.println("لیست ملک ها برای خرید: ");
           searchProperties();
           sc.nextLine();
           return;
       }

       System.out.println("~~~~~ املاک جهت خرید و اجاره ~~~~~");
       for (int i = 0; i < all.size(); i++) {
           Property property= all.get(i);
           if(property!=null){
               System.out.println(i+1);
               property.showDetails();
           };
           System.out.println("================");
       }
       System.out.println("شماره ملک موردنظر خود را وارد کنید: ");
       int choice =sc.nextInt();
       sc.nextLine();

       if(choice <= 0 || choice >all.size()){
           System.out.println("ارور(ERROR)");
           return;
       }

       Property propertyChoosed=all.get(choice - 1);

       if (propertyChoosed.getOwnerId().equals(currentUser.getId())) {
           System.out.println("ملک به اسم خودتان است!");
           System.out.println("برای بازگشت enter بزنید!");
           sc.nextLine();
           return;
       }

       System.out.println("1_ خرید ملک");
       System.out.println("2_ اجاره ملک :");
       System.out.println("گزینه موردنظر خود را وارد کنید: ");

       int entekhab= sc.nextInt();
       sc.nextLine();

       if(entekhab==1){
           performPurchase(propertyChoosed);
       }else if(entekhab==2){
           performRent(propertyChoosed);
       }else{
           System.out.println("ارور(ERROR)");
       }
   }


    private void performPurchase(Property propertyChoosed) {
       if(propertyChoosed.getOwnerId().trim().equalsIgnoreCase(currentUser.getId().trim())){
            System.out.println("این ملک برای خودته!");
            return;
       }

            long price=propertyChoosed.price();
            long budget=currentUser.getBudget();
            if (budget<price) {
            System.out.println("بودجه کافی نیست!");
            System.out.println("بودجه فعلی: " + budget);
            System.out.println( "قیمت ملک: " +price);
            return;
            }
            currentUser.setBudget(budget-price);
            propertyChoosed.setOwnerId(currentUser);
            manage.createContract(propertyChoosed, currentUser, "خرید");
            manage.saveAllData();
            System.out.println("ملک با موفقیت خریداری شد!");
            System.out.println("بودجه جدید شما: " + currentUser.getBudget());
    }


       private void performRent(Property propertyChoosed){
               if(propertyChoosed.getMostajer() !=null && ! propertyChoosed.getMostajer().equals("مستاجر ندارد")){
                   System.out.println("این ملک اجاره داده شده!");
                   return;
               }
               if(currentUser.getBudget()>=propertyChoosed.monthlyPrice()){
                   currentUser.setBudget(currentUser.getBudget() - propertyChoosed.price());
                   propertyChoosed.setMostajer(currentUser.getUserName());
                   manage.createContract(propertyChoosed,currentUser,"اجاره");
                   System.out.println("ملک موردنظر با موفقیت اجاره شد!");
               }else{
                   System.out.println("بودجه شما برای اجاره این ملک کافی نمیباشد!");
               }
       }


    private  void addProperty(){
        System.out.println("~~~~~ ثبت ملک جدید ~~~~~");
        System.out.println("1_ آپارتمان");
        System.out.println("2_ خانه ویلایی");
        System.out.println("3_ پنت هاوس");
        System.out.println("نوع ملک مورد نظرتان را انتخاب کنید: ");
        int type =sc.nextInt();
        sc.nextLine();

        int area;
        while(true){
            System.out.println("متراژ ملک: ");
            area=sc.nextInt();
            if(area>0){
                break;
            }
            System.out.println("زیر صفر باشه کنسله!");
        }

        int bedrooms;
        while(true){
            System.out.println("تعداد اتاقخواب: ");
            bedrooms=sc.nextInt();
            if(bedrooms>0){
                break;
            }
            System.out.println("زیر صفر باشه کنسله!");
        }

        int bathroom;
        while(true){
            System.out.println("تعداد حموم و دستشویی: ");
            bathroom=sc.nextInt();
            if(bathroom>0){
                break;
            }
            System.out.println("زیر صفر باشه کنسله!");
        }

        int floor;
        while(true){
            System.out.println("تعداد طبقات ملک خود: ");
            floor=sc.nextInt();
            if(floor>0){
                break;
            }
            System.out.println("زیر صفر باشه کنسله!");
        }

        int region;
        while(true){
            System.out.println("منطقه ملک: ");
            region=sc.nextInt();
            if(region>0){
                break;
            }
            System.out.println("زیر صفر باشه کنسله!");
        }


        String id=UUID.randomUUID().toString();
        String owner=currentUser.getId();
        String mostajer="مستاجری وجود ندارد!";


        if (type==1){
            int unitNumber;
            while(true){
                System.out.println("واحد ملک: ");
                unitNumber=sc.nextInt();
                if(unitNumber>0){
                    break;
                }
                System.out.println("زیر صفر باشه کنسله!");
            }
            int apartmentFloors;
            while(true){
                System.out.println("تعداد کل طبقات آپارتمان: ");
                apartmentFloors=sc.nextInt();
                if(apartmentFloors>0){
                    break;
                }
                System.out.println("زیر صفر باشه کنسله!");
            }

            int unitsInApartment;
            while(true){
                System.out.println("تعداد کل واحد ها: ");
                unitsInApartment=sc.nextInt();
                if(unitsInApartment>0){
                    break;
                }
                System.out.println("زیر صفر باشه کنسله!");
            }
            Apartment ap=new Apartment(id, area, bedrooms, floor, region, owner, mostajer, bathroom, unitNumber,apartmentFloors,unitsInApartment);
            manage.addProperty(ap);

        }else if(type==2){
            int yardArea;
            while(true){
                System.out.println("متراژ حیاط ویلا: ");
                yardArea=sc.nextInt();
                if(yardArea>0){
                    break;
                }
                System.out.println("زیر صفر باشه کنسله!");
            }

            Villa v=new Villa(id, area, bedrooms, floor, region, owner, mostajer, bathroom, yardArea);
            manage.addProperty(v);

        }else if(type==3){
            int terraceArea;
            while(true){
                System.out.println("متراژ تراس: ");
                terraceArea=sc.nextInt();
                if(terraceArea>0){
                    break;
                }
                System.out.println("زیر صفر باشه کنسله!");
            }

            Penthouse p=new Penthouse(id, area, bedrooms, floor, region, owner, mostajer, bathroom, terraceArea);
            manage.addProperty(p);
        }else{
            System.out.println("عدد را از گزینه هام انتخل کنید!");
            return;
        }

        System.out.println("ملک شما با موفقیت ثبت شدش!");
        sc.nextLine();
    }


    private void searchProperties(){
        System.out.println("~~~~~ املاک ثبت شده ~~~~~");

        ArrayList<Property> all=manage.getAllProperties();

        if(all.isEmpty()){
            System.out.println("ملکی هنوز ثبت نشده است!");
            sc.nextLine();
        }else{
            for (Property p : all){
                System.out.println("===============");
                p.showDetails();
            }
        }
        System.out.println("برای خروج از این منو Enter را بزنید!");
        sc.nextLine();
        sc.nextLine();
    }


    public void showContracts(User currentUser, Manage manage) {
        System.out.println("~~~~~ لیست قراردادهای شما ~~~~~");

        ArrayList<Contract> myContracts = manage.getAllContracts(currentUser.getId());

        if (myContracts.isEmpty()) {
            System.out.println("شما هنوز هیچ قرارداد ثبت شده‌ای ندارید.");
        } else {
            int shomarande=1;
            for (Contract c : myContracts) {
                System.out.println(shomarande + " نوع: " + c.getContractType());
                System.out.println("کد قرارداد: " + c.getContractId());
                System.out.println("کد ملک: " + c.getPropertyId());
                System.out.println("مبلغ معامله: " + c);

                if (c.getBuyerId().equals(currentUser.getId())) {
                    System.out.println("نقش شما: خریدار/مستاجر");
                } else {
                    System.out.println("نقش شما: فروشنده/مالک");
                }
                shomarande++;
            }
        }
        System.out.println("===============");
        System.out.println("برای خروج از این منو Enter را بزنید!");
        sc.nextLine();
        sc.nextLine();
    }

}
