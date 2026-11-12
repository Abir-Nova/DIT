package Factory;

import java.util.ArrayList;
import java.util.List;
// An elf who gets toys and gift papers from the factory and packs the gifts

public class Elf {

    // The toy held by the elf (null when his hands are empty)
    private Toy toy;
    // The gift papers owned by the elf
    private List<GiftPaper> papers = new ArrayList<>();
    // The factory the elf must use to get things (he cannot create objects himself)
    private Factory factory;

    // Constructor: the elf works with the given factory
    public Elf(Factory factory) {
        this.factory = factory;
    }

    // Tries to pick the toy with the given name from the factory
    // Returns true only if a new toy has been picked up
    public boolean pickToy(String toyName) {
        Toy newToy;
        try {
            // Ask the factory for the toy
            newToy = factory.create(toyName);
        } catch (NoSuchToyException e) {
            // The factory does not know this toy
            System.out.println("I didn't find any " + toyName + ".");
            return false;
        }
        // The elf cannot take a toy if his hands are not empty
        if (toy != null) {
            System.out.println("Minute please?! I'm not that fast.");
            return false;
        }
        // His hands are empty: he keeps the new toy
        toy = newToy;
        System.out.println("What a nice one! I would have liked to keep it...");
        return true;
    }

    // Gets nb gift papers from the factory (always returns true)
    public boolean pickPapers(int nb) {
        papers.addAll(factory.getPapers(nb));
        return true;
    }

    // Packs the toy in a gift paper and returns the paper used (null if there is no
    // paper)
    public GiftPaper pack() {
        // No paper left: nothing can be packed
        if (papers.isEmpty()) {
            System.out.println("Wait... I can't pack it with my shirt.");
            return null;
        }
        // Take the first paper: in both cases below it is removed from the list
        GiftPaper paper = papers.remove(0);
        if (toy == null) {
            // No toy to wrap: only the paper is used
            System.out.println("I don't have any toy, but hey at least it's paper!");
        } else {
            // Wrap the toy: the elf's hands are empty again
            paper.wrap(toy);
            toy = null;
            System.out.println("And another kid will be happy!");
        }
        return paper;
    }
}
