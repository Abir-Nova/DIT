public class Gecko {

    // The attribute is now PRIVATE: only code written INSIDE the Gecko class can touch it
    // This is ENCAPSULATION: the object protects its own data
    // and outside code must go through methods (here: getName)
    private String name;

    public Gecko() {
        System.out.println("Hello!");
        this.name = "Unknown";
    }

    public Gecko(String name) {
        System.out.println("Hello " + name + "!");
        this.name = name;
    }

    // GETTER: a public method whose only job is to give access to a private attribute
    // Convention: "get" + attribute name with a capital letter
    // Return type String: the method gives back the name (it does not display it)
    public String getName() {
        return this.name;
    }
}
