package objects;

import java.util.ArrayList;
import java.util.List;


    public class User {
        private String id;
        private String userName;
        private String password;
        private long budget;

        private List<String> ownedHouseIds;
        private List<String> rentedHouseIds;

        public User(String id, String userName, String password, long budget) {
            this.id = id;
            this.userName=userName;
            this.password=password;
            this.budget=budget;
            this.rentedHouseIds=new ArrayList<>();
            this.ownedHouseIds=new ArrayList<>();
        }

        public String getUserName() {
            return userName;
        }
        public String getPassWord() {
            return password;
        }
        public String getId() {
            return id;
        }
        public long getBudget() {
            return budget;
        }
        public void setBudget(long budget) {
            this.budget = budget;
        }
    }
