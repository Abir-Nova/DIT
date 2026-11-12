package Observer;

import java.util.ArrayList;
import java.util.List;
// An order that customers can follow in real time (the observable)

public class Order implements Observable {

    // Current position of the delivery
    private String position;
    // Destination of the delivery
    private String destination;
    // Minutes left before the delivery arrives
    private int timeBeforeArrival;
    // The observers that follow this order
    private List<Observer> observers = new ArrayList<>();

    // Getter of the position
    public String getPosition() {
        return position;
    }

    // Getter of the destination
    public String getDestination() {
        return destination;
    }

    // Getter of the time before arrival
    public int getTimeBeforeArrival() {
        return timeBeforeArrival;
    }

    // Registers a new observer
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Calls update on every observer, returns false if there is no observer
    @Override
    public boolean notifyObservers() {
        if (observers.isEmpty()) {
            return false;
        }
        for (Observer observer : observers) {
            observer.update(this);
        }
        return true;
    }

    // Sets the position, the destination and the time, then warns the observers automatically
    public void setData(String position, String destination, int timeBeforeArrival) {
        this.position = position;
        this.destination = destination;
        this.timeBeforeArrival = timeBeforeArrival;
        notifyObservers();
    }
}
