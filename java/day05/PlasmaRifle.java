public class PlasmaRifle extends Weapon {

    public PlasmaRifle() {
        // Reuse the parent constructor instead of rewriting it: name, apcost, damage, melee
        super("Plasma Rifle", 5, 21, false); // PlasmaRifle: a ranged weapon (melee = false)
    }

    @Override
    public void attack() {
        // The sound of this weapon
        System.out.println("PIOU");
    }
}