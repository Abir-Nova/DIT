// Warrior: "extends Character" = a Warrior IS a Character and inherits everything that is not private
public class Warrior extends Character {
    public Warrior(String name) {
        
        // super(...) calls the Character constructor first: the name and the constant RPGClass "Warrior"
        super(name, "Warrior");
        // Then the Warrior values replace the defaults of Character
        this.life = 100;
        this.strength = 10;
        this.agility = 8;
        this.wit = 3;
        System.out.println(this.name + ": My name will go down in history!");
    }

    // @Override: the compiler checks that we really redefine the attack of the parent
    @Override
    public void attack(String weapon) {
        // A warrior can only attack with a hammer or a sword: anything else does nothing
        // "hammer".equals(weapon) compares Strings by content and is safe when weapon is null
        if ("hammer".equals(weapon) || "sword".equals(weapon)) {
            // The generic growl of Character, then the warrior's own line
            super.attack(weapon);
            System.out.println(this.name + ": I'll crush you with my " + weapon + "!");
        }
    }
}