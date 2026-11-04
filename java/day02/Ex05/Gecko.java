
public class Gecko {

    private String name;
    private int age;

    public Gecko() {
        System.out.println("Hello!");
        this.name = "Unknown";
    }

    public Gecko(String name) {
        System.out.println("Hello " + name + "!");
        this.name = name;
    }

    public Gecko(String name, int age) {
        this(name);
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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
            default:
                System.out.println("Impossible Gecko");
                break;
        }
    }

    // Methods overloading: 3 methods called "hello" told apart by their parameter type
    // Java chooses at COMPILE time from the type of the argument we pass
    // (Java has no "optional parameter" syntax: overloading is the way to get the same effect)
    // hello("Titi") -> "Hello Titi, I'm mimi!"
    public void hello(String other) {
        System.out.println("Hello " + other + ", I'm " + this.name + "!");
    }

    // hello(2) -> "Hello, I'm mimi!" displayed twice
    public void hello(int times) {
        for (int i = 0; i < times; i++) { // a number <= 0 gives zero turns: nothing displayed
            System.out.println("Hello, I'm " + this.name + "!");
        }
    }

    // In all other cases, the method does nothing:
    // any other type (double, boolean, an object...) lands here because everything is an Object
    public void hello(Object other) {
        // intentionally empty
    }
}
