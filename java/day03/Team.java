
import java.util.ArrayList;

// Default package, like Astronaut, so Team can use Astronaut directly
public class Team {

    private final String name;
    // The team HOLDS Astronauts (it does not create them): this is "composition"
    // ArrayList<Astronaut> = a resizable list that can only contain Astronaut objects
    private final ArrayList<Astronaut> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void add(Astronaut astronaut) {
        if (astronaut != null) {
            this.members.add(astronaut);
        }
    }

    // remove(Object) deletes the first element that IS that object (same reference)
    public void remove(Astronaut astronaut) {
        this.members.remove(astronaut);
    }

    public int countMembers() {
        return this.members.size();
    }

    // Target: "SpaceBrothers: Mutta on mission, Hibito on standby, Serika on standby"
    // We build ONE String and display it once, so the separators are easy to place
    public void showMembers() {
        if (this.members.isEmpty()) {
            return; // empty team: display nothing
        }
        StringBuilder line = new StringBuilder(this.name).append(": ");
        for (int i = 0; i < this.members.size(); i++) {
            Astronaut a = this.members.get(i);
            if (i > 0) {
                line.append(", "); // separator BETWEEN members only, never before the first
            }
            // "on mission" exactly when the astronaut has a destination
            line.append(a.getName());
            line.append(a.getDestination() != null ? " on mission" : " on standby");
        }
        line.append(".");
        System.out.println(line);
    }

    // ---------------- Ex07 ----------------
    // The team's doActions is overloaded like the Astronaut's one.
    // No parameter: the TEAM speaks, ONCE. It must not call the astronauts' doActions(),
    // otherwise each astronaut would display its own "Nothing to do.".
    public void doActions() {
        System.out.println(this.name + ": Nothing to do.");
    }

    // With a parameter: every member runs its own doActions with that same argument.
    // For a chocolate.Mars, each astronaut gets a full chocolate (snacks + 1 each),
    // since the same object is simply handed to everyone.
    public void doActions(planet.Mars mars) {
        if (mars == null) {
            doActions();
            return;
        }
        for (Astronaut a : this.members) {
            a.doActions(mars);
        }
    }

    public void doActions(chocolate.Mars bar) {
        if (bar == null) {
            doActions();
            return;
        }
        for (Astronaut a : this.members) {
            a.doActions(bar);
        }
    }

    public void doActions(planet.moon.Phobos phobos) {
        if (phobos == null) {
            doActions();
            return;
        }
        for (Astronaut a : this.members) {
            a.doActions(phobos);
        }
    }
}