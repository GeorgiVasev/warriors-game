package battles;

import charchters.Warrior;

public class Battle {

    public static boolean fight(Warrior warriorOne, Warrior warriorTwo) {
        while (warriorOne.isAlive() && warriorTwo.isAlive()) {
            warriorTwo.setHealth(warriorTwo.getHealth() - warriorOne.getAttack());

            if (!warriorTwo.isAlive()) return warriorOne.isAlive();

            warriorOne.setHealth(warriorOne.getHealth() - warriorTwo.getAttack());
        }

        return warriorOne.isAlive();
    }
}
