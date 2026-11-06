// Unit: abstract base of every fighter , It implements the generic part of Fighter
// equip and attack are left abstract because Monster do them differently

public abstract class Unit implements Fighter {

    // Protected: the children (Monster) read and change them directly
    protected String name;
    protected int hp;
    protected int ap;
    // The one Fighter this unit is currently close to (null = close to nobody)
    protected Fighter closeTarget;

    // Only one constructor, protected: name, hp, ap in this order
    protected Unit(String name, int hp, int ap) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.closeTarget = null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public int getAp() {
        return this.ap;
    }

    public Fighter getCloseTarget() {
        return this.closeTarget;
    }

    @Override
    public void receiveDamage(int damage) {
        // Dead is dead: nothing more can happen to a unit that has no hp left
        if (this.hp <= 0) {
            return;
        }
        this.hp -= damage;
        // Never store a negative hp: 0 means dead
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    @Override
    public boolean moveCloseTo(Fighter fighter) {
        // Refuse (false, no message) 
        // when: dead, nobody given, moving closer to itself,
        // or already close to this very target
        if (this.hp <= 0 || fighter == null || fighter == this || fighter == this.closeTarget) {
            return false;
        }
        System.out.println(this.name + " is moving closer to " + fighter.getName() + ".");
        this.closeTarget = fighter;
        return true;
    }

    @Override
    public void recoverAP() {
        if (this.hp <= 0){
            return;
        }
        this.restoreAp(7);
    }

    // Helper for the children: adds "amount" ap without ever going over 50 (and never lowers the ap)
    protected void restoreAp(int amount) {
        this.ap = Math.min(50, this.ap + amount);
    }
}