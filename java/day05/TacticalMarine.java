// TacticalMarine: a standard marine with a PlasmaRifle

public class TacticalMarine extends SpaceMarine {

    public TacticalMarine(String name) {
        // 100 hp and 20 ap by default
        super(name, 100, 20);
        System.out.println(name + " on duty.");
        this.equip(new PlasmaRifle());
    }

    @Override
    public void recoverAP() {
        this.restoreAp(12); // 12 instead of 9
    }
}