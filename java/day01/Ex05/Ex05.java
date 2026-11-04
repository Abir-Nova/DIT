import java.util.ArrayList;

public class Ex05 {
    public static ArrayList myGetArgs(String... var) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : var) {
            list.add(s);
        }
        return list;
    }
}