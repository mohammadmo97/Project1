package dataBase;

import objects.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class dataUser {

    private File fileName = new File("Users.txt");
    public dataUser() {}


    public static  String setUserLine(User user){
        return user.getId() + " , " + user.getUserName() + " , " + user.getPassWord() +" , "+user.getBudget();
    }

    public static  User getUserLine(String UserLine){
        String[] parts =UserLine.split(" , ");
        long booje = Long.parseLong(parts[3]);
        return new User(parts[0],parts[1],parts[2],booje);
    }


    public void saveUsers(List<User> users) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for(User user : users){
                writer.println(setUserLine(user));
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public List<User> loadUsers(File fileName) {
        List<User> object = new ArrayList<>();
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = readFile.readLine()) != null) {
                if (!(line.isEmpty())) {
                    User user = getUserLine(line);
                    object.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("error!IOException");
        }
        return object;
    }

}
