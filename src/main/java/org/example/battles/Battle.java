package org.example.battles;


import org.example.Armies.StraightArmy;
import org.example.Armies.WholeArmy;
import org.example.charchters.IWarrior;


public class Battle {

    private Battle() {
    }

    public static boolean fight(IWarrior warriorOne, IWarrior warriorTwo) {
        while (warriorOne.isAlive() && warriorTwo.isAlive()) {
            warriorOne.hit(warriorTwo);

            if (!warriorTwo.isAlive())
                return warriorOne.isAlive();

            warriorTwo.hit(warriorOne);
        }
        return warriorOne.isAlive();
    }

    public static boolean fight(WholeArmy blueArmy, WholeArmy redArmy) {

        var it1 = blueArmy.iterator();
        var it2 = redArmy.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            IWarrior blueFirst = it1.next();
            IWarrior redFirst = it2.next();

            fight(blueFirst, redFirst);
        }
        return it1.hasNext();
    }

    public static boolean straightArmyFight(StraightArmy blueArmy, StraightArmy redArmy) {

        while (true) {
            var it1 = blueArmy.iterator();
            var it2 = redArmy.iterator();

            if (!it1.hasNext()) return false;
            if (!it2.hasNext()) return true;

            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }
        }
    }
}
