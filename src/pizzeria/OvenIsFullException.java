package pizzeria;

public class OvenIsFullException extends Exception{

    public OvenIsFullException(String message) {
        super(message);
    }
}
