package pizzeria;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza implements Serializable{
    private String name;
    private ArrayList<String> belag = new ArrayList<>();

    public Pizza(String name, ArrayList<String> belag){
        this.name = name;
        this.belag = belag;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getBelag() {
        return belag;
    }

    public void removeBelag(String belag){
        for (int i = 0; i < belag.length(); i++) {
            if(this.belag.get(i).equals(belag)){
                this.belag.remove(i);
            }
        }
    }

    public void addBelag(String belag){
        this.belag.add(belag);
    }

    @Override
    public String toString() {
        return name + " " + belag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        if (getName() != null ? !getName().equals(pizza.getName()) : pizza.getName() != null) return false;
        return getBelag() != null ? getBelag().equals(pizza.getBelag()) : pizza.getBelag() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBelag() != null ? getBelag().hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pizza(getName(), getBelag());
    }
}
