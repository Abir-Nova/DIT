package Composite;
import java.util.ArrayList;
import java.util.List;
// A sentence made of other sentences (words or other composites)
public class SentenceComposite implements Sentence {
    // The children of this composite
    private List<Sentence> childSentence = new ArrayList<>();
    // Calls print on every child, in the order they were added
    @Override
    public void print() {
        for (Sentence sentence : childSentence) {
            sentence.print();
        }
    }
    // Adds a child
    public void add(Sentence sentence) {
        childSentence.add(sentence);
    }
    // Removes a child
    public void remove(Sentence sentence) {
        childSentence.remove(sentence);
    }
}
