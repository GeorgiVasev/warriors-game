package org.example.charchters;

import weapons.Weapon;


public class Knight extends Warrior {

    private static int idSequence;
    private final int id = ++idSequence;

    private int attack = 7;

    public Knight() {
        super(50);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void equipWeapons(Weapon...weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                attack += w.getAttack();
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" + getHealth() + "}" +
                "{A=" + getAttack() + "}";
    }
}
