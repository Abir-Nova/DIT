package Decorator;
// Abstract base class of every warrior

public abstract class Warrior {

    // Health points (protected so the child classes can set them)
    protected int hp;
    // Damage points caused by the warrior
    protected int dmg;

    // Getter of the health points
    public int getHp() {
        return hp;
    }

    // Getter of the damage points
    public int getDmg() {
        return dmg;
    }
}
