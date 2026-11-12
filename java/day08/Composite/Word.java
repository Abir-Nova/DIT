package Composite;
// A word is the simplest sentence (a leaf of the composite)
public class Word implements Sentence {
    // The text given to the constructor
    private String word;
    // Constructor: stores the text of the word
    public Word(String word) {
        this.word = word;
    }
    // Displays the text of the word
    @Override
    public void print() {
        System.out.println(word);
    }
}
