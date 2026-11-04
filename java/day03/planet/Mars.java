// Another class named Mars, but in package "planet": full name planet.Mars
// Two classes may share a simple name as long as their packages differ
package planet;

public class Mars {

    private final String landingSite;

    // The landing site is given at creation as required
    public Mars(String landingSite) {
        this.landingSite = landingSite;
    }

    public String getLandingSite() {
        return this.landingSite;
    }
}






