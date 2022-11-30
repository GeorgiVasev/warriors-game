package org.example.charchters;

public class Lancer extends Warrior {
    private static final int RANGE_DAMAGE = 50;

    public Lancer() {
        super(50);
    }

    @Override
    public int getAttack() {
        return 6;
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
                var lanceDamage = dealtDamage * RANGE_DAMAGE / PERCENTS;
                nextWarrior.receiveDamage(lanceDamage);
            }
        }
    }
}
