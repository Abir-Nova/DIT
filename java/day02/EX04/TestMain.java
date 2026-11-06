
public class TestMain {
    public static void main(String[] args) {
        Gecko arthur = new Gecko("Arthur", 5); // Hello Arthur!
        System.out.println(arthur.getAge());   // 5
        arthur.setAge(12);
        System.out.println(arthur.getAge());   // 12

        // Every frontier of the age ranges:
        int[] ages = {-1, 0, 1, 2, 3, 10, 11, 13, 14};
        // expected: Impossible, Unborn, Baby, Baby, Adult, Adult, Old, Old, Impossible
        Gecko g = new Gecko();                 // Hello!
        for (int a : ages) {
            g.setAge(a);
            System.out.print(a + " -> ");
            g.status();
        }
    }
}
