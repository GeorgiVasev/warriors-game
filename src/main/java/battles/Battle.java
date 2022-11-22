package battles;


import Armies.Army;
import charchters.Warrior;


public class Battle {

    private Battle() {
    }


    public static boolean fight(Warrior warriorOne, Warrior warriorTwo) {
        while (warriorOne.isAlive() && warriorTwo.isAlive()) {
            warriorOne.hit(warriorTwo);

            if (!warriorTwo.isAlive())
                return warriorOne.isAlive();

            warriorTwo.hit(warriorOne);
        }
        return warriorOne.isAlive();
    }

    public static boolean fight(Army blueArmy, Army redArmy) {

        var it1 = blueArmy.iterator();
        var it2 = redArmy.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            Warrior blueFirst = it1.next();
            Warrior redFirst = it2.next();
            blueFirst.setNextWarrior(it1.next());
            redFirst.setNextWarrior(it2.next());

            fight(blueFirst, redFirst);
        }
        return it1.hasNext();
    }
}
