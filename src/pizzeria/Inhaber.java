package pizzeria;

import java.util.ArrayList;

public class Inhaber extends Mensch implements Finanzen{
    private String nachname;
    private int alter;
    private static int countMitarbeiter = 0;
    private ArrayList<Mensch> mitarbeiter = new ArrayList<>();


    public Inhaber(String name, String nachname, int alter) {
        super(name);
        this.nachname = nachname;
        this.alter = alter;
    }

    public String getNachname() {
        return nachname;
    }

    public int getAlter() {
        return alter;
    }

    public int getCountMitarbeiter() {
        return countMitarbeiter;
    }

    public void mitarbeiterEinstellen(Mensch mitarbeiter) {
        this.mitarbeiter.add(mitarbeiter);
        countMitarbeiter++;
    }

    public void mitarbeiterFeuern(Mensch mitarbeiter){
        this.mitarbeiter.remove(mitarbeiter);
    }

    public ArrayList<Mensch> getMitarbeiter() {
        return mitarbeiter;
    }

    @Override
    public String toString() {
        return super.getName() + " " + nachname + " " + alter;
    }

    @Override
    public void verwalteFinanzen(Pizzeria pizzeria , double finanzen) {
        if(finanzen <= 0){
            //gesamtKapital = kapital - finanzen;
        }
        else {
            //kapital = kapital + finanzen;
        }
    }
}