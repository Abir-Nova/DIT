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
}