package org.example.Armies;


import org.example.charchters.IWarrior;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class Army {

    protected Army() {
        // default constructor
    }

    private Collection<IWarrior> troops = new ArrayList<>();

    abstract void addUnit(IWarrior warrior);

    public Army addUnit(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnit(factory.get());
        }
        return this;
    }

    public Collection<IWarrior> getTroops() {
        return troops;
    }
}
