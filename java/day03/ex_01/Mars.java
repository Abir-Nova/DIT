public class Mars{
    private static int nextId = 0;

    // Static attribute belongs to the class not to an object 
    // There is 1 copy of nextId, shared by every Mars 
    // It is the "ticket dispenser": it remembers which id to give next

    private final int id;

    // Normal (instance) attribute: every Mars object has its own id
    // "final" = can be assigned once (in the constructor) and never changed afterwards
    // which fits a getter but NO setter
    
    public Mars() {
        this.id = nextId++;
    }

    // Take the current ticket then move the dispenser forward
    // nextId++ returns the OLD value and then increments it:
    // 1st Mars -> id = 0, nextId becomes 1
    // 2nd Mars -> id = 1, nextId becomes 2 ...


    public int getId() {
        return this.id;
    }
}




