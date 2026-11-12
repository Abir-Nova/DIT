
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
// Small test runner: it runs the methods marked with @Test, with set up and clean up methods

public class TestRunner {

    // Runs every enabled @Test method of the given class
    public static void runTests(Class<?> testClass) {
        Object instance;
        try {
            // One instance of the class is used to call all the methods
            instance = testClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            // The class cannot be created: nothing can be run
            e.printStackTrace();
            return;
        }
        Method[] methods = testClass.getDeclaredMethods();
        // Once, before any test
        invokeAnnotated(methods, BeforeClass.class, instance);
        for (Method method : methods) {
            // Read the @Test annotation (null if the method does not have it)
            Test test = method.getAnnotation(Test.class);
            // Only run the methods that have @Test with enabled = true
            if (test != null && test.enabled()) {
                // Display the name of the test
                System.out.println(test.name());
                // Before each test
                invokeAnnotated(methods, Before.class, instance);
                // The test itself
                invoke(method, instance);
                // After each test
                invokeAnnotated(methods, After.class, instance);
            }
        }
        // Once, after all the tests
        invokeAnnotated(methods, AfterClass.class, instance);
    }

    // Calls every method that has the given annotation (does nothing if there is none)
    private static void invokeAnnotated(Method[] methods, Class<? extends Annotation> annotation, Object instance) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
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
