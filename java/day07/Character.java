// Character: ABSTRACT parent of every hero (Warrior, Mage)
// Abstract = it can only be used through a child class

public abstract class Character implements Movable, Comparable<Object> {

    // Protected attributes: visible to the children (Warrior, Mage) and hidden from the outside world
    protected String name;
    protected int life = 50;
    protected int agility = 2;
    protected int strength = 2;
    protected int wit = 2;
    // EX05: capacity of the character (power for a Warrior, magnetism for a Mage), 0 by default
    protected int capacity = 0;
    // "final" = a CONSTANT: it is given once in the constructor and can never change afterwards
    protected final String RPGClass;

    // Protected constructor: name first, RPGClass second. only the children can call it through super(...)
    protected Character(String name, String RPGClass) {
        this.name = name;
        this.RPGClass = RPGClass;
    }

    // EX05: same constructor as above, but it also takes the capacity
    protected Character(String name, String RPGClass, int capacity) {
        this.name = name;
        this.RPGClass = RPGClass;
        this.capacity = capacity;
    }

    // Getters only (no setters): the attributes are read from the outside, never written
    public String getName() {
        return this.name;
    }

    public int getLife() {
        return this.life;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getWit() {
        return this.wit;
    }

    public String getRPGClass() {
        return this.RPGClass;
    }

    // Generic attack: growls, whatever the weapon is
    // The children reuse it through super.attack(...)
    public void attack(String weapon) {
        System.out.println(this.name + ": Rrrrrrrrr....");
    }

    // movements 
    @Override
    public void moveRight() {
        System.out.println(this.name + ": moves right");
    }

    @Override
    public void moveLeft() {
        System.out.println(this.name + ": moves left");
    }

    @Override
    public void moveForward() {
        System.out.println(this.name + ": moves forward");
    }

    @Override
    public void moveBack() {
        System.out.println(this.name + ": moves back");
    }

    // EX05 : 
    // "final" = this method can not be overridden by Warrior or Mage: everybody draws a weapon the same way
    public final void unsheathe() {
        System.out.println(this.name + ": unsheathes his weapon.");
    }

    // EX05: Comparable interface. The parameter is an Object so that anything that is not a Character can be received (it returns 0)
    @Override
    public int compareTo(Object other) {
        // Not a character: nothing to compare, return 0
        if (!(other instanceof Character)) {
            return 0;
        }
        Character o = (Character) other;
        // Same type (two Mages or two Warriors): the biggest capacity is the greatest
        if ((this instanceof Mage && o instanceof Mage) || (this instanceof Warrior && o instanceof Warrior)) {
            return Integer.compare(this.capacity, o.capacity);
        }
        // This is a Mage and the other is a Warrior: the Warrior is the greatest only if its capacity is a multiple of the Mage's
        // (a capacity of 0 is tested first to avoid a division by zero)
        if (this instanceof Mage && o instanceof Warrior) {
            return (this.capacity != 0 && o.capacity % this.capacity == 0) ? -1 : 1;
        }
        // This is a Warrior and the other is a Mage: same rule seen from the other side
        if (this instanceof Warrior && o instanceof Mage) {
            return (o.capacity != 0 && this.capacity % o.capacity == 0) ? 1 : -1;
        }
        // Any other kind of character: considered equal
        return 0;
    }
}
