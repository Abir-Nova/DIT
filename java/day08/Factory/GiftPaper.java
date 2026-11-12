package Factory;
// A gift paper that can wrap one toy
public class GiftPaper {
    // The wrapped toy (null when the paper is empty)
    private Toy gift;
    // Wraps a toy: it acts as the setter of the attribute
    public void wrap(Toy toy) {
        this.gift = toy;
    }
    // Returns the wrapped toy and empties the paper (the attribute is set to null)
    public Toy unwrap() {
        Toy toy = this.gift;
        this.gift = null;
        return toy;
    }
}
