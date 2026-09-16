import menu.LoginMenu;
import user.Manage;

public class Main {
    public static void main(String[] args) {
        Manage commonManage=new Manage();
        LoginMenu login = new LoginMenu(commonManage);
        login.display();
    }
}