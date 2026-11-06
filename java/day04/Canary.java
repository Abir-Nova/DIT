// Canary: a bird (2 legs, type BIRD) that lays eggs

public class Canary extends Animal {

    // Private: number of eggs laid in its life, 0 at the beginning
    private int eggs = 0;

    public Canary(String name) {
        super(name, 2, Type.BIRD);
        System.out.println("Yellow and smart? Here I am!");
    }

    public int getEggsCount() {
        return this.eggs;
    }

    public void layEgg() {
        this.eggs++;
    }
}
