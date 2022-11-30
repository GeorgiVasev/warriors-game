package org.example.charchters;

public class Vampire extends Warrior {

    private static final int VAMPIRISM = 50;

    public Vampire() {
        super(40);
    }

    @Override
    public int getAttack() {
        return 4;
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        int healthAfter = opponent.getHealth();
        int dealtDamage = healthBefore - healthAfter;
        final int PERCENTS = 100;
        int healingPoints = dealtDamage * VAMPIRISM / PERCENTS;
        healBy(healingPoints);
    }
}
