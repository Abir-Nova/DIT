
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
}
