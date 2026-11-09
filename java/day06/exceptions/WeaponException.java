// WeaponException: our own CHECKED exception (it extends Exception), dedicated to weapon errors
// Checked = the compiler forces every caller to either catch it (try/catch) or declare it (throws)

public class WeaponException extends Exception {

    // Required by good practice for exceptions (version number used when an exception is serialized)
    private static final long serialVersionUID = 1L;

    // The message is stored by the parent class Exception and read back with getMessage()
    public WeaponException(String message) {
        super(message);
    }

    // The three messages are declared here, in one place, as small factories: Character, Warrior and Mage just ask for the right one
    
    // Message 1: the attack parameter is empty.
    public static WeaponException bareHands(String name) {
        return new WeaponException(name + ": I refuse to fight with my bare hands.");
    }

    // Message 2: a warrior is given a weapon that is not a hammer or a sword
    public static WeaponException warriorRefusal(String name, String weapon) {
        return new WeaponException(name + ": A " + weapon + "?? What should I do with this?!");
    }

    // Message 3: a mage is given a weapon that is not magic or a wand
    public static WeaponException mageRefusal(String name, String weapon) {
        return new WeaponException(name + ": I don't need this stupid " + weapon + "! Don't misjudge my powers!");
    }
}
