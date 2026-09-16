package user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import dataBase.dataContract;
import dataBase.dataProperty;
import objects.Contract;
import objects.Property.Property;
import objects.User;
import java.io.File;

public class Manage {
    private ArrayList<User> allUsers;
    private ArrayList<Property> allProperties;
    private ArrayList<Contract> allContracts;

    public Manage() {
        this.allUsers = new ArrayList<>();
        this.allProperties = new ArrayList<>(dataProperty.loadProperties());
        this.allContracts = new ArrayList<>(dataContract.loadContract());
    }

   public void addProperty(Property property) {
         if (property != null) {
            allProperties.add(property);
            dataProperty saver = new dataProperty();
            saver.saveProperties(allProperties);
        }
    }

    public ArrayList<Property> getAllProperties() {
        List<Property> newList=dataBase.dataProperty.loadProperties();
        allProperties.clear();
        allProperties.addAll(newList);
        return allProperties;
    }

    public ArrayList<Property> getPropertiesByOwnerId(String ownerId) {
        ArrayList<Property> userProperty = new ArrayList<>();
        for (Property p : allProperties) {
            if (p.getOwnerId().trim().equals(ownerId.trim())){
                userProperty.add(p);
            }
        }
        return userProperty;
    }

    public void createContract(Property property, User buyer, String type) {
        String propertyId = property.getId();
        String buyerId = buyer.getId();
        String sellerId = property.getOwnerId();

        long price = property.price();
        String mostajerId = "مستجری ندارد!";

        Contract contract = new Contract(UUID.randomUUID().toString(), propertyId, buyerId, sellerId, mostajerId, (int) price, type);

        allContracts.add(contract);
        dataContract dc = new dataContract();
        dc.saveContract(allContracts);

        System.out.println("قرارداد با موفقیت ساخته شد!");
        System.out.println("آیدی قرارداد: " + contract.getContractId());
    }

    public ArrayList<Contract> getAllContracts(String userId) {
        ArrayList<Contract> userContracts=new ArrayList<>();
        for(Contract contract:allContracts){
            if(contract.getBuyerId().equals(userId)||contract.getSellerId().equals(userId)){
                userContracts.add(contract);
            }
        }
        return  userContracts;
    }

    public void saveAllData(){
        dataProperty sp=new dataProperty(); // sp=saverpropperty
        sp.saveProperties(allProperties);
        dataBase.dataUser su =new dataBase.dataUser();  // sv =.......
        List<User> latestUser=su.loadUsers(new File("Users.txt"));

        for(User rUser:allUsers){
            for(User fileUser:latestUser){
                if(rUser.getId().equals(fileUser.getId())){
                    fileUser.setBudget((rUser.getBudget()));
                }
            }
        }
        su.saveUsers(latestUser);
    }
}