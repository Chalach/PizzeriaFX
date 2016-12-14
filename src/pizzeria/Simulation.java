package pizzeria;

import java.io.IOException;
import java.util.ArrayList;

public class Simulation extends Thread {
    private Pizzeria pizzeria;
    private ArrayList<Pizza> pizza;
    private ArrayList<Mensch> mitarbeiter;
    private Kundenstamm kundenstamm;

    public Simulation(Pizzeria pizzeria, ArrayList<Pizza> pizza, ArrayList<Mensch> mitarbeiter) throws IOException {
        this.pizzeria = pizzeria;
        this.pizza = pizza;
        this.mitarbeiter = mitarbeiter;
        kundenstamm = new Kundenstamm();
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public ArrayList<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(ArrayList<Pizza> pizza) {
        this.pizza = pizza;
    }

    public ArrayList<Mensch> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(ArrayList<Mensch> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public void startSimulation(){
        for (int i = 0; i < kundenstamm.getSizeofKundenListe(); i++) {
            run();
        }
    }

    // Eigentlich sollte hier das Hauptprogramm folgen:
    //  Kunden kommen und gehen ins Restaurant, bestellen, bezahlen,...
    //  Inhaber kann den Ofen upgraden, Pizzeria erweitern,...

    // Aus Eigenverschuldung, und dem daraus resultierende Zeitmangel, wurde dies aber nicht fertiggestellt
    public void run() {
        System.out.println("Kunde betretet die Pizzeria: ");
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}