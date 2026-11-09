
public class Example {

    public static void main(String[] args) {
        Warrior warrior = new Warrior("Jean-Luc");
        Mage mage = new Mage("Robert");
        
        warrior.moveRight();
        warrior.moveLeft();
        warrior.moveBack();
        warrior.moveForward();
        
        mage.moveRight();
        mage.moveLeft();
        mage.moveBack();
        mage.moveForward();
    }
}
