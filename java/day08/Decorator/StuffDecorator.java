package Decorator;
// Base class of the decorators: a warrior that wraps another warrior (the holder)

public class StuffDecorator extends Warrior {

    // The decorated warrior
    protected Warrior holder;

    // By default the health points are the ones of the holder
    @Override
    public int getHp() {
        return holder.getHp();
    }

    // By default the damage points are the ones of the holder
    @Override
    public int getDmg() {
        return holder.getDmg();
    }
}
