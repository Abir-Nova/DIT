package Decorator;

public class Example {

    public static void main(String[] args) {
        Warrior albert = new BasicWarrior();
        System.out.println("Albert has " + albert.getHp() + " health points.");
        albert = new Shield(albert);
        System.out.println("Albert has " + albert.getHp() + " health points.");
        Warrior georges = new KingWarrior();
        System.out.println("Georges has " + georges.getHp() + " health points and can hit "
                + georges.getDmg() + " damages.");
        georges = new FireSword(georges);
        georges = new Shield(georges);
        System.out.println("Georges has " + georges.getHp() + " health points.");
        System.out.println("Georges can hit " + georges.getDmg() + " damages.");
    }

}
