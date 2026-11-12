package Factory;
// Abstract base class of every toy made by the factory
public abstract class Toy {
    // Title of the toy (private: it can only be read with getTitle)
    private String title;
    // Constructor used by the child classes to set the title
    public Toy(String title) {
        this.title = title;
    }
    // Returns the title of the toy
    public String getTitle() {
        return title;
    }
}
