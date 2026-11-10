// Movable: an INTERFACE = a contract that lists what a "movable" thing can do, without saying how
// Every class that "implements Movable" must provide a body for each of these four methods

public interface Movable {

    // In an interface, methods are public and abstract automatically (no body, just the signature)
    void moveRight();
    void moveLeft();
    void moveForward();
    void moveBack();
}
