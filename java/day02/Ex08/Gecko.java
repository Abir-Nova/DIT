public class Gecko {

    private String name;
    private int age;
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
            case 0: System.out.println("Unborn Gecko"); break;
            case 1: case 2: System.out.println("Baby Gecko"); break;
            case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                System.out.println("Adult Gecko"); break;
            case 11: case 12: case 13: System.out.println("Old Gecko"); break;
            default: System.out.println("Impossible Gecko"); break;
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

    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(100, energy));
    }

    public void eat(String food) {
        if ("meat".equalsIgnoreCase(food)) {
            System.out.println("Yummy!");
            setEnergy(this.energy + 10); 
        } 
        else if ("vegetable".equalsIgnoreCase(food)) {
            System.out.println("Erk!");
            setEnergy(this.energy - 10); 
        } 
        else {
            System.out.println("I can't eat this!"); 
        }
    }

    public void work() {
        if (this.energy >= 25) {
            System.out.println("I'm working T.T");
            setEnergy(this.energy - 9);
        } 
        else {
            System.out.println("Heyyy I'm too sleepy, better take a nap!");
            setEnergy(this.energy + 50);
        }
    }

    // Ex08

    // Helper (we are told we may add methods): one place that displays a line
    // Calling other.say(...) makes the OTHER gecko "speak"
    private void say(String message) {
        System.out.println(message);
    }

    // Helper: enough energy to go out means at least 30 (the outing costs 30)
    private boolean canGoOut() {
        return this.energy >= 30;
    }

    // fraternize is OVERLOADED: 1 version per kind of friend
    // Java picks the right one from the type of the argument (Gecko or Snake)

    public void fraternize(Gecko other) {
        boolean iCan = this.canGoOut();
        boolean otherCan = other.canGoOut();

        // Inside the Gecko class we may call private members of ANOTHER Gecko ( other.canGoOut() ):
        // private means "private to the class", not "private to the object"

        if (iCan && otherCan) {
            // The current gecko speaks first, then the other one
            this.say("I'm going to drink with " + other.getName() + "!");
            other.say("I'm going to drink with " + this.getName() + "!");

            this.setEnergy(this.energy - 30);
            other.setEnergy(other.energy - 30);
        } 
        else if (!iCan && !otherCan) {
            this.say("Not today!");
            other.say("Not today!");
        } 
        else if (!iCan) {
            // I am the tired one: I apologise first then the other answers
            this.say("Sorry " + other.getName() + ", I'm too tired to go out tonight.");
            other.say("Oh! That's too bad, another time then!");
        } 
        else {
            // The other is the tired one
            other.say("Sorry " + this.getName() + ", I'm too tired to go out tonight.");
            this.say("Oh! That's too bad, another time then!");
        }
    }

    public void fraternize(Snake snake) {
        if (this.energy >= 10) {
            this.say("LET'S RUN AWAY!!!");
            this.setEnergy(0);
        } 
        else {
            this.say("..."); // plays dead
        }
    }
}
