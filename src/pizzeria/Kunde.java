package pizzeria;

public class Kunde extends Mensch implements Finanzen{
    private double geld;
    private int id = 0;

    public Kunde(String name) {
        super(name);
        geld = (Math.random() + 1) * 100 - 100;
        id++;
    }

    public int getId() {
        return id;
    }

    public double getGeld() {
        return geld;
    }

    @Override
    public String toString() {
        return "Kunde: " + super.getName() + "\n" + "Geld = " + geld + "\n";
    }

    @Override
    public void verwalteFinanzen(Pizzeria pizzeria , double finanzen) {

    }
}
