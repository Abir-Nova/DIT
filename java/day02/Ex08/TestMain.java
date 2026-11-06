
public class TestMain {
    public static void main(String[] args) {
        Gecko a = new Gecko("Arthur");
        Gecko b = new Gecko("Benjy");
        Gecko tired = new Gecko("Abir");
        Gecko tired2 = new Gecko("Mouad");
        tired.setEnergy(20);
        tired2.setEnergy(10);

        System.out.println("--- both fine");
        a.fraternize(b);
        System.out.println(a.getEnergy() + " " + b.getEnergy()); // 70 70

        System.out.println("--- I am tired");
        tired.fraternize(a);

        System.out.println("--- the other is tired");
        a.fraternize(tired);

        System.out.println("--- both tired");
        tired.fraternize(tired2);

        System.out.println("--- frontier: exactly 30 energy");
        Gecko e30 = new Gecko("Thirty");
        e30.setEnergy(30);
        e30.fraternize(a);
        System.out.println(e30.getEnergy() + " " + a.getEnergy()); // 0 40

        Snake snake = new Snake();
        System.out.println("--- snake, energy >= 10");
        Gecko s1 = new Gecko("Sam");
        s1.fraternize(snake);
        System.out.println(s1.getEnergy()); // 0

        System.out.println("--- snake, energy < 10");
        Gecko s2 = new Gecko("Sue");
        s2.setEnergy(9);
        s2.fraternize(snake);
        System.out.println(s2.getEnergy()); // 9
    }
}
