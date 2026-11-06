// Animal: parent of every animal (Cat, Shark, Canary...). Default package at the project root
public class Animal {
    // Protected enum: visible to Animal, its children and its package, hidden from the rest of the world
    protected enum Type {
        MAMMAL, FISH, BIRD
    }
    // Static counters: ONE copy shared by every Animal ever created. They never go down (animals never die)
    private static int numberOfAnimals = 0;
    private static int numberOfMammals = 0;
    private static int numberOfFish = 0;
    private static int numberOfBirds = 0;
    // Instance attributes: final + no setter = read-only (a name, a type and a number of legs never change)
    private final String name;
    private final int legs;
    private final Type type;
    // Protected constructor: only children (or the same package) can call it
    protected Animal(String name, int legs, Type type) {
        this.name = name;
        this.legs = legs;
        this.type = type;
        // Every creation increases the global counter, then the counter of its own type
        numberOfAnimals++;
        switch (type) {
            case MAMMAL:
                numberOfMammals++;
                break;
            case FISH:
                numberOfFish++;
                break;
            case BIRD:
                numberOfBirds++;
                break;
        }
        System.out.println("My name is " + this.name + " and I am a " + this.getType() + "!");
    }
    public String getName() {
        return this.name;
    }
    public int getLegs() {
        return this.legs;
    }
    // Returns a lowercase String ("mammal"), NOT the enum value itself  
    // Locale.ROOT avoids locale surprises
    public String getType() {
        return this.type.name().toLowerCase(java.util.Locale.ROOT);
    }
    // Static getters: called on the class (Animal.getNumberOfAnimals()), they display AND return the counter
    public static int getNumberOfAnimals() {
        display(numberOfAnimals, "animal", "animals");
        return numberOfAnimals;
    }
    public static int getNumberOfMammals() {
        display(numberOfMammals, "mammal", "mammals");
        return numberOfMammals;
    }
    public static int getNumberOfFish() {
        // The plural of "fish" is "fish"
        display(numberOfFish, "fish", "fish");
        return numberOfFish;
    }
    public static int getNumberOfBirds() {
        display(numberOfBirds, "bird", "birds");
        return numberOfBirds;
    }
    // Shared helper: "is" + singular only when the count is exactly 1, "are" + plural otherwise (0 included)
    private static void display(int count, String singular, String plural) {
        System.out.println("There " + (count == 1 ? "is" : "are") + " currently " + count + " " + (count == 1 ? singular : plural) + " in our world.");
    }
}