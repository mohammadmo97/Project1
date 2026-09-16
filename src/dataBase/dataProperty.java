package dataBase;

import objects.Property.*;
import java.io.*;
import java.util.*;


public class dataProperty {
    private static final String FILE_NAME = "Properties.txt";


    public String setPropertyLine(Property property) {
        String type = "";
        if(property instanceof Apartment) type ="Apartment";
        else if(property instanceof Villa) type ="Villa";
        else if(property instanceof Penthouse) type= "Penthouse";

        return type+"," + property.getId()+","+ property.getArea()+ ","+ property.getBedrooms() +"," + property.getFloors()+"," + property.getRegion()+","+property.getOwnerId() +"," + property.getMostajer() +","+property.getBathrooms() + ","+property.price();
    }

    public void saveProperties(List<Property> properties) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Property p : properties) {
                String line = "";
                if (p instanceof Apartment) line= new dataApartment().setApartmentInLine((Apartment) p);
                else if (p instanceof Villa) line= new dataVilla().setVillaInLine((Villa) p);
                else if (p instanceof Penthouse) line= new dataPenthouse().setPenthouseInLine((Penthouse) p);
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Property> loadProperties() {
        List<Property> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;
        try (BufferedReader br=new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) !=null) {
                line = line.trim();
                if(line.isEmpty()) continue;

                String[] fields = line.split(",");
                String type = fields[0];

                if(type.equalsIgnoreCase("Apartment")){
                    list.add(dataApartment.getApartmentInLine(line));
                }else if(type.equalsIgnoreCase("Villa")){
                    list.add(dataVilla.getVillaInLine(line));
                }else if(type.equalsIgnoreCase("Penthouse")){
                    list.add(dataPenthouse.getPenthouseInLine(line));
                }
            }
        }catch (Exception exception){
            System.out.println("Error: " + exception.getMessage());
        }
        return list;
    }
}