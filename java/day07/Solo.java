// Generic class that holds one value of any type T

public class Solo<T> {

    // The value held by this object
    private T value;

    // Constructor: stores the given value
    public Solo(T value) {
        this.value = value;
    }

    // Getter: returns the held value
    public T getValue() {
        return value;
    }

    // Setter: replaces the held value
    public void setValue(T value) {
        this.value = value;
    }
}
