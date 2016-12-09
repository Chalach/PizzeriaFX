package pizzeria;

public class Ofen {
    private int anzahlPizzen;
    private String name;
    private int upgrade;

    public Ofen() {
        anzahlPizzen = 1;
        name = "LowOven5000";
        upgrade = 1;
    }

    public int getAnzahlPizzen() {
        return anzahlPizzen;
    }

    public String getName() {
        return name;
    }

    // Der Inhaber oder Pizzaiolo kann den Namen seines Oven's auch umbenennen
    public void setName(String name) {
        this.name = name;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void upgradeOven() {
        if (getUpgrade() + 1 == 2){
            name = "MiddleOven7500";
            anzahlPizzen = 2;
            upgrade = 2;
        }
        else if (getUpgrade() + 1 == 2){
            name = "HighOven9000";
            anzahlPizzen = 3;
            upgrade = 3;
        }
    }
}