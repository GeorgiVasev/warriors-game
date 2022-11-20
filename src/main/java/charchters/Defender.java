package charchters;

public class Defender extends Warrior {

    private static final int DEFENSE = 2;

    public Defender() {
        super(3, 60);
    }

    @Override
    public void getsHit(Warrior warrior) {
        int attack = warrior.getAttack() - getDefense();
        if (attack <= 0) {
            attack = 0;
        }
        setHealth(getHealth() - attack);
    }

    public int getDefense() {
        return DEFENSE;
    }
}
