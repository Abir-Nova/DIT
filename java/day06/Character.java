// Character: ABSTRACT parent of every hero (Warrior, Mage)
// Abstract = it can only be used through a child class

public abstract class Character implements Movable {

    // Protected attributes: visible to the children (Warrior, Mage) and hidden from the outside world
    protected String name;
    protected int life = 50;
    protected int agility = 2;
    protected int strength = 2;
    protected int wit = 2;
    // "final" = a CONSTANT: it is given once in the constructor and can never change afterwards
    protected final String RPGClass;

    // Protected constructor: name first, RPGClass second. Only the children can call it through super(...)
    protected Character(String name, String RPGClass) {
        this.name = name;
        this.RPGClass = RPGClass;
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
}
