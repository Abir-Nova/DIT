import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// A small child class used only for testing Weapon
class TestWeapon extends Weapon {

    public TestWeapon(String name, int apcost, int damage, boolean melee) {
        super(name, apcost, damage, melee);
    }

    @Override
    public void attack() {
        System.out.println("TEST ATTACK");
    }
}

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

    public static void main(String[] args) throws Exception {

        System.out.println("===== EXERCISE 01: WEAPON TESTS =====\n");

        // TEST 1: Weapon must be abstract
        check(
            "Weapon is an abstract class",
            Modifier.isAbstract(Weapon.class.getModifiers())
        );

        // TEST 2: Check attributes and their types
        String[] fieldNames = {"name", "apcost", "damage", "melee"};
        Class<?>[] fieldTypes = {
            String.class,
            int.class,
            int.class,
            boolean.class
        };

        for (int i = 0; i < fieldNames.length; i++) {

            Field field = Weapon.class.getDeclaredField(fieldNames[i]);

            check(
                fieldNames[i] + " has the correct type",
                field.getType() == fieldTypes[i]
            );

            check(
                fieldNames[i] + " is protected",
                Modifier.isProtected(field.getModifiers())
            );
        }

        // TEST 3: Constructor must be protected
        Constructor<?> constructor = Weapon.class.getDeclaredConstructor(
            String.class,
            int.class,
            int.class,
            boolean.class
        );

        check(
            "Constructor is protected",
            Modifier.isProtected(constructor.getModifiers())
        );

        check(
            "Constructor is not public",
            !Modifier.isPublic(constructor.getModifiers())
        );

        // TEST 4: Create a child object and test constructor values
        TestWeapon weapon = new TestWeapon(
            "Test Weapon",
            5,
            21,
            false
        );

        check(
            "getName() returns the correct name",
            weapon.getName().equals("Test Weapon")
        );

        check(
            "getApcost() returns the correct AP cost",
            weapon.getApcost() == 5
        );

        check(
            "getDamage() returns the correct damage",
            weapon.getDamage() == 21
        );

        check(
            "isMelee() returns the correct boolean",
            !weapon.isMelee()
        );

        // TEST 5: Check that all getters are public
        String[] getterNames = {
            "getName",
            "getApcost",
            "getDamage",
            "isMelee"
        };

        for (String getterName : getterNames) {

            Method method = Weapon.class.getDeclaredMethod(getterName);

            check(
                getterName + "() is public",
                Modifier.isPublic(method.getModifiers())
            );
        }

        // TEST 6: Check that no setters exist
        boolean hasSetter = false;

        for (Method method : Weapon.class.getDeclaredMethods()) {
            if (method.getName().startsWith("set")
                    && method.getParameterCount() == 1) {
                hasSetter = true;
            }
        }

        check("Weapon has no setters", !hasSetter);

        // TEST 7: Check attack() method
        Method attack = Weapon.class.getDeclaredMethod("attack");

        check(
            "attack() is public",
            Modifier.isPublic(attack.getModifiers())
        );

        check(
            "attack() is abstract",
            Modifier.isAbstract(attack.getModifiers())
        );

        check(
            "attack() returns void",
            attack.getReturnType() == void.class
        );

        check(
            "attack() has no parameters",
            attack.getParameterCount() == 0
        );

        // TEST 8: Child class implements attack()
        System.out.println("\nTesting inherited attack():");
        weapon.attack();

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