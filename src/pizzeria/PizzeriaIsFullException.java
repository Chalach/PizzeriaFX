package pizzeria;

public class PizzeriaIsFullException extends Exception{

    public PizzeriaIsFullException(String message) {
        super(message);
    }
}
