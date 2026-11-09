// Same class as before (Ex01 to Ex05) + the exception handling of Ex06. 
// Default package, NOT a package "exceptions"
public abstract class Character implements Movable {
    protected String name;
    protected int life = 50;
    protected int agility = 2;
    protected int strength = 2;
    protected int wit = 2;
    // Constant: given once in the constructor, never changed afterwards
    protected final String RPGClass;
    
    protected Character(String name, String RPGClass) {
        this.name = name;
        this.RPGClass = RPGClass;
    }
    
    public String getName() {
        return this.name;
    }
    public int getLife() {
        return this.life;
    }
    public int getAgility() {
        return this.agility;
    }
    public int getStrength() {
        return this.strength;
    }
    public int getWit() {
        return this.wit;
    }
    public String getRPGClass() {
        return this.RPGClass;
    }
    
    // "throws WeaponException": warns every caller that this method may fail with that checked exception
    public void attack(String weapon) throws WeaponException {
        // An empty (or missing) weapon means bare hands: refuse by throwing the exception
        if (weapon == null || weapon.isEmpty()) {
            throw WeaponException.bareHands(this.name);
        }
        System.out.println(this.name + ": Rrrrrrrrr....");
    }
    
    // Calls attack, catches the exception and prints its message: the caller never has to deal with the exception
    // Because attack is overridden, the Warrior or Mage version runs here (polymorphism)
    public void tryToAttack(String weapon) {
        try {
            this.attack(weapon);
        } catch (WeaponException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void moveRight() {
        System.out.println(this.name + ": moves right");
    }
    @Override
    public void moveLeft() {
        System.out.println(this.name + ": moves left");
    }
    @Override
    public void moveForward() {
        System.out.println(this.name + ": moves forward");
    }
    @Override
    public void moveBack() {
        System.out.println(this.name + ": moves back");
    }
    
    // final: cannot be overridden by Warrior or Mage.
    public final void unsheathe() {
        System.out.println(this.name + ": unsheathes his weapon.");
    }
}
