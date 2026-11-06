
public class TestMain {

    public static void main(String[] args) {
        Gecko g = new Gecko("Gigi");      // Hello Gigi!
        System.out.println(g.getEnergy());     // 100 (default)

        g.eat("VEGETABLE");               // Erk!
        System.out.println(g.getEnergy());     // 90
        g.eat("meAt");                   // Yummy!
        g.eat("MEAT");                   // Yummy!  (capped: 100, not 110)
        System.out.println(g.getEnergy());     // 100
        g.eat("pizza");                  // I can't eat this!
        System.out.println(g.getEnergy());     // 100

        g.setEnergy(500);
        System.out.println(g.getEnergy());     // 100
        g.setEnergy(-20);
        System.out.println(g.getEnergy());     // 0
        g.eat("Vegetable");              // Erk!  (stays 0)
        System.out.println(g.getEnergy());     // 0
    }
}
