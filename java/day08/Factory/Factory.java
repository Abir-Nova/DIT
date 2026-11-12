package Factory;

import java.util.ArrayList;
import java.util.List;

// The factory hides how toys and gift papers are created
public class Factory {
    // Creates the toy matching the given name, or throws if the name is unknown
    public Toy create(String toyName) throws NoSuchToyException {
        // "teddy" gives a TeddyBear
        if ("teddy".equals(toyName)) {
            return new TeddyBear();
        }
        // "gameboy" gives a Gameboy
        if ("gameboy".equals(toyName)) {
            return new Gameboy();
        }
        // Any other name: this toy does not exist
        throw new NoSuchToyException("No such toy: " + toyName + ".");
    } 

    // Creates a list that contains n gift papers
    public List<GiftPaper> getPapers(int n) {
        List<GiftPaper> papers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            papers.add(new GiftPaper());
        }
        return papers;
    }
}
