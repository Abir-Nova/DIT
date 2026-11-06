// Shark: a fish (0 legs, type FISH) that can get into a frenzy and eat other animals

public class Shark extends Animal {

    // Private: only read/changed through the methods below & False by default
    private boolean frenzy = false;

    public Shark(String name) {
        super(name, 0, Type.FISH);
        System.out.println("A KILLER IS BORN!");
    }

    // Stores the received value in frenzy & Returns nothing
    public void smellBlood(boolean frenzy) {
        this.frenzy = frenzy;
    }

    public void status() {
        if (this.frenzy) {
            System.out.println(this.getName() + " is smelling blood and wants to kill.");
        } else {
            System.out.println(this.getName() + " is swimming peacefully.");
        }
    }

    // A basic shark eats anything that exists, except itself (children override this to be pickier)
    public boolean canEat(Animal animal) {
        return animal != null && animal != this;
    }

    public void eat(Animal animal) {
        // canEat is called on "this": if a child overrides it, the child's version runs (polymorphism)
        if (this.canEat(animal)) {
            System.out.println(this.getName() + " ate a " + animal.getType() + " named " + animal.getName() + ".");
            // A fed shark calms down
            this.frenzy = false;
        } else {
            System.out.println(this.getName() + ": It's not worth my time.");
        }
    }
}