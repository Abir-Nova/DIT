package Decorator;
// A shield decorator: adds 10 health points to the holder

public class Shield extends StuffDecorator {

    // Constructor: keeps the decorated warrior and displays the shield message
    public Shield(Warrior holder) {
        this.holder = holder;
        System.out.println("May this shield protect me against every enemy.");
    }

    // Health points of the holder + 10
    @Override
    public int getHp() {
        return super.getHp() + 10;
    }
}
