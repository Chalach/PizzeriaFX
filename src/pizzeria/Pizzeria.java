package pizzeria;

public class Pizzeria {
    private String name;
    private Inhaber inhaber;
    private double kapital;
    private int anzahlPlaezte;

    public Pizzeria(String name, Inhaber inhaber) {
        this.name = name;
        this.inhaber = inhaber;
        this.kapital = 7000.0;
        anzahlPlaezte = 15;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mensch getInhaber() {
        return inhaber;
    }

    public void setInhaber(Inhaber inhaber) {
        this.inhaber = inhaber;
    }

    public double getKapital() {
        return kapital;
    }

    // Hinweis: Es wird + (-kapital); bzw. + kapital gerechnet;
    public void addKapital(double kapital){
        this.kapital += kapital;
    }

    public int getAnzahlPlaezte() {
        return anzahlPlaezte;
    }

    public void addAnzahlPlaezte() {
        anzahlPlaezte += 15;
    }

    @Override
    public String toString() {
        return "Restaurant: " + name + "\n" +
                "Inhaber: " + inhaber.toString() + "\n" +
                "Kapital: " + kapital + "\n" +
                "Anzahl Plätze: " + anzahlPlaezte + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizzeria)) return false;

        Pizzeria pizzeria = (Pizzeria) o;

        if (Double.compare(pizzeria.getKapital(), getKapital()) != 0) return false;
        if (getAnzahlPlaezte() != pizzeria.getAnzahlPlaezte()) return false;
        if (getName() != null ? !getName().equals(pizzeria.getName()) : pizzeria.getName() != null) return false;
        return getInhaber() != null ? getInhaber().equals(pizzeria.getInhaber()) : pizzeria.getInhaber() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getInhaber() != null ? getInhaber().hashCode() : 0);
        temp = Double.doubleToLongBits(getKapital());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getAnzahlPlaezte();
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pizzeria(getName(), (Inhaber) getInhaber());
    }
}
