package pizzeria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Kunden, welche die pizzeria betreten oder verlassen
public class Kundenstamm {
    private boolean isAlreadyReadIn = false;
    private ArrayList<String> namenListe = new ArrayList<>();
    private ArrayList<Kunde> kundenListe = new ArrayList<>();

    public Kundenstamm() throws IOException {
        getNamenListe();
        for (int i = 0; i < 20; i++) {
            Kunde kunde = new Kunde(namenListe.get(i));
            kundenListe.add(kunde);
        }
    }

    public int getSizeofKundenListe(){
        return kundenListe.size();
    }

    public ArrayList<String> getNamenListe() throws IOException{
        if(!isAlreadyReadIn){
            try (BufferedReader br = new BufferedReader(new FileReader("namen\\namen.txt"))) {
                for (String c; (c = br.readLine()) != null; ) {
                    namenListe.add(c);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Konnte die Datei nicht finden!");
            }
            isAlreadyReadIn = true;
        }
        return namenListe;
    }

    public ArrayList<Kunde> getKundenListe() {
        return kundenListe;
    }

    @Override
    public String toString() {
        return "Kundenstamm{" +
                "isAlreadyReadIn=" + isAlreadyReadIn +
                ", namenListe=" + namenListe +
                ", kundenListe=" + kundenListe +
                '}';
    }
}
