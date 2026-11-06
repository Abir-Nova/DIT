
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

        System.out.println("===== EXERCISE 07: MONSTER TESTS =====\n");

        // RADSCORPION CONSTRUCTORS AND NUMBERING
        final RadScorpion[] rad1 = new RadScorpion[1];

        String rad1Output = captureOutput(
                () -> rad1[0] = new RadScorpion()
        );

        check("First RadScorpion has the correct name",
                rad1[0].getName().equals("RadScorpion #1"));

        check("First RadScorpion displays the correct message",
                rad1Output.equals("RadScorpion #1: Crrr!\n"));

        check("RadScorpion starts with 80 HP",
                rad1[0].getHp() == 80);

        check("RadScorpion starts with 50 AP",
                rad1[0].getAp() == 50);

        check("RadScorpion deals 25 damage",
                rad1[0].getDamage() == 25);

        check("RadScorpion attack costs 8 AP",
                rad1[0].getApcost() == 8);

        // SUPERMUTANT CONSTRUCTORS AND NUMBERING
        final SuperMutant[] mutant1 = new SuperMutant[1];

        String mutant1Output = captureOutput(
                () -> mutant1[0] = new SuperMutant()
        );

        check("First SuperMutant has the correct name",
                mutant1[0].getName().equals("SuperMutant #1"));

        check("First SuperMutant displays the correct message",
                mutant1Output.equals("SuperMutant #1: Roaarrr!\n"));

        check("SuperMutant starts with 170 HP",
                mutant1[0].getHp() == 170);

        check("SuperMutant starts with 20 AP",
                mutant1[0].getAp() == 20);

        check("SuperMutant deals 60 damage",
                mutant1[0].getDamage() == 60);

        check("SuperMutant attack costs 20 AP",
                mutant1[0].getApcost() == 20);

        // NUMBERING IS INDEPENDENT BETWEEN MONSTER CLASSES
        final RadScorpion[] rad2 = new RadScorpion[1];
        final SuperMutant[] mutant2 = new SuperMutant[1];

        captureOutput(() -> rad2[0] = new RadScorpion());
        captureOutput(() -> mutant2[0] = new SuperMutant());

        check("Second RadScorpion is numbered #2",
                rad2[0].getName().equals("RadScorpion #2"));

        check("Second SuperMutant is numbered #2",
                mutant2[0].getName().equals("SuperMutant #2"));

        // RADSCORPION DAMAGE AGAINST TACTICAL MARINE
        final TacticalMarine[] tactical = new TacticalMarine[1];

        captureOutput(() -> tactical[0] = new TacticalMarine("Tactical"));

        RadScorpion attacker = rad1[0];

        attacker.moveCloseTo(tactical[0]);

        final boolean[] attackResult = {false};

        String attackOutput = captureOutput(
                () -> attackResult[0] = attacker.attack(tactical[0])
        );

        check("RadScorpion successfully attacks TacticalMarine",
                attackResult[0]);

        check("RadScorpion deals double damage to TacticalMarine",
                tactical[0].getHp() == 50);

        check("RadScorpion spends 8 AP",
                attacker.getAp() == 42);

        check("RadScorpion displays the correct attack message",
                attackOutput.equals(
                        "RadScorpion #1 attacks Tactical.\n"
                ));

        // RADSCORPION DAMAGE AGAINST ASSAULT TERMINATOR
        final AssaultTerminator[] terminator = new AssaultTerminator[1];

        captureOutput(() -> terminator[0]
                = new AssaultTerminator("Terminator"));

        RadScorpion attacker2 = rad2[0];

        attacker2.moveCloseTo(terminator[0]);

        final boolean[] terminatorAttack = {false};

        captureOutput(
                () -> terminatorAttack[0]
                = attacker2.attack(terminator[0])
        );

        check("RadScorpion successfully attacks AssaultTerminator",
                terminatorAttack[0]);

        check("AssaultTerminator receives normal RadScorpion damage",
                terminator[0].getHp() == 128);

        // NORMAL DAMAGE AGAINST ANOTHER MONSTER
        RadScorpion attacker3 = new RadScorpion();
        SuperMutant targetMutant = new SuperMutant();

        attacker3.moveCloseTo(targetMutant);

        final boolean[] normalAttack = {false};

        captureOutput(
                () -> normalAttack[0]
                = attacker3.attack(targetMutant)
        );

        check("RadScorpion deals normal damage to a Monster",
                normalAttack[0] && targetMutant.getHp() == 145);

        // SUPERMUTANT ATTACK
        SuperMutant brute = new SuperMutant();
        SuperMutant victim = new SuperMutant();

        brute.moveCloseTo(victim);

        final boolean[] bruteAttack = {false};

        String bruteOutput = captureOutput(
                () -> bruteAttack[0] = brute.attack(victim)
        );

        check("SuperMutant successfully attacks",
                bruteAttack[0]);

        check("SuperMutant deals 60 damage",
                victim.getHp() == 110);

        check("SuperMutant spends 20 AP",
                brute.getAp() == 0);

        check("SuperMutant displays the correct attack message",
                bruteOutput.equals(
                        "SuperMutant #4 attacks SuperMutant #5.\n"
                ));

        // SUPERMUTANT RECOVERY
        SuperMutant healer = new SuperMutant();

        healer.receiveDamage(20);

        healer.recoverAP();

        check("SuperMutant recovers 7 AP",
                healer.getAp() == 27);

        check("SuperMutant recovers 10 HP",
                healer.getHp() == 160);

        healer.recoverAP();

        check("SuperMutant HP recovery does not exceed 170",
                healer.getHp() == 170);

        check("SuperMutant AP recovery continues",
                healer.getAp() == 41);

        // DEAD SUPERMUTANT
        SuperMutant deadMutant = new SuperMutant();

        deadMutant.receiveDamage(1000);

        int oldAP = deadMutant.getAp();

        deadMutant.recoverAP();

        check("Dead SuperMutant stays at zero HP",
                deadMutant.getHp() == 0);

        check("Dead SuperMutant cannot recover AP",
                deadMutant.getAp() == oldAP);

        // ATTACK WITH INSUFFICIENT AP
        SuperMutant lowAP = new SuperMutant();
        SuperMutant lowAPTarget = new SuperMutant();

        // Spend all available AP using repeated recovery-independent setup
        // The constructor starts with 20 AP; reduce it through attacks
        // by using a separate attacker with a controlled target.
        lowAP.moveCloseTo(lowAPTarget);

        final boolean[] lowAPResult = {false};

        // First attack consumes all 20 AP
        captureOutput(() -> lowAPResult[0]
                = lowAP.attack(lowAPTarget));

        check("SuperMutant can spend all 20 AP",
                lowAPResult[0] && lowAP.getAp() == 0);

        int targetHPBefore = lowAPTarget.getHp();

        String failedAttackOutput = captureOutput(
                () -> lowAPResult[0] = lowAP.attack(lowAPTarget)
        );

        check("SuperMutant cannot attack without enough AP",
                !lowAPResult[0]);

        check("Insufficient AP attack produces no message",
                failedAttackOutput.isEmpty());

        check("Failed attack does not damage the target",
                lowAPTarget.getHp() == targetHPBefore);

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
