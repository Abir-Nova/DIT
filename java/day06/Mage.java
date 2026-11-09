// Mage: another child of Character, frail but clever
public class Mage extends Character {
    
    public Mage(String name) {
        // The name and the constant RPGClass "Mage" go to the Character constructor
        super(name, "Mage");
        this.life = 70;
        this.strength = 3;
        this.agility = 10;
        this.wit = 10;
        System.out.println(this.name + ": May the gods be with me.");
    }

    @Override
    public void attack(String weapon) {
        // A mage can ONLY attack with magic or a wand: anything else does nothing
        if ("magic".equals(weapon) || "wand".equals(weapon)) {
            super.attack(weapon);
            System.out.println(this.name + ": Feel the power of my " + weapon + "!");
        }
    }
}