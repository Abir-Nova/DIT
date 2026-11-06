import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        ArrayList result = Ex05.myGetArgs("Hello", "World", "42");
        for (Object o : result) {
            System.out.println(o);
        }

        ArrayList single = Ex05.myGetArgs("only one");
        System.out.println(single);

        ArrayList empty = Ex05.myGetArgs();
        System.out.println(empty);
        System.out.println(empty.size());
    }
}
