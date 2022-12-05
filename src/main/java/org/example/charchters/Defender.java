package org.example.charchters;

import weapons.Weapon;

public class Defender extends Warrior {
    private static int idSequence;
    private final int id = ++idSequence;
    private int defense = 2;
    private int attack = 3;

    public Defender() {
        super(60);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void receiveDamage(int attack) {
        super.receiveDamage(Math.max(0, attack - getDefense()));
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void equipWeapons(Weapon... weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                defense += w.getDefense();
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
