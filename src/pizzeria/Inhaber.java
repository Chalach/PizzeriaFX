package pizzeria;

import java.util.ArrayList;

public class Inhaber extends Mensch{
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inhaber)) return false;

        Inhaber inhaber = (Inhaber) o;

        if (getAlter() != inhaber.getAlter()) return false;
        if (getNachname() != null ? !getNachname().equals(inhaber.getNachname()) : inhaber.getNachname() != null)
            return false;
        return getMitarbeiter() != null ? getMitarbeiter().equals(inhaber.getMitarbeiter()) : inhaber.getMitarbeiter() == null;

    }

    @Override
    public int hashCode() {
        int result = getNachname() != null ? getNachname().hashCode() : 0;
        result = 31 * result + getAlter();
        result = 31 * result + (getMitarbeiter() != null ? getMitarbeiter().hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Inhaber(getName(), getNachname(), getAlter());
    }
}