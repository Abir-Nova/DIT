// RadScorpion: a fast monster Its name is "RadScorpion #<id>" so no parameters are needed

public class RadScorpion extends Monster {

    // Static: one counter shared by all RadScorpions (SuperMutant has its own counter)
    private static int count = 0;

    // Static helper usable inside super(...): increments the counter and builds the name
    private static String nextName() {
        count++;
        return "RadScorpion #" + count;
    }

    public RadScorpion() {
        // 80 hp, maximum ap (50)
        super(nextName(), 80, 50);
        this.damage = 25;
        this.apcost = 8;
        System.out.println(this.name + ": Crrr!");
    }

    @Override
    protected int getDamageAgainst(Fighter target) {
        // Double damage on a marine, except on an AssaultTerminator (armor): everything else takes the normal damage
        if (target instanceof SpaceMarine && !(target instanceof AssaultTerminator)) {
            return this.damage * 2;
        }
        return this.damage;
    }
}