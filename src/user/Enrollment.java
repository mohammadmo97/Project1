package user;

import java.io.File;
import java.util.List;
import java.util.UUID;
import dataBase.DatabaseHandler;
import dataBase.dataUser;
import objects.User;


public class Enrollment{

    dataUser datauser = new dataUser();
    File userFile=new File("Users.txt");

    public Enrollment(){}

    public String signUp(String userName, String password, String confirmPassword, long budget){
        List<User> users=datauser.loadUsers(userFile);
        if(!password.equals(confirmPassword)){
            return "رمز ها یکسان نمیباشند لطفا دوباره سعی کنید!";
        }
        for(User u : users){
            if(u.getUserName().equals(userName)){
                return "یوزرنیم قبلا انتخاب شده.لطفا یک یوزرنیم دیگه انتخاب کنید!" ;
            }
        }

        String uniqueId=UUID.randomUUID().toString();

        User newUser=new User(uniqueId, userName, password, budget);
        users.add(newUser);
        datauser.saveUsers(users);


        return"ثبت نام به درستی انجام شدش...شناسه شما: " + uniqueId ;
        }

    public User login(String userName, String password){
        List<User> users=datauser.loadUsers(userFile);
        for (User user : users) {
            if(user.getUserName().equals(userName)  &&  user.getPassWord().equals(password)){
                return user;
            }
        }
        return null;
    }
}
