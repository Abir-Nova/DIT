// Nested package: planet.moon  ->  folder planet/moon/
// "moon inside planet" is only a naming/folder hierarchy: it gives NO special access
// Classes of planet.moon do not automatically see package-private members of planet
package planet.moon;

// Phobos is in another package than Mars, so it must import it
// Only planet.Mars is imported here, so the simple name "Mars" is unambiguous in this file
import planet.Mars;

public class Phobos {

    private final Mars mars; // the planet this moon orbits
    private final String landingSite; // the moon's own landing site

    // Both values are given at creation No setters
    public Phobos(Mars mars, String landingSite) {
        this.mars = mars;
        this.landingSite = landingSite;

        // The type system already guarantees "a Mars or null",
        // so "otherwise" means: nothing (null) was passed
        if (mars != null) {
            System.out.println("Phobos placed in orbit.");
        } else {
            System.out.println("No planet given.");
        }
    }

    public Mars getMars() {
        return this.mars;
    }

    public String getLandingSite() {
        return this.landingSite;
    }
}
