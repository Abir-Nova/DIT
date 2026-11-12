package Factory;
// Exception thrown when the factory cannot create the requested toy
public class NoSuchToyException extends RuntimeException {
    // Constructor: keeps the message given by the caller
    public NoSuchToyException(String message) {
        super(message);
    }
}
