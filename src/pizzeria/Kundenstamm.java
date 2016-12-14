package pizzeria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Kundenstamm {
    private ArrayList<String> namenListe = new ArrayList<>();
    private ArrayList<Kunde> kundenListe = new ArrayList<>();

    public Kundenstamm() throws IOException {
        generateNamenListe();
        generateKundenListe();
    }

    public ArrayList<String> getNamenListe(){
        return namenListe;
    }

    public ArrayList<Kunde> getKundenListe() {
        return kundenListe;
    }

    public int getSizeofKundenListe(){
        return kundenListe.size();
    }

    public void generateNamenListe() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("namen\\namen.txt"))) {
            for (String c; (c = br.readLine()) != null; ) {
                namenListe.add(c);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Konnte die Datei nicht finden!");
        }
    }

    public void generateKundenListe(){
        Random randomGenerator = new Random();
        int kunden = randomGenerator.nextInt(15);
        for (int i = 0; i < kunden; i++) {
            Kunde kunde = new Kunde(namenListe.get(i));
            kundenListe.add(kunde);
        }
    }

    @Override
    public String toString() {
        return "Kundenstamm{" +
                "isAlreadyReadIn=" +
                ", namenListe=" + namenListe +
                ", kundenListe=" + kundenListe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kundenstamm)) return false;

        Kundenstamm that = (Kundenstamm) o;

        if (getNamenListe() != null ? !getNamenListe().equals(that.getNamenListe()) : that.getNamenListe() != null)
            return false;
        return getKundenListe() != null ? getKundenListe().equals(that.getKundenListe()) : that.getKundenListe() == null;

    }

    @Override
    public int hashCode() {
        int result = getNamenListe() != null ? getNamenListe().hashCode() : 0;
        result = 31 * result + (getKundenListe() != null ? getKundenListe().hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return new Kundenstamm();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}