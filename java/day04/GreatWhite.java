// GreatWhite: a picky and proud Shark

public class GreatWhite extends Shark {

    public GreatWhite(String name) {
        super(name);
    }

    // Refuses to eat a Canary (instanceof tests the real class of the object)
    @Override
    public boolean canEat(Animal animal) {
        return !(animal instanceof Canary) && super.canEat(animal);
    }

    @Override
    public void eat(Animal animal) {
        if (animal instanceof Canary) {
            // A Canary only triggers this threat: the normal eat message is NOT displayed
            System.out.println(this.getName() + ": Next time you try to give me that to eat, I'll eat you instead.");
            return;
        }
        // Remember the answer BEFORE eating, then do the normal Shark eating
        boolean willEat = this.canEat(animal);
        super.eat(animal);
        // Compliment displayed after eating, only when another Shark really was the meal
        if (willEat && animal instanceof Shark) {
            System.out.println(this.getName() + ": The best meal one could wish for.");
        }
    }
}
