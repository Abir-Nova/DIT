// Monster: abstract base of every monster
// Monsters always fight in melee, with their own body

public abstract class Monster extends Unit {

    // Protected, 0 for now: each concrete monster sets its own values
    protected int damage = 0;
    protected int apcost = 0;

    protected Monster(String name, int hp, int ap) {
        super(name, hp, ap);
    }

    public int getDamage() {
        return this.damage;
    }

    public int getApcost() {
        return this.apcost;
    }

    @Override
    public boolean equip(Weapon weapon) {
        // A dead monster does nothing and returns false
        if (this.hp <= 0) {
            return false;
        }
        System.out.println("Monsters are proud and fight with their own bodies.");
        return false;
    }

    // Hook for the children: how much damage an attack on this target deals
    protected int getDamageAgainst(Fighter target) {
        return this.damage;
    }

    @Override
    public boolean attack(Fighter target) {
        if (this.hp <= 0 || target == null) {
            return false;
        }
        // Melee: the monster must first be close to ITS target
        if (this.closeTarget != target) {
            System.out.println(this.name + ": I'm too far away from " + target.getName() + ".");
            return false;
        }
        // In range, but not enough action points: the attack fails silently
        if (this.ap < this.apcost) {
            return false;
        }
        // Successful attack: pay the cost, announce it BEFORE the target receives the damage
        this.ap -= this.apcost;
        System.out.println(this.name + " attacks " + target.getName() + ".");
        target.receiveDamage(this.getDamageAgainst(target));
        return true;
    }
}