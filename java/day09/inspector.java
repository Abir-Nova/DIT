
import java.lang.reflect.Field;
import java.lang.reflect.Method;
// Inspects a class at runtime with reflection

public class Inspector<T> {

    // The class to inspect (Class is generic, so it is stored as Class<T>)
    private Class<T> inspectedClass;

    // Constructor: takes the class to inspect
    public Inspector(Class<T> inspectedClass) {
        this.inspectedClass = inspectedClass;
    }

    // Displays some information about the inspected class
    public void displayInformations() {
        System.out.println("Information of the \"" + inspectedClass.getName() + "\" class:");
        // Parent class (null for Object, interfaces and primitive types)
        Class<? super T> superclass = inspectedClass.getSuperclass();
        System.out.println("Superclass: " + (superclass == null ? "null" : superclass.getName()));
        // Only the methods declared by the class itself, not the inherited ones
        Method[] methods = inspectedClass.getDeclaredMethods();
        System.out.println(methods.length + " methods:");
        for (Method method : methods) {
            System.out.println("- " + method.getName());
        }
        // Only the fields declared by the class itself, not the inherited ones
        Field[] fields = inspectedClass.getDeclaredFields();
        System.out.println(fields.length + " fields:");
        for (Field field : fields) {
            System.out.println("- " + field.getName());
        }
    }
}
