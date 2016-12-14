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

    public void setAnzahlPizzen(int anzahlPizzen) {
        this.anzahlPizzen = anzahlPizzen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public void upgradeOfen() {
        setUpgrade(getUpgrade() + 1);
        setAnzahlPizzen(getAnzahlPizzen() + 1);
        if (getUpgrade() == 2){
            setName("MiddleOven7500");
        }
        if (getUpgrade() == 3){
            setName("HighOven9000");
        }
    }

    @Override
    public String toString() {
        return "Ofen{" +
                "anzahlPizzen=" + anzahlPizzen +
                ", name='" + name + '\'' +
                ", upgrade=" + upgrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ofen)) return false;

        Ofen ofen = (Ofen) o;

        if (getAnzahlPizzen() != ofen.getAnzahlPizzen()) return false;
        if (getUpgrade() != ofen.getUpgrade()) return false;
        return getName() != null ? getName().equals(ofen.getName()) : ofen.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getAnzahlPizzen();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getUpgrade();
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Ofen();
    }
}