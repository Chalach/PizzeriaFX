package pizzeria;

public class Pizza {

    public enum Pizzas{
        GRUNDPIZZA ("Mozzarella", "Origano"),
        MARGERITHA ("Mozzarella", "Origano");


        Pizzas(String mozzarella, String origano) {
        }
    }

    public static void main(String[] args) {
        Pizzas pizza = Pizzas.MARGERITHA;
        Pizzas pizza2 = Pizzas.GRUNDPIZZA;
        System.out.println(pizza);
        System.out.println(pizza2);
    }
}
