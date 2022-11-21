package charchters;

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
    public void hit(Warrior opponent) {
        super.hit(opponent);
        final int PERCENTS = 100;
        var lanceDamage = getAttack() * RANGE_DAMAGE / PERCENTS;

        if (opponent.getNextWarrior() != null)
            opponent.getNextWarrior().receiveDamage(lanceDamage);
    }
}
