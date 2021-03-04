package kemia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
}

public class Kemia {

    private final String file = "felfedezesek.csv";
    private ArrayList<Elem> tomb;

    public Kemia() {
        this.tomb = new ArrayList<>();
    }

    private void beolvas() {
        try {
            List<String> sorok = Files.readAllLines(Paths.get(file));
            int i = 0;
            while (i < sorok.size()) {
                String[] s = sorok.get(i).split(";");
                for (int j = 0; j < s.length; j++) {
                    String ev = s[0];
                    String név = s[1];
                    String jel = s[2];
                    int rendszam = Integer.parseInt(s[3]);
                    String felfedezok = s[4];
                    tomb.add(new Elem(ev, név, jel, rendszam, felfedezok));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
