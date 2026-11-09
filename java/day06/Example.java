
public class Example {

    public static void main(String[] args) {
        Character perso = new TestCharacter("Jean-Luc");

        System.out.println(perso.getName());
        System.out.println(perso.getLife());
        System.out.println(perso.getAgility());
        System.out.println(perso.getStrength());
        System.out.println(perso.getWit());
        System.out.println(perso.getRPGClass());

        perso.attack("my weapon");
    }
}

// Temporary implementation for testing the abstract Character class
class TestCharacter extends Character {

    public TestCharacter(String name) {
        super(name, "SomethingSetByTestCharacter");
    }
}
