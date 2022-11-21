package charchters;

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
    public void hit(Warrior opponent) {
        var healthBefore = opponent.getHealth();
        super.hit(opponent);
        var healthAfter = opponent.getHealth();
        var dealtDamage = healthBefore - healthAfter;
        final int PERCENTS = 100;
        var healingPoints = dealtDamage * VAMPIRISM / PERCENTS;
        healBy(healingPoints);
    }

}
