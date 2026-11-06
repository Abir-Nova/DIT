
public class TestMain {
    public static void main(String[] args) {
        Gecko g = new Gecko("Gigi");
        g.setEnergy(25);
        g.work(); // I'm working T.T  (25 >= 25)
        System.out.println(g.getEnergy()); // 16
        g.work(); // Heyyy I'm too sleepy, better take a nap!
        System.out.println(g.getEnergy()); // 66

        g.setEnergy(24); // frontier: 24 or less -> nap
        g.work();
        System.out.println(g.getEnergy()); // 74

        g.setEnergy(80);
        g.work(); // working
        System.out.println(g.getEnergy()); // 71
    }
}
