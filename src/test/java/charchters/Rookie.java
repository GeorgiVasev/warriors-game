package charchters;

import org.example.charchters.Warrior;

public class Rookie extends Warrior {

    public Rookie() {
        super(30);
    }

    @Override
    public int getAttack() {
        return 1;
    }
}
