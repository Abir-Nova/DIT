package Observer;
// Interface of an object that follows an observable

public interface Observer {

    // Called by the observable when something changes
    void update(Observable observable);
}
