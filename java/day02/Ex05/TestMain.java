
public class TestMain {
    public static void main(String[] args) {
        Gecko mimi = new Gecko("mimi");
        mimi.hello("Titi");
        mimi.hello(2);

        mimi.hello(2.5);   // double  -> does nothing
        mimi.hello(true);  // boolean -> does nothing
        mimi.hello(0);     // zero times -> nothing
    }
}

// Expected:
//   Hello mimi!$
//   Hello Titi, I'm mimi!$
//   Hello, I'm mimi!$
//   Hello, I'm mimi!$
// then NOTHING for the last three calls.
