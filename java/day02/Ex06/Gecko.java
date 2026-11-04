
public class Gecko {

    private String name;
    private int age;
    // Field INITIALISER: 100 is assigned automatically before any constructor body runs
    private int energy = 100;

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

    public void hello(String other) {
        System.out.println("Hello " + other + ", I'm " + this.name + "!");
    }

    public void hello(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Hello, I'm " + this.name + "!");
        }
    }

    public void hello(Object other) {
    }

    public int getEnergy() {
        return this.energy;
    }

    // The setter is the ONLY place that writes "energy" so it is the ideal guard:
    // whatever value comes in, the stored energy stays between 0 and 100 included
    // Math.min(100, x) caps at 100; Math.max(0, ...) raises anything negative to 0.
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(100, energy));
    }

    // eat: takes a String, is case insensitive, returns nothing
    // equalsIgnoreCase compares Strings ignoring upper/lower case
    // ALWAYS compare Strings with equals/equalsIgnoreCase, never with == (that compares references)
    // Writing the literal first ("meat".equalsIgnoreCase(food)) also avoids a crash when food is null
    public void eat(String food) {
        if ("meat".equalsIgnoreCase(food)) {
            System.out.println("Yummy!");
            setEnergy(this.energy + 10); // the setter clamps at 100
        } 
        else if ("vegetable".equalsIgnoreCase(food)) {
            System.out.println("Erk!");
            setEnergy(this.energy - 10); // the setter clamps at 0
        } 
        else {
            System.out.println("I can't eat this!"); // energy untouched
        }
    }
}
