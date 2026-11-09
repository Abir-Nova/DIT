// Default package (no package line)
// It can USE classes of named packages,
// but a class inside a named package can never import a class of the default package

public class Astronaut {

    private static int nextId = 0;

    private final String name;
    private int snacks;
    private String destination;
    private final int id;

    public Astronaut(String name) {
        this.name = name;
        this.snacks = 0;
        this.destination = null;
        this.id = nextId++;
        System.out.println(name + " ready for launch!");
    }

    public String getName() {
        return this.name;
    }

    public int getSnacks() {
        return this.snacks;
    }

    public String getDestination() {
        return this.destination;
    }

    public int getId() {
        return this.id;
    }

    // OPTIONAL PARAMETER = OVERLOADING (as on Day 02): three methods, one name
    // We write the FULL class names (planet.Mars / chocolate.Mars) because both classes are called Mars: 
    // importing both would be ambiguous. The compiler picks the right version from the argument's type
    // Careful: never name a variable "planet" or "chocolate" here, it would hide the package
    // doActions()  -> no parameter
    public void doActions() {
        System.out.println(this.name + ": Nothing to do.");
        remindAboutSnacks();
    }

    // doActions(planet.Mars) -> a mission: the landing site becomes the destination
    public void doActions(planet.Mars mars) {
        if (mars == null) { // defensive: a null argument behaves like "no parameter"
            doActions();
            return;
        }
        System.out.println(this.name + ": Started a mission!");
        this.destination = mars.getLandingSite();
        remindAboutSnacks();
    }

    // doActions(chocolate.Mars) -> a snack: thanks, and one more snack in the bag
    // (note: the statement shows no final period for this sentence)
    public void doActions(chocolate.Mars bar) {
        if (bar == null) {
            doActions();
            return;
        }
        System.out.println(this.name + ": Thanks for this Mars number " + bar.getId());
        this.snacks++;
        remindAboutSnacks();
    }

    // NEW in Ex07: a mission to Phobos.
    // The statement does not detail the behaviour, so this is an ASSUMPTION: it works like
    // a mission to the planet (message + the moon's landing site becomes the destination).
    // Phobos lives in another package, so it is referenced by its full name.
    public void doActions(planet.moon.Phobos phobos) {
        if (phobos == null) {
            doActions();
            return;
        }
        System.out.println(this.name + ": Started a mission!");
        this.destination = phobos.getLandingSite();
        remindAboutSnacks();
    }

    // Helper shared by all versions: "after each previous sentence, if the astronaut
    // has no destination, display the snack reminder". It reads the state AFTER the update,
    // so the snack count includes the chocolate just received
    private void remindAboutSnacks() {
        if (this.destination == null) {
            System.out.println(this.name + ": I may have done nothing, but I have "
                    + this.snacks + " Mars to eat at least!");
        }
    }
}