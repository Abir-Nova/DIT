
import java.util.ArrayList;
import java.util.List;
// A battalion is a group of characters (Warriors, Mages, ...)

public class Battalion {

    // All the characters of the battalion
    private List<Character> characters = new ArrayList<>();

    // Adds a list of Characters (or of any class inheriting from Character)
    public void add(List<? extends Character> newCharacters) {
        characters.addAll(newCharacters);
    }

    // Prints the name of every character of the battalion
    public void display() {
        for (Character character : characters) {
            System.out.println(character.getName());
        }
    }

    // Makes the first two characters fight; returns true if a fight happened
    public boolean fight() {
        // Fewer than two characters: nobody to fight
        if (characters.size() < 2) {
            return false;
        }
        Character first = characters.get(0);
        Character second = characters.get(1);
        int result = first.compareTo(second);
        if (result > 0) {
            // The first one wins: the second one leaves the battalion
            characters.remove(1);
        } else if (result < 0) {
            // The second one wins: the first one leaves the battalion
            characters.remove(0);
        } else {
            // Tie: both leave the battalion (remove the last index first)
            characters.remove(1);
            characters.remove(0);
        }
        return true;
    }
}
