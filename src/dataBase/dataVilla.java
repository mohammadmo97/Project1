package dataBase;

import objects.Property.Villa;


public class dataVilla extends dataProperty {

    public String setVillaInLine(Villa v) {
        return setPropertyLine(v) + "," + v.getYardArea() + "," + v.getFloors();
    }

    public static Villa getVillaInLine(String line) {
        String[] f = line.split(",");
        return new Villa(f[1], Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), f[6], f[7], Integer.parseInt(f[8]), Integer.parseInt(f[10]));
    }
}