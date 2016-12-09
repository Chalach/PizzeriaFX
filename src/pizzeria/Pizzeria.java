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

    public void addKapital(double kapital){
        if(kapital <= 0){
            // Hinweis: Es wird + (-kapital) gerechnet
            double kapital2 = this.kapital;
            if (kapital2 + kapital > 0){
                this.kapital += kapital;
            }
        }
        else {
            this.kapital += kapital;
        }
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
}
