package org.example.charchters;


import weapons.Weapon;

public class Lancer extends Warrior {
    private static int idSequence;
    private final int id = ++idSequence;

    private int rangeDamage = 50;
    private int attack = 6;

    public Lancer() {
        super(50);
    }


    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);

        if (opponent instanceof HasWarriorBehind opponentWithNext) {
            IWarrior nextWarrior = opponentWithNext.getWarriorBehind();
            if (nextWarrior != null) {
                int healthAfter = opponent.getHealth();
                int dealtDamage = healthBefore - healthAfter;
                final int PERCENTS = 100;
                var lanceDamage = dealtDamage * rangeDamage / PERCENTS;
                nextWarrior.receiveDamage(lanceDamage);
            }
        }
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
                rangeDamage += w.getRangeDamage();
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" +getHealth() + "}" +
                "{A=" + getAttack() + "}";
    }
}
