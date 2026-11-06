// AssaultTerminator: a heavy marine with a PowerFist and a thick armor

public class AssaultTerminator extends SpaceMarine {

    public AssaultTerminator(String name) {
        // 150 hp and 30 ap by default
        super(name, 150, 30);
        System.out.println(name + " has teleported from space.");
        this.equip(new PowerFist());
    }

    @Override
    public void receiveDamage(int damage) {
        // The armor absorbs 3 damage, but at least 1 damage always gets through;
        super.receiveDamage(Math.max(1, damage - 3));
    }
}