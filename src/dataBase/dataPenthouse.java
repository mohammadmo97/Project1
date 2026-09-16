package dataBase;

import objects.Property.Penthouse;


public class dataPenthouse extends dataProperty {

    public String setPenthouseInLine(Penthouse p) {
        return setPropertyLine(p) + "," + p.getTerrasArea();
    }

    public static Penthouse getPenthouseInLine(String line) {
        String[] f = line.split(",");
        return new Penthouse(f[1], Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), f[6], f[7], Integer.parseInt(f[8]), Integer.parseInt(f[10]));
    }
}
