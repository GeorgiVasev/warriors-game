package Armies;

import charchters.Warrior;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    public Army() {
        // default constructor
    }

    private Collection<Warrior> troops = new ArrayList<>();

    private void addUnit(Warrior warrior) {
        troops.add(warrior);
    }

    public Army addUnit(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnit(factory.get());
        }
        return this;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new ArmyIterator();
    }

    class ArmyIterator implements Iterator<Warrior> {

        Iterator<Warrior> iterator = troops.iterator();
        Warrior champion;

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
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return champion;
        }
    }
}
