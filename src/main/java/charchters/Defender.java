package charchters;

public class Defender extends Warrior {

    private static final int DEFENSE = 2;

    public Defender() {
        super(60);
    }

    @Override
    public int getAttack() {
        return 3;
    }

    @Override
    public void receiveDamage(int attack) {
        super.receiveDamage(Math.max(0, attack - getDefense()));
    }

    public int getDefense() {
        return DEFENSE;
    }
}
