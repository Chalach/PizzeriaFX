package pizzeria;

public abstract class Mensch {
    private String name;

    public Mensch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensch)) return false;

        Mensch mensch = (Mensch) o;

        return getName() != null ? getName().equals(mensch.getName()) : mensch.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}