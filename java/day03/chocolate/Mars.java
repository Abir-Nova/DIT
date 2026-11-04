// PACKAGE: the first line declares which package the class belongs to
// Rule: the folder path must match the package name -> chocolate/Mars.java -> package chocolate
// The body is the Ex01 Mars class, unchanged Its FULL name is now chocolate.Mars

package chocolate;

// "public" is: now essential: a class without it is visible only INSIDE its own package
// so other packages (and the default package of TestMain) could not use it

public class Mars{
    private static int nextId = 0;

    private final int id;

    // The constructor must be public too for the same reason
    
    public Mars() {
        this.id = nextId++;
    }

    public int getId() {
        return this.id;
    }
}





