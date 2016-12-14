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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kunde)) return false;

        Kunde kunde = (Kunde) o;

        if (Double.compare(kunde.getGeld(), getGeld()) != 0) return false;
        return getId() == kunde.getId();

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getGeld());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getId();
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Kunde(getName());
    }
}
