// Weapon: ABSTRACT class = cannot be created with "new Weapon(...)"
// only through a child (PlasmaRifle, PowerFist)

public abstract class Weapon {

    // Protected attributes: visible to the children, hidden from the outside. 
    // final + no setter = read-only
    protected final String name;
    protected final int apcost;
    protected final int damage;
    protected final boolean melee;

    // NOT public (protected): only the children can call it, in this order: name, apcost, damage, melee.
    protected Weapon(String name, int apcost, int damage, boolean melee) {
        this.name = name;
        this.apcost = apcost;
        this.damage = damage;
        this.melee = melee;
    }

    // getters 
    public String getName() {
        return this.name;
    }

    public int getApcost() {
        return this.apcost;
    }

    public int getDamage() {
        return this.damage;
    }

    // Getter of a primitive boolean: by convention it starts with "is"
    public boolean isMelee() {
        return this.melee;
    }

    // Abstract method: no body here , Every child MUST implement its own attack()
    public abstract void attack();

    // Bookkeeping for SpaceMarine.equip: a weapon can belong to only one marine at a time
    private boolean taken = false;

    // Package-private helpers (no modifier): only the classes of this folder, (SpaceMarine) use them
    boolean isTaken() {
        return this.taken;
    }

    void setTaken(boolean taken) {
        this.taken = taken;
    }
}