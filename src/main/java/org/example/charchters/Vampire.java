package org.example.charchters;

import weapons.Weapon;

public class Vampire extends Warrior {
    private static int idSequence;
    private final int id = ++idSequence;
    private int attack = 4;
    private int vampirism = 50;

    public Vampire() {
        super(40);
    }


    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        int healthAfter = opponent.getHealth();
        int dealtDamage = healthBefore - healthAfter;
        final int PERCENTS = 100;
        int healingPoints = dealtDamage * vampirism / PERCENTS;
        healBy(healingPoints);
    }


    @Override
    public void equipWeapons(Weapon...weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                vampirism += w.getVampirism();
                attack += w.getAttack();
            }
        }
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public int getVampirism() {
        return vampirism;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" + getHealth() + "}" +
                "{A=" + getAttack() + "}";
    }
}
