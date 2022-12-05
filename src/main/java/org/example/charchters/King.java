package org.example.charchters;

import weapons.Weapon;

public class King extends Warrior {
    private static int idSequence;
    private final int id = ++idSequence;

    private int attack = 5;
    private int vampirism = 0;
    private int defense = 0;
    private int rangeDamage = 0;

    public King() {
        super(100);
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

        if (opponent instanceof HasWarriorBehind opponentWithNext) {
            IWarrior nextWarrior = opponentWithNext.getWarriorBehind();
            if (nextWarrior != null) {
                var lanceDamage = dealtDamage * rangeDamage / PERCENTS;
                nextWarrior.receiveDamage(lanceDamage);
            }
        }
    }
    @Override
    public void receiveDamage(int attack) {
        super.receiveDamage(Math.max(0, attack - getDefense()));
    }

    @Override
    public void equipWeapons(Weapon... weapons) {
        if (isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                attack += w.getAttack();
                vampirism += w.getVampirism();
                defense += w.getDefense();
                rangeDamage += w.getRangeDamage();
            }
        }
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getVampirism() {
        return vampirism;
    }
    public int getRangeDamage() {
        return rangeDamage;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" +getHealth() + "}" +
                "{A=" + getAttack() + "}";
    }
}
