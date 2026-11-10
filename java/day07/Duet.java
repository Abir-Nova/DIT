// Class with two static generic methods to compare two values

public class Duet {

    // Returns the smallest of the two values, using compareTo
    // "? super T" lets T be compared with itself or with one of its parents
    
    public static <T extends Comparable<? super T>> T min(T a, T b) {
        // A negative or zero result means a is smaller than (or equal to) b
        return a.compareTo(b) <= 0 ? a : b;
    }

    // Returns the highest of the two values, using compareTo
    public static <T extends Comparable<? super T>> T max(T a, T b) {
        // A positive or zero result means a is higher than (or equal to) b
        return a.compareTo(b) >= 0 ? a : b;
    }
}
