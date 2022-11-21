package battles;

import charchters.Warrior;

public class Rookie extends Warrior {

    public Rookie() {
        super(50);
    }

    @Override
    public int getAttack() {
        return 1;
    }
}
