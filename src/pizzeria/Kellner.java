package pizzeria;

import java.util.ArrayList;

public class Kellner extends Mensch{
    private ArrayList<Pizza> pinnWand = new ArrayList<>();
    private double geldTasche;

    public Kellner(String name) {
        super(name);
        this.geldTasche = 125.0;
    }

    public double getGeldTasche() {
        return geldTasche;
    }

    public ArrayList<Pizza> getPinnWand() {
        return pinnWand;
    }

    public void pizzaHinzufuegen(Pizza pizza) {
        pinnWand.add(pizza);
    }

    public void pizzaLoeschen(Pizza pizza){
        pinnWand.remove(pizza);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kellner)) return false;

        Kellner kellner = (Kellner) o;

        if (Double.compare(kellner.getGeldTasche(), getGeldTasche()) != 0) return false;
        return getPinnWand() != null ? getPinnWand().equals(kellner.getPinnWand()) : kellner.getPinnWand() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getPinnWand() != null ? getPinnWand().hashCode() : 0;
        temp = Double.doubleToLongBits(getGeldTasche());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Kellner(getName());
    }
}
