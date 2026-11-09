
public class Mage extends Character {

    public Mage(String name) {
        super(name, "Mage");
        this.life = 70;
        this.strength = 3;
        this.agility = 10;
        this.wit = 10;
        System.out.println(this.name + ": May the gods be with me.");
    }

    @Override
    public void attack(String weapon) throws WeaponException {
        
        // Empty weapon first, then the weapons a mage refuses
        if (weapon == null || weapon.isEmpty()) {
            throw WeaponException.bareHands(this.name);
        }
        if (!"magic".equals(weapon) && !"wand".equals(weapon)) {
            throw WeaponException.mageRefusal(this.name, weapon);
        }
        super.attack(weapon);
        System.out.println(this.name + ": Feel the power of my " + weapon + "!");
    }

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