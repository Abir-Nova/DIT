package Observer;
// Interface of an object that can be followed by observers

public interface Observable {

    // Registers an observer
    void addObserver(Observer observer);

    // Warns the observers, returns false if there is none
    boolean notifyObservers();
}
