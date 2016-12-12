package pizzeria;

import javafx.fxml.FXML;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzen {
    @FXML private ArrayList<Pizza> pizzen = new ArrayList<>();
    @FXML private ArrayList<String> belag = new ArrayList<>();

    public Pizzen() {
    }
    @FXML
    public void readPizzas() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pizzen\\pizzen.ser"));
        while(ois.readObject() != null){
            //Pizza pizza = (Pizza) ois.readObject();
            System.out.println(ois.readObject());
        }
        ois.close();
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

    private void addPizza() throws IOException, ClassNotFoundException {
        String o;
        String p;
        //while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Name Pizza:");
            o = sc.nextLine();
            if (o.equals("exit")){
                writePizza();
                System.exit(0);
            }
            System.out.println("Belag");
            while (true){
                p = sc.nextLine();
                if (p.equals("exit")){
                    break;
                }
                belag.add(p);
            }
            Pizza pizza = new Pizza(o, belag);
            pizzen.add(pizza);
        //}

    }
}
