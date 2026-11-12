import java.lang.reflect.Method;
// Small test runner: it runs the methods marked with @Test
public class TestRunner {
    // Runs every enabled @Test method of the given class
    public static void runTests(Class<?> testClass) {
        Object instance;
        try {
            // One instance of the class is used to call all the test methods
            instance = testClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            // The class cannot be created: nothing can be run
            e.printStackTrace();
            return;
        }
        // Look at every method declared by the class
        for (Method method : testClass.getDeclaredMethods()) {
            // Read the @Test annotation (null if the method does not have it)
            Test test = method.getAnnotation(Test.class);
            // Only run the methods that have @Test with enabled = true
            if (test != null && test.enabled()) {
                // Display the name of the test, then run it
                System.out.println(test.name());
                invoke(method, instance);
            }
        }
    }
    // Calls a method and shows the error if the call fails
    private static void invoke(Method method, Object instance) {
        try {
            method.invoke(instance);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
