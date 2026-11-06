import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestMain {

    private static int passed = 0;
    private static int failed = 0;

    static class TestMarine extends SpaceMarine {
        public TestMarine(String name, int hp, int ap) {
            super(name, hp, ap);
        }
    }

    public static void check(String description, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + description);
            passed++;
        } else {
            System.out.println("[FAIL] " + description);
            failed++;
        }
    }

    public static String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(output);

        try {
            System.setOut(testOut);
            action.run();
            testOut.flush();

            return output.toString().replace("\r\n", "\n");
        } finally {
            System.setOut(originalOut);
            testOut.close();
        }
    }

    public static void main(String[] args) {

        System.out.println("===== EXERCISE 05: SPACEMARINE TESTS =====\n");

        TestMarine marine = new TestMarine("Marine", 100, 30);
        TestMarine otherMarine = new TestMarine("Other", 100, 30);
        TestMarine target = new TestMarine("Target", 100, 20);

        // Initial state
        check("SpaceMarine starts without a weapon",
                marine.getWeapon() == null);

        check("SpaceMarine has the correct name",
                marine.getName().equals("Marine"));

        // Equip a Plasma Rifle
        PlasmaRifle rifle = new PlasmaRifle();

        String equipOutput = captureOutput(() -> marine.equip(rifle));

        check("Can equip a Plasma Rifle",
                marine.getWeapon() == rifle);

        check("Equip displays the correct message",
                equipOutput.equals(
                    "Marine has been equipped with a Plasma Rifle.\n"
                ));

        // Another marine cannot take the same weapon
        String stolenOutput = captureOutput(() -> otherMarine.equip(rifle));

        check("Another marine cannot equip an already taken weapon",
                otherMarine.getWeapon() == null);

        check("Failed equip produces no message",
                stolenOutput.isEmpty());

        // Replacing a weapon releases the previous one
        PowerFist fist = new PowerFist();

        check("Can replace the equipped weapon",
                marine.equip(fist));

        check("Previous weapon becomes available again",
                !rifle.isTaken());

        check("New weapon is marked as taken",
                fist.isTaken());

        check("Another marine can now equip the released weapon",
                otherMarine.equip(rifle));

        // Empty-handed attack
        TestMarine emptyMarine = new TestMarine("Empty", 100, 30);

        String emptyOutput = captureOutput(() -> emptyMarine.attack(target));

        check("Empty-handed attack returns false",
                !emptyMarine.attack(target));

        check("Empty-handed attack displays the correct message",
                emptyOutput.equals(
                    "Empty: Hey, this is crazy. I'm not going to fight this empty-handed.\n"
                ));

        // Melee weapon cannot attack from a distance
        String distantOutput = captureOutput(() -> marine.attack(target));

        check("Melee attack fails when too far away",
                !marine.attack(target));

        check("Correct too-far message is displayed",
                distantOutput.equals(
                    "Marine: I'm too far away from Target.\n"
                ));

        check("Failed melee attack does not consume AP",
                marine.getAp() == 30);

        // Move into melee range
        marine.moveCloseTo(target);

        // Successful melee attack
        String attackOutput = captureOutput(() -> marine.attack(target));

        check("Melee attack succeeds in range",
                marine.attack(target));

        check("Successful melee attack deducts 8 AP",
                marine.getAp() == 14);

        check("Successful melee attack deals 50 damage",
                target.getHp() == 50);

        check("Attack message and weapon sound are in the right order",
                attackOutput.equals(
                    "Marine attacks Target with a Power Fist.\nSBAM\n"
                ));

        // Ranged weapon can attack without moving closer
        TestMarine rangedMarine = new TestMarine("Ranged", 100, 30);
        TestMarine rangedTarget = new TestMarine("RangedTarget", 100, 20);

        PlasmaRifle rangedRifle = new PlasmaRifle();
        rangedMarine.equip(rangedRifle);

        check("Ranged marine does not need to move closer",
                !rangedMarine.moveCloseTo(rangedTarget));

        String rangedOutput = captureOutput(
                () -> rangedMarine.attack(rangedTarget)
        );

        check("Ranged weapon attacks without melee range",
                rangedTarget.getHp() == 79);

        check("Ranged attack uses the correct output",
                rangedOutput.equals(
                    "Ranged attacks RangedTarget with a Plasma Rifle.\nPIOU\n"
                ));

        check("Ranged attack deducts 5 AP",
                rangedMarine.getAp() == 25);

        // Insufficient AP
        TestMarine lowAPMarine = new TestMarine("LowAP", 100, 4);
        lowAPMarine.equip(new PlasmaRifle());

        String lowAPOutput = captureOutput(
                () -> lowAPMarine.attack(target)
        );

        check("Insufficient AP prevents attack",
                !lowAPMarine.attack(target));

        check("Insufficient AP attack produces no message",
                lowAPOutput.isEmpty());

        check("Insufficient AP is not deducted",
                lowAPMarine.getAp() == 4);

        // AP recovery
        TestMarine recoveryMarine = new TestMarine("Recovery", 100, 20);

        recoveryMarine.recoverAP();

        check("SpaceMarine recovers 9 AP",
                recoveryMarine.getAp() == 29);

        TestMarine almostFullMarine = new TestMarine("AlmostFull", 100, 48);
        almostFullMarine.recoverAP();

        check("AP never exceeds 50",
                almostFullMarine.getAp() == 50);

        // Dead marine
        TestMarine deadMarine = new TestMarine("Dead", 0, 20);
        deadMarine.equip(new PlasmaRifle());

        check("Dead marine cannot equip",
                !deadMarine.equip(new PlasmaRifle()));

        check("Dead marine cannot attack",
                !deadMarine.attack(target));

        check("Dead marine cannot move",
                !deadMarine.moveCloseTo(target));

        deadMarine.recoverAP();

        check("Dead marine cannot recover AP",
                deadMarine.getAp() == 20);

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