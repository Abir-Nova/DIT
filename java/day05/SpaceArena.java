import java.util.ArrayList;
import java.util.List;

// SpaceArena: simulates the fights between a team of monsters and a team of space marines

public class SpaceArena {

    // Registered fighters, in registration order. A fighter that falls is removed from its list
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final ArrayList<SpaceMarine> spaceMarines = new ArrayList<>();

    // Fighters currently in the arena. A winner stays there between two fights (null = nobody inside)
    private Monster currentMonster = null;
    private SpaceMarine currentSpaceMarine = null;

    public void enlistMonsters(List<? extends Monster> newMonsters) {
        if (newMonsters == null) {
            return;
        }
        for (Monster monster : newMonsters) {
            // A Fighter that is already registered (or null) is ignored
            if (monster != null && !this.monsters.contains(monster)) {
                this.monsters.add(monster);
            }
        }
    }

    public void enlistSpaceMarines(List<? extends SpaceMarine> newSpaceMarines) {
        if (newSpaceMarines == null) {
            return;
        }
        for (SpaceMarine spaceMarine : newSpaceMarines) {
            if (spaceMarine != null && !this.spaceMarines.contains(spaceMarine)) {
                this.spaceMarines.add(spaceMarine);
            }
        }
    }

    // Returns true if at least one round was played
    public boolean fight() {
        
        // Remove a current fighter if it died between two calls to fight()
        if (this.currentMonster != null && this.currentMonster.getHp() <= 0) {
            this.monsters.remove(this.currentMonster);
            this.currentMonster = null;
        }

        if (this.currentSpaceMarine != null && this.currentSpaceMarine.getHp() <= 0) {
            this.spaceMarines.remove(this.currentSpaceMarine);
            this.currentSpaceMarine = null;
        }

        if (this.monsters.isEmpty()) {
            System.out.println("No monsters available to fight.");
        }
        if (this.spaceMarines.isEmpty()) {
            System.out.println("Those cowards ran away.");
        }
        if (this.monsters.isEmpty() || this.spaceMarines.isEmpty()) {
            return false;
        }
        boolean roundPlayed = false;
        while (true) {
            // The space marine always enters first
            
            if (this.currentSpaceMarine == null) {
                
                this.currentSpaceMarine = nextAlive(this.spaceMarines);
                
                if (this.currentSpaceMarine == null) {
                    System.out.println("The monsters are victorious.");
                    return roundPlayed;
                }
                
                System.out.println(this.currentSpaceMarine.getName() + " has entered the arena.");
            }

            if (this.currentMonster == null) {
                
                this.currentMonster = nextAlive(this.monsters);
                
                if (this.currentMonster == null) {
                    System.out.println("The spaceMarines are victorious.");
                    return roundPlayed;
                }
                
                System.out.println(this.currentMonster.getName() + " has entered the arena.");
            }
           
            // One round = the space marine plays first, then the monster (if it is still standing)
            
            roundPlayed = true;
            
            this.turn(this.currentSpaceMarine, this.currentMonster);
            
            if (this.currentMonster.getHp() <= 0) {
                // The monster has fallen: the winner recovers ap once, a new monster will enter
                this.currentSpaceMarine.recoverAP();
                this.monsters.remove(this.currentMonster);
                this.currentMonster = null;
                continue;
            }
            
            this.turn(this.currentMonster, this.currentSpaceMarine);
            
            if (this.currentSpaceMarine.getHp() <= 0) {
                // The space marine has fallen: the winner recovers ap once, a new marine will enter
                this.currentMonster.recoverAP();
                this.spaceMarines.remove(this.currentSpaceMarine);
                this.currentSpaceMarine = null;
            }
        }
    }

    // One turn: try to attack; on failure, go closer if possible, otherwise it was a lack of ap: recover
    private void turn(Fighter attacker, Fighter defender) {
        
        if (!attacker.attack(defender)) {
            if (!attacker.moveCloseTo(defender)) {
                attacker.recoverAP();
            }
        }
    }

    // First fighter of the list that is still alive (dead ones met on the way are removed) or null
    
    private <T extends Fighter> T nextAlive(ArrayList<T> fighters) {
        
        while (!fighters.isEmpty()) {
            
            T first = fighters.get(0);
            
            if (first.getHp() > 0) {
                return first;
            }
            
            fighters.remove(0);
        }
        return null;
    }
}