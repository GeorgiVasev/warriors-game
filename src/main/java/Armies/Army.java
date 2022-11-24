package Armies;

import charchters.HasWarriorBehind;
import charchters.IWarrior;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army implements Iterable<IWarrior> {

    public Army() {
        // default constructor
    }

    private Collection<IWarrior> troops = new ArrayList<>();
    private WarriorInArmy tail;

    static class WarriorInArmy implements IWarrior, HasWarriorBehind {

        IWarrior warrior;
        IWarrior nextWarrior;

        public WarriorInArmy(IWarrior warrior) {
            this.warrior = warrior;
        }

        @Override
        public void hit(IWarrior opponent) {
            warrior.hit(opponent);
        }

        @Override
        public int getAttack() {
            return 0;
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
        public IWarrior getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(IWarrior nextWarrior) {
            this.nextWarrior = nextWarrior;
        }

    }

    private void addUnit(IWarrior warrior) {
        WarriorInArmy wrapped = new WarriorInArmy(warrior);
        if (tail != null) {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
        troops.add(wrapped);
    }

    public Army addUnit(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnit(factory.get());
        }
        return this;
    }

    @Override
    public Iterator<IWarrior> iterator() {
        return new ArmyIterator();
    }

    class ArmyIterator implements Iterator<IWarrior> {

        Iterator<IWarrior> iterator = troops.iterator();
        IWarrior champion;

        @Override
        public boolean hasNext() {
            if (champion != null && champion.isAlive()) {
                return true;
            }
            while (iterator.hasNext()) {
                champion = iterator.next();
                if (champion.isAlive()) return true;
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
}
