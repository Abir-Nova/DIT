// SpaceMarine: abstract base of every marine
// Unlike a monster, a marine uses a Weapon

public abstract class SpaceMarine extends Unit {

    // Private: read through getWeapon(), changed only by equip()
    private Weapon weapon = null;

    protected SpaceMarine(String name, int hp, int ap) {
        super(name, hp, ap);
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public boolean equip(Weapon weapon) {
        // Does nothing (false, no message) 
        // when: dead, no weapon given or weapon already taken by a marine
        if (this.hp <= 0 || weapon == null || weapon.isTaken()) {
            return false;
        }
        // Drop the previous weapon so that somebody else can take it
        if (this.weapon != null) {
            this.weapon.setTaken(false);
        }
        this.weapon = weapon;
        weapon.setTaken(true);
        System.out.println(this.name + " has been equipped with a " + weapon.getName() + ".");
        return true;
    }

    @Override
    public boolean attack(Fighter target) {
        if (this.hp <= 0 || target == null) {
            return false;
        }
        if (this.weapon == null) {
            System.out.println(this.name + ": Hey, this is crazy. I'm not going to fight this empty-handed.");
            return false;
        }
        // Range only matters for a melee weapon
        if (this.weapon.isMelee() && this.closeTarget != target) {
            System.out.println(this.name + ": I'm too far away from " + target.getName() + ".");
            return false;
        }

        // Check available AP
        if (this.ap < this.weapon.getApcost()) {
            return false;
        }
        this.ap -= this.weapon.getApcost();
        // Announce, then the weapon makes its sound, then the target suffers
        System.out.println(this.name + " attacks " + target.getName() + " with a " + this.weapon.getName() + ".");
        this.weapon.attack();
        target.receiveDamage(this.weapon.getDamage());
        return true;
    }

    @Override
    public boolean moveCloseTo(Fighter fighter) {
        // Only a marine with a melee weapon needs (and is allowed) to move closer , otherwise nothing happens
        if (this.weapon != null && this.weapon.isMelee()) {
            return super.moveCloseTo(fighter);
        }
        return false;
    }

    @Override
    public void recoverAP() {
        if (this.hp <= 0) {
            return;
        }

        this.restoreAp(9);
    }
}