package Observer;
// A customer who follows an order (the observer)

public class Customer implements Observer {

    // Called when the observed object changes
    @Override
    public void update(Observable observable) {
        // Only an Order has delivery information to display
        if (observable instanceof Order) {
            Order order = (Order) observable;
            System.out.println("Position (" + order.getPosition() + "), " + order.getTimeBeforeArrival() + " minutes before arrival at " + order.getDestination() + ".");
        }
    }
}
