// Fighter: an INTERFACE = a contract
// Every class that "implements Fighter" must provide all these methods
// Methods of an interface are public and abstract automatically

public interface Fighter {

    // "boolean" (primitive) is not the same as "Boolean" (object) , the primitive is required here
    boolean equip(Weapon weapon);

    boolean attack(Fighter fighter);

    void receiveDamage(int damage);

    boolean moveCloseTo(Fighter fighter);

    void recoverAP();

    String getName();

    int getAp();

    int getHp();
}
