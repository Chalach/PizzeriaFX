package pizzeria;

import java.util.ArrayList;

public class Kellner extends Mensch implements Finanzen{
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
    public void verwalteFinanzen(Pizzeria pizzeria , double finanzen) {

    }
}
