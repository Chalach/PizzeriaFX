package pizzeria;

import javafx.fxml.FXML;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

// Hilfs-Test Klasse
// -> wird evtl. gelöscht

public class Pizzen {
    @FXML private ArrayList<Pizza> pizzen = new ArrayList<>();
    @FXML private ArrayList<String> belag = new ArrayList<>();

    public Pizzen() {
    }

    @FXML
    public void readPizzas() throws Exception {
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("pizzen\\pizzen.ser"));
        try{
            while(ois.readObject() != null){
                Pizza pizza = (Pizza) ois.readObject();
                pizzen.add(pizza);
                System.out.println(ois.readObject());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void writePizza() throws Exception {
        //addPizza();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pizzen\\pizzen.ser"));
        for (Pizza i : pizzen) {
            oos.writeObject(i);
        }
        ArrayList<String> i = new ArrayList<>();
        i.add("peter");
        i.add("petwerer");
        i.add("petwewqer");
        Pizza pizza = new Pizza("Marina", i);
        oos.writeObject(pizza);
        System.out.println(pizza);
        oos.close();
    }

    private void addPizza() throws IOException, ClassNotFoundException {
        String o;
        String p;
        boolean test = true;

        o = JOptionPane.showInputDialog("Name Pizza:");
        System.out.println(o);
        System.out.println("Belag");
        while (test){
            p = JOptionPane.showInputDialog("Belager Pizza:");
            if (p.equals("exit")){
                test = false;
            }
            else{
                belag.add(p);
            }
        }
        Pizza pizza = new Pizza(o, belag);
        pizzen.add(pizza);
        belag.clear();
    }
}
