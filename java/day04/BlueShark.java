// BlueShark: a picky Shark
// Only canEat changes
// eat is inherited as is

public class BlueShark extends Shark {

    public BlueShark(String name) {
        super(name);
    }

    // @Override makes the compiler check that we really redefine a method of the parent
    @Override
    public boolean canEat(Animal animal) {
        // super.canEat keeps the common rules (not null, not itself), then only FISH is accepted
        return super.canEat(animal) && animal.getType().equals("fish");
    }
}
