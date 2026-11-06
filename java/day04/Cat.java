// Cat: "extends Animal" = a Cat IS an Animal and inherits everything that is not private
public class Cat extends Animal {
    // Private color, getter only (a setter would be animal cruelty!)
    private final String color;
    // Color not given: reuse the other constructor with the default color "grey"
    public Cat(String name) {
        this(name, "grey");
    }
    public Cat(String name, String color) {
        // super(...) calls the Animal constructor: first line, 4 legs, type MAMMAL
        super(name, 4, Type.MAMMAL);
        this.color = color;
        System.out.println(name + ": MEEEOOWWWW");
    }
    public String getColor() {
        return this.color;
    }
    public void meow() {
        System.out.println(this.getName() + " the " + this.color + " kitty is meowing.");
    }
}