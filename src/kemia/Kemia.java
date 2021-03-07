package kemia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Elem {

    private String ev, nev, vegyjel;
    private int rendszam;
    private String felfedezo;

    public Elem(String ev, String nev, String vegyjel, int rendszam, String felfedezo) {
        this.ev = ev;
        this.nev = nev;
        this.vegyjel = vegyjel;
        this.rendszam = rendszam;
        this.felfedezo = felfedezo;
    }

    public String getEv() {
        return ev;
    }

    public String getNev() {
        return nev;
    }

    public String getVegyjel() {
        return vegyjel;
    }

    public int getRendszam() {
        return rendszam;
    }

    public String getFelfedezo() {
        return felfedezo;
    }

    @Override
    public String toString() {
        return "ev=" + ev + ", nev=" + nev + ", vegyjel=" + vegyjel + ", rendszam=" + rendszam + ", felfedezo=" + felfedezo;
    }

}

public class Kemia {

    private final String file = "felfedezesek.csv";
    private ArrayList<Elem> elemek;

    public Kemia() {
        this.elemek = new ArrayList<>();
        beolvas();
    }

    private void beolvas() {
        try {
            List<String> sorok = Files.readAllLines(Paths.get(file));
            int i = 1;
            while (i < sorok.size()) {
                String[] s = sorok.get(i).split(";");
                for (int j = 0; j < s.length; j++) {
                    String ev = s[0];
                    String név = s[1];
                    String jel = s[2];
                    int rendszam = Integer.parseInt(s[3]);
                    String felfedezok = s[4];
                    elemek.add(new Elem(ev, név, jel, rendszam, felfedezok));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void kiir(int feladatszam, String szoveg) {
        System.out.print("\n" + feladatszam + ".feladat: " + szoveg);
    }

    public void feladat3() {
        kiir(3, "Elemek száma: " + elemek.size());
    }

    public void feladat4() {
        int db = 0;
        for (Elem elem : elemek) {
            if (elem.getEv().toLowerCase().equals("ókor")) {
                db++;
            }
        }
        kiir(4, "Felfedezések száma az ókorban: " + db);
    }
     public void feladat5() {
        Scanner sc = new Scanner(System.in);
        String vegyjel = "";
        boolean karakter = false;
        do {
            kiir(5, "Kérek egy vegyjelet:");
            vegyjel = sc.nextLine();
            //System.out.println(vegyjel.length());
            if (vegyjel.length() <= 2) {
                karakter = true;
//                if (vegyjel.contains("[A-Z]") && vegyjel.contains("[a-z]")) {
//                   
//                }else{
//                karakter=false;
//                }
            } else {
                karakter = false;
            }
        } while (!karakter);
        feladat6(vegyjel);
    }
       public void feladat6(String vegyjel) {
        int N = elemek.size();
        int i = 0;
        while (i < N && !(elemek.get(i).getVegyjel().toLowerCase().equals(vegyjel.toLowerCase()))) {
            i++;
        }
        System.out.println(i + "," + N);

        if (i < N) {
            kiir(6, elemek.get(i).toString());

        } else {
            kiir(6, "Keresés:");
            System.out.println("\t Nincs ilyen elem az adatforrásban!");
        }
    }

    public void feladat7() {
        int maxEvKül = 0;
        for (int i = 0; i < elemek.size() - 1; i++) {
            if (!(elemek.get(i).getEv().toLowerCase().equals("ókor") || elemek.get(i + 1).getEv().toLowerCase().equals("ókor"))) {
                int elsoEv = Integer.parseInt(elemek.get(i).getEv());
                int masikEv = Integer.parseInt(elemek.get(i + 1).getEv());
                int kül = masikEv - elsoEv;
                if (maxEvKül < kül) {
                    maxEvKül = kül;
                }
            }
        }
        String szov = maxEvKül + " év volt a leghoszabb időszak két elem felfedezése között.";
        kiir(7, szov);
    }
     public void feladat8() {
        System.out.println("\n 8.feladat: ");
        HashMap<String, Integer> statisztika= new HashMap<>();
        for (Elem elem : elemek) {
            String kulcs = elem.getEv();
            if (!statisztika.containsKey(kulcs)) {
                statisztika.put(kulcs,1);
            }else{
            int ertek=statisztika.get(kulcs);
            statisztika.put(kulcs, ++ertek);
            }
        }
        for (Map.Entry<String, Integer> entry : statisztika.entrySet()) {
            String key = entry.getKey();
            Integer value=entry.getValue();
            System.out.printf("\t%s: %d db\n", key, value);
        }
        
        
        
    }

    public static void main(String[] args) {
        Kemia kemia = new Kemia();
        kemia.feladat3();
        kemia.feladat4();
        kemia.feladat5();

        kemia.feladat7();
        kemia.feladat8();

        System.out.println("");
    }
}
