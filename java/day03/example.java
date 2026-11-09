
import planet.*;
import planet.moon.Phobos;

public class Example {

    public static void main(String[] args) {
        planet.Mars titi = new planet.Mars("Here and there");
        planet.Mars toto = new planet.Mars("Up");
        planet.moon.Phobos phobos1 = new planet.moon.Phobos(titi, "Alpha 3");
        planet.moon.Phobos phobos2 = new planet.moon.Phobos(toto, "Beta 1");
        System.out.println(phobos1.getLandingSite());
    }
}
