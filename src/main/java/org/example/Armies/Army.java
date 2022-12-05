package org.example.Armies;


import org.example.charchters.*;
import org.example.commands.CanProcessCommand;
import org.example.commands.ChampionHitCommand;
import org.example.commands.Command;
import weapons.Weapon;


import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<IWarrior> {

    private static int idSequence = 0;
    private final int id = ++idSequence;

    private WarriorInArmy head;
    private WarriorInArmy tail;
    private Warlord warlord;
    private King king;

    private List<IWarrior> troops = new ArrayList<>();

    public Army() {
        // default constructor
    }

    public void moveUnits() {
        if (warlord != null) {
            var newArrangement = warlord.moveUnits(this);
            head = null;
            tail = null;

            while (newArrangement.iterator().hasNext()) {
                addUnit(newArrangement.iterator().next());
            }
        }
    }

    private void addUnit(IWarrior warrior) {
        if (warrior instanceof King && king != null) {
            return;
        } else if (warrior instanceof King currWarrior) {
            king = currWarrior;
        }

        WarriorInArmy wrapped = new WarriorInArmy(warrior);
        if (head == null) {
            head = wrapped;
        } else {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
        troops.add(tail);
    }

    public Army addUnit(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnit(factory.get());
        }
        return this;
    }

    public static class WarriorInArmy implements IWarrior, HasWarriorBehind, CanProcessCommand {

        IWarrior warrior;
        WarriorInArmy nextWarrior;

        public WarriorInArmy(IWarrior warrior) {
            this.warrior = warrior;
        }

        @Override
        public void hit(IWarrior opponent) {
            warrior.hit(opponent);
            processCommand(ChampionHitCommand.INSTANCE, this);
        }

        public Warrior unwrap(IWarrior patient) {
            return (Warrior) warrior;
        }

        @Override
        public void processCommand(Command command, WarriorInArmy sender) {
            if (warrior instanceof CanProcessCommand processor) {
                processor.processCommand(command, sender);
            }
            if (nextWarrior != null) {
                nextWarrior.processCommand(command, this);
            }
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public void receiveDamage(int attack) {
            warrior.receiveDamage(attack);
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return IWarrior.super.isAlive();
        }

        @Override
        public void equipWeapons(Weapon... weapons) {
            warrior.equipWeapons(weapons);
        }

        @Override
        public WarriorInArmy getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(WarriorInArmy nextWarrior) {
            this.nextWarrior = nextWarrior;
        }

        public IWarrior getWarrior() {
            return this.warrior;
        }

        @Override
        public String toString() {
            return getWarrior().toString();
        }
    }

    @Override
    public Iterator<IWarrior> iterator() {
        return new ArmyIterator();
    }

    class ArmyIterator implements Iterator<IWarrior> {
        WarriorInArmy cursor = head;

        @Override
        public boolean hasNext() {
            while (cursor != null && !cursor.isAlive()) {
                cursor = cursor.getWarriorBehind();
            }
            return cursor != null;
        }

        @Override
        public IWarrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            WarriorInArmy res = cursor;
            cursor = cursor.getWarriorBehind();
            return res.unwrap(cursor);
        }
    }

    public Iterator<IWarrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<IWarrior> {

        WarriorInArmy champion = head;

        @Override
        public boolean hasNext() {
            while (champion != null) {
                if (champion.isAlive()) return true;
                champion = champion.getWarriorBehind();
            }
            return false;
        }

        @Override
        public IWarrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return champion;
        }
    }

    public void equipWarriorAtPosition(int position, Weapon... weapon) {
        if (troops.get(position) != null) {
            troops.get(position).equipWeapons(weapon);
        }
    }

    public Warrior unwrap(IWarrior war) {
        return (Warrior) war;
    }

    public List<IWarrior> getTroops() {
        return troops;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "Army#%02d[%n".formatted(id), "\n]");
        sj.setEmptyValue("Army#%02d[ EMPTY ]".formatted(id));
        for (var warrior : this) {
            sj.add(warrior.toString());
        }
        return sj.toString();
    }
}
