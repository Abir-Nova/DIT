package Decorator;
// A fire sword decorator: adds 3 damage points to the holder

public class FireSword extends StuffDecorator {

    // Constructor: keeps the decorated warrior and displays the sword message
    public FireSword(Warrior holder) {
        this.holder = holder;
        System.out.println("I can slice and burn like the wind and the flames.");
    }

    // Damage points of the holder + 3
    @Override
    public int getDmg() {
        return super.getDmg() + 3;
    }
}
