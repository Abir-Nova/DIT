package Observer;

import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) {
        Order order = new Order();
        Customer customer = new Customer();
        order.addObserver(customer);
        order.setData("123.5326, 237.9277", "6W 40th Street, New York", 10);
        order.notifyObservers();
    }

}
