
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// Annotation that marks a method as a test
// It is kept at runtime so the TestRunner can read it with reflection

@Retention(RetentionPolicy.RUNTIME)
// It can only be put on methods
@Target(ElementType.METHOD)
public @interface Test {

    // Name of the test
    String name();

    // Tells if the test is enabled (true by default)
    boolean enabled() default true;
}
