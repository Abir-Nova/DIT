import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
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

    // Captures everything printed by a weapon's attack() method
    public static String captureAttack(Weapon weapon) {

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            weapon.attack();
        } finally {
            System.setOut(originalOut);
        }

        return output.toString();
    }

    public static void main(String[] args) throws Exception {

        System.out.println("===== EXERCISE 02: WEAPON TESTS =====\n");

        // TEST 1: Both classes inherit from Weapon
        check(
            "PlasmaRifle extends Weapon",
            Weapon.class.isAssignableFrom(PlasmaRifle.class)
        );

        check(
            "PowerFist extends Weapon",
            Weapon.class.isAssignableFrom(PowerFist.class)
        );

        // TEST 2: Both classes have public constructors without parameters
        Constructor<?> plasmaConstructor =
            PlasmaRifle.class.getDeclaredConstructor();

        Constructor<?> fistConstructor =
            PowerFist.class.getDeclaredConstructor();

        check(
            "PlasmaRifle has a public no-argument constructor",
            Modifier.isPublic(plasmaConstructor.getModifiers())
        );

        check(
            "PowerFist has a public no-argument constructor",
            Modifier.isPublic(fistConstructor.getModifiers())
        );

        // TEST 3: Create both weapons
        PlasmaRifle plasma = new PlasmaRifle();
        PowerFist fist = new PowerFist();

        // TEST 4: PlasmaRifle attributes
        System.out.println("\n--- PlasmaRifle ---");

        check(
            "Name is Plasma Rifle",
            plasma.getName().equals("Plasma Rifle")
        );

        check(
            "AP cost is 5",
            plasma.getApcost() == 5
        );

        check(
            "Damage is 21",
            plasma.getDamage() == 21
        );

        check(
            "Melee is false",
            !plasma.isMelee()
        );

        // TEST 5: PowerFist attributes
        System.out.println("\n--- PowerFist ---");

        check(
            "Name is Power Fist",
            fist.getName().equals("Power Fist")
        );

        check(
            "AP cost is 8",
            fist.getApcost() == 8
        );

        check(
            "Damage is 50",
            fist.getDamage() == 50
        );

        check(
            "Melee is true",
            fist.isMelee()
        );

        // TEST 6: Check attack sounds and newlines
        System.out.println("\n--- Attack sounds ---");

        String plasmaSound = captureAttack(plasma);
        String fistSound = captureAttack(fist);

        check(
            "PlasmaRifle attack prints PIOU",
            plasmaSound.equals("PIOU" + System.lineSeparator())
        );

        check(
            "PowerFist attack prints SBAM",
            fistSound.equals("SBAM" + System.lineSeparator())
        );

        // TEST 7: Both attack methods are declared in their own classes
        check(
            "PlasmaRifle overrides attack()",
            PlasmaRifle.class.getDeclaredMethod("attack")
                .getDeclaringClass() == PlasmaRifle.class
        );

        check(
            "PowerFist overrides attack()",
            PowerFist.class.getDeclaredMethod("attack")
                .getDeclaringClass() == PowerFist.class
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