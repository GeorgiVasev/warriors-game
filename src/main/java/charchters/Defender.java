package charchters;

public class Defender extends Warrior {

    private static final int DEFENSE = 2;

    public Defender() {
        super(3, 60);
    }

    @Override
    public void receiveDmg(int attack) {
        attack -= getDefense();
        if (attack <= 0) {
            attack = 0;
        }
        super.receiveDmg(attack);
    }

    public int getDefense() {
        return DEFENSE;
    }
}
