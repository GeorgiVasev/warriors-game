package org.example.Armies;


import org.example.charchters.HasWarriorBehind;
import org.example.charchters.IWarrior;
import org.example.charchters.Warrior;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WholeArmy extends Army implements Iterable<IWarrior> {

    public WholeArmy() {
        // default constructor
    }

    private WholeArmy.WarriorInArmy tail;

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
        public void processCommand(Command command, WholeArmy.WarriorInArmy sender) {
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
        public IWarrior getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(WholeArmy.WarriorInArmy nextWarrior) {
            this.nextWarrior = nextWarrior;
        }

    }

    void addUnit(IWarrior warrior) {
        WholeArmy.WarriorInArmy wrapped = new WholeArmy.WarriorInArmy(warrior);
        if (tail != null) {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
        getTroops().add(wrapped);
    }


    @Override
    public Iterator<IWarrior> iterator() {
        return new WholeArmy.ArmyIterator();
    }

    class ArmyIterator implements Iterator<IWarrior> {

        Iterator<IWarrior> iterator = getTroops().iterator();
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
