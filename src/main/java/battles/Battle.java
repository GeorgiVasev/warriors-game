package battles;


import Armies.Army;
import charchters.Warrior;


public class Battle {

    public static boolean fight(Warrior warriorOne, Warrior warriorTwo) {
        while (warriorOne.isAlive() && warriorTwo.isAlive()) {
            warriorTwo.receiveDmg(warriorOne.getAttack());

            if (!warriorTwo.isAlive())
                return warriorOne.isAlive();

            warriorOne.receiveDmg(warriorTwo.getAttack());
        }
        return warriorOne.isAlive();
    }

    public static boolean fight(Army blueArmy, Army redArmy) {

        var it1 = blueArmy.iterator();
        var it2 = redArmy.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }
}
