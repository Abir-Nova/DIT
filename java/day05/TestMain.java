import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestMain {

    private static int passed = 0;
    private static int failed = 0;

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

        System.out.println("===== EXERCISE 06: MARINE TESTS =====\n");

        // TACTICAL MARINE CONSTRUCTOR
        final TacticalMarine[] tactical = new TacticalMarine[1];

        String tacticalOutput = captureOutput(
            () -> tactical[0] = new TacticalMarine("Tactical")
        );

        TacticalMarine tm = tactical[0];

        check("TacticalMarine is created",
                tm != null);

        check("TacticalMarine displays the correct constructor messages",
                tacticalOutput.equals(
                    "Tactical on duty.\n"
                    + "Tactical has been equipped with a Plasma Rifle.\n"
                ));

        check("TacticalMarine has 100 HP",
                tm.getHp() == 100);

        check("TacticalMarine has 20 AP",
                tm.getAp() == 20);

        check("TacticalMarine has a PlasmaRifle",
                tm.getWeapon() instanceof PlasmaRifle);

        check("TacticalMarine has the correct weapon name",
                tm.getWeapon().getName().equals("Plasma Rifle"));

        check("PlasmaRifle has 5 AP cost",
                tm.getWeapon().getApcost() == 5);

        check("PlasmaRifle deals 21 damage",
                tm.getWeapon().getDamage() == 21);

        check("PlasmaRifle is not melee",
                !tm.getWeapon().isMelee());

        // TACTICAL MARINE RECOVERY
        tm.recoverAP();

        check("TacticalMarine recovers 12 AP",
                tm.getAp() == 32);

        tm.recoverAP();
        tm.recoverAP();

        check("TacticalMarine AP does not exceed 50",
                tm.getAp() == 50);

        // ASSAULT TERMINATOR CONSTRUCTOR
        final AssaultTerminator[] assault = new AssaultTerminator[1];

        String assaultOutput = captureOutput(
            () -> assault[0] = new AssaultTerminator("Assault")
        );

        AssaultTerminator at = assault[0];

        check("AssaultTerminator is created",
                at != null);

        check("AssaultTerminator displays the correct constructor messages",
                assaultOutput.equals(
                    "Assault has teleported from space.\n"
                    + "Assault has been equipped with a Power Fist.\n"
                ));

        check("AssaultTerminator has 150 HP",
                at.getHp() == 150);

        check("AssaultTerminator has 30 AP",
                at.getAp() == 30);

        check("AssaultTerminator has a PowerFist",
                at.getWeapon() instanceof PowerFist);

        check("PowerFist has the correct weapon name",
                at.getWeapon().getName().equals("Power Fist"));

        check("PowerFist has 8 AP cost",
                at.getWeapon().getApcost() == 8);

        check("PowerFist deals 50 damage",
                at.getWeapon().getDamage() == 50);

        check("PowerFist is a melee weapon",
                at.getWeapon().isMelee());

        // ASSAULT TERMINATOR ARMOR
        at.receiveDamage(10);

        check("Armor reduces 10 damage to 7",
                at.getHp() == 143);

        at.receiveDamage(3);

        check("Armor reduces 3 damage to 1",
                at.getHp() == 142);

        at.receiveDamage(1);

        check("Armor never reduces damage below 1",
                at.getHp() == 141);

        // Check that large damage cannot make HP negative
        at.receiveDamage(1000);

        check("AssaultTerminator HP never goes below zero",
                at.getHp() == 0);

        // Separate instance to test the minimum damage rule
        AssaultTerminator minimumDamage =
                new AssaultTerminator("Minimum");

        minimumDamage.receiveDamage(1);

        check("Minimum incoming damage still causes 1 damage",
                minimumDamage.getHp() == 149);

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