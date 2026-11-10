
import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String args[]) {
        List<Mage> mages = new ArrayList<>();
        mages.add(new Mage("Merlin"));
        mages.add(new Mage("Mandrake"));
        List<Warrior> warriors = new ArrayList<>();
        warriors.add(new Warrior("Spartacus"));
        warriors.add(new Warrior("Clovis"));
        Battalion battalion = new Battalion();
        battalion.add(mages);
        battalion.add(warriors);
        battalion.display();
    }
}
