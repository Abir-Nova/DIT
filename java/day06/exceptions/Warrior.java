
public class Warrior extends Character {

    public Warrior(String name) {
        super(name, "Warrior");
        this.life = 100;
        this.strength = 10;
        this.agility = 8;
        this.wit = 3;
        System.out.println(this.name + ": My name will go down in history!");
    }

    // The overriding method may declare the same exception as the parent (or a narrower one), never a wider one
    @Override
    public void attack(String weapon) throws WeaponException {
        // Order matters: an EMPTY weapon must give the "bare hands" message, not the "inappropriate weapon" one
        if (weapon == null || weapon.isEmpty()) {
            throw WeaponException.bareHands(this.name);
        }
        // Not a hammer, not a sword: the warrior refuses, and nothing else is displayed (no growl)
        if (!"hammer".equals(weapon) && !"sword".equals(weapon)) {
            throw WeaponException.warriorRefusal(this.name, weapon);
        }
        // Valid weapon: the generic growl of Character, then the warrior's own line
        super.attack(weapon);
        System.out.println(this.name + ": I'll crush you with my " + weapon + "!");
    }

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