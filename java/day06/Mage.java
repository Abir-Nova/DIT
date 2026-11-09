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
        // A mage can only attack with magic or a wand: anything else does nothing
        if ("magic".equals(weapon) || "wand".equals(weapon)) {
            super.attack(weapon);
            System.out.println(this.name + ": Feel the power of my " + weapon + "!");
        }
    }

    // Ex04: the Mage moves "furtively"
    @Override
    public void moveRight() {
        System.out.println(this.name + ": moves right furtively.");
    }
    @Override
    public void moveLeft() {
        System.out.println(this.name + ": moves left furtively.");
    }
    @Override
    public void moveBack() {
        System.out.println(this.name + ": moves back furtively.");
    }
    @Override
    public void moveForward() {
        System.out.println(this.name + ": moves forward furtively.");
    }
}