// Warrior: "extends Character" = a Warrior IS a Character and inherits everything that is not private

public class Warrior extends Character {

    public Warrior(String name) {
        // Without capacity: use the default value 0
        this(name, 0);
    }

    // EX05: constructor with the capacity (for a Warrior it is the power)
    public Warrior(String name, int capacity) {
        // super(...) calls the Character constructor first: the name, the constant RPGClass "Warrior" and the capacity
        super(name, "Warrior", capacity);
        // Then the Warrior values replace the defaults of Character
        this.life = 100;
        this.strength = 10;
        this.agility = 8;
        this.wit = 3;
        System.out.println(this.name + ": My name will go down in history!");
    }

    // @Override: the compiler checks that we really redefine the attack of the parent
    @Override
    public void attack(String weapon) {
        // A warrior can only attack with a hammer or a sword: anything else does nothing
        // "hammer".equals(weapon) compares Strings by content and is safe when weapon is null
        if ("hammer".equals(weapon) || "sword".equals(weapon)) {
            // The generic growl of Character, then the warrior's own line
            super.attack(weapon);
            System.out.println(this.name + ": I'll crush you with my " + weapon + "!");
        }
    }

    // Ex04: the Warrior moves "like a bad boy" (override of the Character versions)
    @Override
    public void moveRight() {
        System.out.println(this.name + ": moves right like a bad boy.");
    }

    @Override
    public void moveLeft() {
        System.out.println(this.name + ": moves left like a bad boy.");
    }

    @Override
    public void moveBack() {
        System.out.println(this.name + ": moves back like a bad boy.");
    }

    @Override
    public void moveForward() {
        System.out.println(this.name + ": moves forward like a bad boy.");
    }
}
