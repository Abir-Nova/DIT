// Animal: parent of every animal (Cat, Shark, Canary...). Default package at the project root

public class Animal {

    // Protected enum: visible to Animal, its children and its package, hidden from the rest of the world
    protected enum Type {
        MAMMAL, FISH, BIRD
    }

    // Instance attributes: final + no setter = read-only (a name, a type and a number of legs never change)
    private final String name;
    private final int legs;
    private final Type type;

    // Protected constructor: only children (or the same package) can call it
    protected Animal(String name, int legs, Type type) {
        this.name = name;
        this.legs = legs;
        this.type = type;
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
}