package dataBase;

import objects.Contract;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class dataContract {
    private static String FILE_NAME = "Contract.txt";

    public String setContractLine(Contract contract) {
        return contract.getContractId()+ "," + contract.getPropertyId()+ ","+ contract.getBuyerId()+ ","+ contract.getSellerId()+ "," + contract.getMostajerId()+"," + contract.getAmount()+ ","+contract.getContractType();
    }

    public void saveContract(List<Contract> contracts) {
        try(PrintWriter writer=new PrintWriter(new FileWriter(FILE_NAME))) {
            for(Contract c: contracts){
                writer.println(setContractLine(c));
            }
        }catch (IOException e){
            System.out.println("ارور در سیو کردن قرارداد: "+ e.getMessage());
        }
    }

    public static List<Contract> loadContract(){
        List<Contract> list=new ArrayList<>();
        File file=new File(FILE_NAME);
        if (!file.exists())
            return list;

        try (BufferedReader br=new BufferedReader(new FileReader(file))) {
            String line;
            while ((line =br.readLine())!= null) {
                if (line.trim().isEmpty()) continue;
                String[] f=line.split(",");
                Contract contract =new Contract(f[0],f[1],f[2],f[3],f[4],Integer.parseInt(f[5]),f[6]);
                list.add(contract);
            }
        } catch(Exception exception) {
             exception.printStackTrace();
        }
        return list;
    }
}