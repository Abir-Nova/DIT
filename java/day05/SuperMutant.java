// SuperMutant: a brute monster Its name is "SuperMutant #<id>", so no parameters are needed

public class SuperMutant extends Monster {

    private static int count = 0;
    // Full health: it can never be exceeded
    private static final int MAX_HP = 170;

    private static String nextName() {
        count++;
        return "SuperMutant #" + count;
    }

    public SuperMutant() {
        // 170 hp and 20 ap
        super(nextName(), MAX_HP, 20);
        this.damage = 60;
        this.apcost = 20;
        System.out.println(this.name + ": Roaarrr!");
    }

    @Override
    public void recoverAP() {
        // Normal ap recovery (Unit) + 10 hp (never above the full health, and never raising the dead)
        super.recoverAP();
        if (this.hp > 0) {
            this.hp = Math.min(MAX_HP, this.hp + 10);
        }
    }
}