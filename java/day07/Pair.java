// Generic class that holds two elements, each with its own type (T and V)

public class Pair<T, V> {

    // First element of the pair
    private final T first;
    // Second element of the pair
    private final V second;

    // Constructor: takes the first and the second element
    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    // Getter for the first element (no setter as requested)
    public T getFirst() {
        return first;
    }

    // Getter for the second element (no setter as requested)
    public V getSecond() {
        return second;
    }

    // Prints the pair in the format: first: [first], second: [second].
    public void display() {
        System.out.println("first: " + first + ", second: " + second + ".");
    }
}
