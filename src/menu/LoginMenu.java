package menu;

import objects.User;
import user.Enrollment;
import user.Manage;
import java.util.Scanner;


public class LoginMenu{
    private Enrollment enrollment= new Enrollment();
    private Scanner sc =new Scanner(System.in);

    private Manage manage;
    public LoginMenu(Manage manage){
        this.manage=manage;
    }


    public void display(){
        System.out.println("~~~~~ به سامانه املاکی خوش آمدید ~~~~~");
        System.out.println("1_ SignUp");
        System.out.println("2_ SignIn");
        System.out.println("3_ خروج از برنامه");
        System.out.println("انتخاب کنید: ");
        int choice=sc.nextInt();

        User currentUser= null;

        switch(choice){
            case 1:
                currentUser=signUp();
                break;
            case 2:
                currentUser=signIn();
                break;
            case 3:
                System.out.println("تا درودی دیگر بدرود!");
                return;
            default:
                System.out.println("ارور(ERROR)");
        }
        if(currentUser!=null){
            System.out.println(" خوش اومدی"+currentUser.getUserName());

            MainMenu mainPanel = new MainMenu(currentUser, manage);
            mainPanel.display();
        }
    }



    private User signUp(){
        sc.nextLine();
        System.out.println("~~~~~ فرم ثبت نام ~~~~~");
        System.out.println("نام کاربری: ");
        String user=sc.nextLine();
        System.out.println("رمز عبور: ");
        String pass= sc.nextLine();
        System.out.println("تایید رمز: ");
        String confirm=sc.nextLine();
        System.out.println("بودجه اولیه شما: ");
        long budget=sc.nextLong();

        String hashedPass=hashMe(pass);
        String hashedConfirm=hashMe(confirm);

        String xoxo=enrollment.signUp(user, hashedPass, hashedConfirm, budget);
        System.out.println(xoxo);

        if(xoxo.contains("ثبت نام به درستی انجام شدش")){
            return enrollment.login(user , hashedPass);
        }
        return null;
    }
    private User signIn(){
        sc.nextLine();
        System.out.println("~~~~~ منو ورودی ~~~~~");
        System.out.println("نام کاربری: ");
        String user =sc.nextLine();
        System.out.println("رمز عبور: ");
        String pass = sc.nextLine();

        String hashedPass=hashMe(pass);

        User logedUser=enrollment.login(user, hashedPass);

        if(logedUser==null){
            System.out.println("یا نام کاربری یا رمز عبور اشتباه است.دوباره تلاش کنید!");
        }
        return logedUser;
    }

    private String hashMe(String password){
        try{
            java.security.MessageDigest digest=java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash=digest.digest(password.getBytes());
            return  java.util.Base64.getEncoder().encodeToString(hash);
        }catch (Exception exception){
            return  password;
        }
    }
}
