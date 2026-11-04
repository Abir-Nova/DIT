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

    public void hello(Object other) { }

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


    // work(): no parameter, returns nothing
    // Enough energy (25 or more) -> works, loses 9 energy
    // 24 or less -> naps, regains 50 energy
    // The two branches are exclusive so a single if/else is enough
    // setEnergy keeps the value in [0, 100] so a nap at 70 gives 100 and not 120
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
}
