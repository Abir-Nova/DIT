// No "package" line: this class lives in the DEFAULT package (project root)
// exactly as the PDF delivery path "./Astronaut.java" says

public class Astronaut {

    // Shared counter (static): 1 copy for all Astronauts same trick as Mars.id
    private static int nextId = 0;

    private final String name;
    private int snacks; // an int is 0 by default; it is set explicitly in the constructor
    private String destination; // an object reference is null by default
    private final int id;

    // The only constructor takes the name: since no other constructor exists,
    // it is IMPOSSIBLE to create an Astronaut without a name ("it is mandatory")
    // new Astronaut() would not compile
    public Astronaut(String name) {
        this.name = name;
        this.snacks = 0;
        this.destination = null;
        this.id = nextId++;
        System.out.println(name + " ready for launch!");
    }

    // Getters only: no setter exists, so outside code cannot change these values
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
}
