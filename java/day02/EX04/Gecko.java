public class Gecko {

    private String name;
    // An int attribute is 0 by default, so a Gecko built without age is "Unborn"
    private int age;

    // Constructor 1: no parameter (same rules as before)
    public Gecko() {
        System.out.println("Hello!");
        this.name = "Unknown";
    }

    // Constructor 2: name only (same rules as before)
    public Gecko(String name) {
        System.out.println("Hello " + name + "!");
        this.name = name;
    }

    // Constructor 3: name AND age
    // this(name) calls constructor 2, so the greeting and the name logic are not copied again
    // A call to this(...) MUST be the very first line of the constructor
    public Gecko(String name, int age) {
        this(name);
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    // Getter and setter of age
    // The setter takes the new value and returns nothing
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // status(): displays a sentence that depends on the age using a switch
    // Several "case" lines in a row (without break between them) share the same code:
    // that is how we express "1 or 2" and "between 3 and 10"
    public void status() {
        switch (this.age) {
            case 0:
                System.out.println("Unborn Gecko");
                break;
            case 1:
            case 2:
                System.out.println("Baby Gecko");
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("Adult Gecko");
                break;
            case 11:
            case 12:
            case 13:
                System.out.println("Old Gecko");
                break;
            default: // every other value negative ones included
                System.out.println("Impossible Gecko");
                break;
        }
    }
}
