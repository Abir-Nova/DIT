import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestMain {

    private static int passed = 0;
    private static int failed = 0;

    // Prints the result of each test
    public static void check(String description, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + description);
            passed++;
        } else {
            System.out.println("[FAIL] " + description);
            failed++;
        }
    }

    // A simple test class implementing Fighter
    static class TestFighter implements Fighter {

        public boolean equip(Weapon weapon) {
            return true;
        }

        public boolean attack(Fighter fighter) {
            return true;
        }

        public void receiveDamage(int damage) {
        }

        public boolean moveCloseTo(Fighter fighter) {
            return true;
        }

        public void recoverAP() {
        }

        public String getName() {
            return "Test Fighter";
        }

        public int getAp() {
            return 20;
        }

        public int getHp() {
            return 100;
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("===== EXERCISE 03: FIGHTER TESTS =====\n");

        // TEST 1: Fighter must be an interface
        check(
            "Fighter is an interface",
            Fighter.class.isInterface()
        );

        // TEST 2: Check the number of required methods
        Method[] methods = Fighter.class.getDeclaredMethods();

        check(
            "Fighter contains exactly 8 methods",
            methods.length == 8
        );

        // TEST 3: Check each method's exact signature
        check(
            "equip(Weapon) returns boolean",
            Fighter.class.getDeclaredMethod("equip", Weapon.class)
                .getReturnType() == boolean.class
        );

        check(
            "attack(Fighter) returns boolean",
            Fighter.class.getDeclaredMethod("attack", Fighter.class)
                .getReturnType() == boolean.class
        );

        check(
            "receiveDamage(int) returns void",
            Fighter.class.getDeclaredMethod("receiveDamage", int.class)
                .getReturnType() == void.class
        );

        check(
            "moveCloseTo(Fighter) returns boolean",
            Fighter.class.getDeclaredMethod("moveCloseTo", Fighter.class)
                .getReturnType() == boolean.class
        );

        check(
            "recoverAP() returns void",
            Fighter.class.getDeclaredMethod("recoverAP")
                .getReturnType() == void.class
        );

        check(
            "getName() returns String",
            Fighter.class.getDeclaredMethod("getName")
                .getReturnType() == String.class
        );

        check(
            "getAp() returns int",
            Fighter.class.getDeclaredMethod("getAp")
                .getReturnType() == int.class
        );

        check(
            "getHp() returns int",
            Fighter.class.getDeclaredMethod("getHp")
                .getReturnType() == int.class
        );

        // TEST 4: All methods must be public and abstract
        for (Method method : methods) {

            int modifiers = method.getModifiers();

            check(
                method.getName() + "() is public",
                Modifier.isPublic(modifiers)
            );

            check(
                method.getName() + "() is abstract",
                Modifier.isAbstract(modifiers)
            );
        }

        // TEST 5: A class can implement Fighter
        TestFighter fighter = new TestFighter();

        check(
            "TestFighter implements Fighter",
            fighter instanceof Fighter
        );

        check(
            "getName() works",
            fighter.getName().equals("Test Fighter")
        );

        check(
            "getAp() works",
            fighter.getAp() == 20
        );

        check(
            "getHp() works",
            fighter.getHp() == 100
        );

        // SUMMARY
        System.out.println("\n===== TEST SUMMARY =====");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));

        if (failed == 0) {
            System.out.println("\nALL TESTS PASSED!");
        } else {
            System.out.println("\nSOME TESTS FAILED!");
        }
    }
}