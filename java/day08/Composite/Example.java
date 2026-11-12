package Composite;
import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) {
        Word w1 = new Word("word1");
        Word w2 = new Word("word2");
        Word w3 = new Word("word3");
        Word w4 = new Word("word4");
        SentenceComposite sc1 = new SentenceComposite();
        SentenceComposite sc2 = new SentenceComposite();
        SentenceComposite sc3 = new SentenceComposite();
        sc1.add(w1);
        sc1.add(w2);
        sc1.add(w3);
        sc2.add(w4);
        sc3.add(sc2);
        sc3.add(sc1);
        sc3.print();
    }
}
