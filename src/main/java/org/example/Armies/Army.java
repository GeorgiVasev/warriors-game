package org.example.Armies;


import org.example.charchters.HasWarriorBehind;
import org.example.charchters.IWarrior;
import org.example.charchters.Warrior;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class Army implements Iterable<IWarrior> {

    private static int idSequence = 0;
    private final int id = ++idSequence;

    private WarriorInArmy head;
    private WarriorInArmy tail;

    public Army() {
        // default constructor
    }

    private void addUnit(IWarrior warrior) {
        WarriorInArmy wrapped = new WarriorInArmy(warrior);
        if (head == null) {
            head = wrapped;
        } else {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
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
        public WarriorInArmy getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(WarriorInArmy nextWarrior) {
            this.nextWarrior = nextWarrior;
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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "Army#%02d[%n ".formatted(id), "\n]");
        sj.setEmptyValue("Army#%02d[ EMPTY ]".formatted(id));
        for (var warrior : this) {
            sj.add(warrior.toString());
        }
        return sj.toString();
    }
}
