public class Gecko {

    // ATTRIBUTE (also called field): data stored INSIDE each Gecko object
    // Each Gecko has its own copy of "name". Public for now (Ex03 will fix that)
    public String name;

    // Constructor WITHOUT parameter (from Ex01)
    // The statement asks for "Hello!" here, and the name must become "Unknown"
    public Gecko() {
        System.out.println("Hello!");
        this.name = "Unknown";
    }

    // Constructor WITH a parameter. 2 constructors with the same name but different
    // parameters = OVERLOADING. Java picks the right one from the arguments of "new"
    //   new Gecko()          -> first constructor
    //   new Gecko("Arthur")  -> this one
    public Gecko(String name) {
        System.out.println("Hello " + name + "!");
        // "this" = the object being built. this.name is the ATTRIBUTE,
        // plain "name" is the PARAMETER (they have the same name, "this" removes the doubt)
        this.name = name;
    }
}
