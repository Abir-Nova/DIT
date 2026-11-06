import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

public class TestMain {

    private static int passed = 0;
    private static int failed = 0;

    // A concrete Monster used only for testing
    static class TestMonster extends Monster {

        public TestMonster(String name, int hp, int ap) {
            super(name, hp, ap);
            this.damage = 25;
            this.apcost = 8;
        }
    }

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

    // Captures printed messages without displaying them
    public static String captureOutput(Runnable action) {

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        return output.toString();
    }

    public static void main(String[] args) {

        System.out.println("===== EXERCISE 04: UNIT AND MONSTER TESTS =====\n");

        // TEST 1: Class structure
        check(
            "Unit is abstract",
            Modifier.isAbstract(Unit.class.getModifiers())
        );

        check(
            "Monster is abstract",
            Modifier.isAbstract(Monster.class.getModifiers())
        );

        check(
            "Unit implements Fighter",
            Fighter.class.isAssignableFrom(Unit.class)
        );

        check(
            "Monster extends Unit",
            Unit.class.isAssignableFrom(Monster.class)
        );

        check(
            "Unit constructor is protected",
            Modifier.isProtected(
                getUnitConstructorModifiers()
            )
        );

        // TEST 2: Create test monsters
        TestMonster monster = new TestMonster("Monster A", 100, 20);
        TestMonster target = new TestMonster("Monster B", 100, 20);

        check("getName() returns the correct name",
            monster.getName().equals("Monster A"));

        check("getHp() returns 100",
            monster.getHp() == 100);

        check("getAp() returns 20",
            monster.getAp() == 20);

        check("getCloseTarget() initially returns null",
            monster.getCloseTarget() == null);

        // TEST 3: Monster attributes
        check("Monster damage is 25",
            monster.getDamage() == 25);

        check("Monster AP cost is 8",
            monster.getApcost() == 8);

        // TEST 4: Damage and HP
        monster.receiveDamage(30);

        check("receiveDamage reduces HP correctly",
            monster.getHp() == 70);

        monster.receiveDamage(100);

        check("HP never goes below zero",
            monster.getHp() == 0);

        // A dead Monster must not receive additional damage
        monster.receiveDamage(10);

        check("Dead Monster HP stays at zero",
            monster.getHp() == 0);

        // TEST 5: Movement
        TestMonster mover = new TestMonster("Mover", 100, 20);
        TestMonster enemy = new TestMonster("Enemy", 100, 20);

        String movementMessage = captureOutput(
            () -> check(
                "First movement succeeds",
                mover.moveCloseTo(enemy)
            )
        );

        check(
            "Movement displays the correct message",
            movementMessage.equals(
                "Mover is moving closer to Enemy."
                + System.lineSeparator()
            )
        );

        check(
            "closeTarget is updated",
            mover.getCloseTarget() == enemy
        );

        check(
            "Cannot move closer to the same target twice",
            !mover.moveCloseTo(enemy)
        );

        check(
            "Cannot move closer to itself",
            !mover.moveCloseTo(mover)
        );

        check(
            "Cannot move closer to null",
            !mover.moveCloseTo(null)
        );

        TestMonster anotherEnemy =
            new TestMonster("Another Enemy", 100, 20);

        check(
            "Can change to another target",
            mover.moveCloseTo(anotherEnemy)
        );

        check(
            "New target is stored correctly",
            mover.getCloseTarget() == anotherEnemy
        );

        // TEST 6: AP recovery
        TestMonster recovering = new TestMonster("Recovering", 100, 20);

        recovering.recoverAP();

        check(
            "Unit recovers 7 AP",
            recovering.getAp() == 27
        );

        TestMonster almostFull = new TestMonster("Almost Full", 100, 48);

        almostFull.recoverAP();

        check(
            "AP never exceeds 50",
            almostFull.getAp() == 50
        );

        TestMonster full = new TestMonster("Full", 100, 50);

        full.recoverAP();

        check(
            "AP stays at 50 when already full",
            full.getAp() == 50
        );

        TestMonster deadRecovering =
            new TestMonster("Dead", 0, 20);

        deadRecovering.recoverAP();

        check(
            "Dead Unit cannot recover AP",
            deadRecovering.getAp() == 20
        );

        // TEST 7: Monster equip
        TestMonster equipping =
            new TestMonster("Equipping", 100, 20);

        String equipMessage = captureOutput(
            () -> check(
                "Monster cannot equip a weapon",
                !equipping.equip(new PlasmaRifle())
            )
        );

        check(
            "Monster displays the correct equip message",
            equipMessage.equals(
                "Monsters are proud and fight with their own bodies."
                + System.lineSeparator()
            )
        );

        // TEST 8: Monster attack - too far away
        TestMonster attacker =
            new TestMonster("Attacker", 100, 20);

        TestMonster victim =
            new TestMonster("Victim", 100, 20);

        String farMessage = captureOutput(
            () -> check(
                "Attack fails when target is too far",
                !attacker.attack(victim)
            )
        );

        check(
            "Correct too-far message is displayed",
            farMessage.equals(
                "Attacker: I'm too far away from Victim."
                + System.lineSeparator()
            )
        );

        check(
            "Failed distant attack does not consume AP",
            attacker.getAp() == 20
        );

        check(
            "Failed distant attack does not cause damage",
            victim.getHp() == 100
        );

        // TEST 9: Successful attack
        attacker.moveCloseTo(victim);

        String attackMessage = captureOutput(
            () -> check(
                "Attack succeeds when in range",
                attacker.attack(victim)
            )
        );

        check(
            "Correct attack message is displayed",
            attackMessage.equals(
                "Attacker attacks Victim."
                + System.lineSeparator()
            )
        );

        check(
            "Successful attack deducts 8 AP",
            attacker.getAp() == 12
        );

        check(
            "Successful attack deals 25 damage",
            victim.getHp() == 75
        );

        // TEST 10: Insufficient AP
        TestMonster lowAp =
            new TestMonster("Low AP", 100, 7);

        TestMonster lowApTarget =
            new TestMonster("Target", 100, 20);

        lowAp.moveCloseTo(lowApTarget);

        String lowApMessage = captureOutput(
            () -> check(
                "Attack fails with insufficient AP",
                !lowAp.attack(lowApTarget)
            )
        );

        check(
            "Insufficient AP attack produces no message",
            lowApMessage.isEmpty()
        );

        check(
            "Insufficient AP is not deducted",
            lowAp.getAp() == 7
        );

        check(
            "Insufficient AP attack causes no damage",
            lowApTarget.getHp() == 100
        );

        // TEST 11: Dead Monster cannot attack
        TestMonster deadAttacker =
            new TestMonster("Dead Attacker", 0, 20);

        TestMonster livingTarget =
            new TestMonster("Living Target", 100, 20);

        deadAttacker.moveCloseTo(livingTarget);

        check(
            "Dead Monster cannot attack",
            !deadAttacker.attack(livingTarget)
        );

        check(
            "Dead Monster cannot equip",
            !deadAttacker.equip(new PowerFist())
        );

        check(
            "Dead Monster cannot move",
            !deadAttacker.moveCloseTo(livingTarget)
        );

        check(
            "Dead Monster cannot recover AP",
            deadAttacker.getAp() == 20
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

    // Retrieves the Unit constructor modifiers for reflection testing
    private static int getUnitConstructorModifiers() {
        try {
            return Unit.class.getDeclaredConstructor(
                String.class,
                int.class,
                int.class
            ).getModifiers();
        } catch (NoSuchMethodException e) {
            return 0;
        }
    }
}