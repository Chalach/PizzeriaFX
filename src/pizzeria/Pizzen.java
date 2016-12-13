package pizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzen {
    @FXML private ArrayList<Pizza> pizzen = new ArrayList<>();
    @FXML private ArrayList<String> belag = new ArrayList<>();

    public Pizzen() {
    }
    @FXML
    public void readPizzas() throws IOException, ClassNotFoundException {
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("pizzen\\pizzen.ser"));
        while(ois.readObject() != null){
            Pizza pizza = (Pizza) ois.readObject();
            pizzen.add(pizza);
            System.out.println(ois.readObject());
        }
    }

    @FXML
    public void writePizza() throws IOException, ClassNotFoundException{
        addPizza();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pizzen\\pizzen.ser"));
        for (Pizza i : pizzen) {
            oos.writeObject(i);
        }
        oos.close();
    }

    private void message(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void addPizza() throws IOException, ClassNotFoundException {
        String o;
        String p;
        System.out.println("Pter");
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
        writePizza();
    }
}
