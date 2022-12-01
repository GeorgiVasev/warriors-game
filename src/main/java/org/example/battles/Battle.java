package org.example.battles;


import lombok.extern.slf4j.Slf4j;
import org.example.Armies.Army;
import org.example.charchters.IWarrior;

import java.util.StringJoiner;

@Slf4j
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

    public static boolean fight(Army blueArmy, Army redArmy) {

        var it1 = blueArmy.firstAliveIterator();
        var it2 = redArmy.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            IWarrior blueFirst = it1.next();
            IWarrior redFirst = it2.next();

            fight(blueFirst, redFirst);
        }
        return it1.hasNext();
    }

    public static boolean straightArmyFight(Army blueArmy, Army redArmy) {

        log.atDebug().log("Blue Army before the battle: \n{}", blueArmy);
        log.atDebug().log("Red Army before the battle: \n{}", redArmy);
        boolean flag;
        int round = 1;
        while (true) {
            var it1 = blueArmy.iterator();
            var it2 = redArmy.iterator();

            if (!it1.hasNext()) {
                flag = false;
                break;
            }

            if (!it2.hasNext()) {
                flag = true;
                break;
            }
            log.atDebug().log("Round {}", round++);

            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }
        }
        log.atDebug().log(() -> (flag ? "First" : "Second") + " army won");
        log.atDebug().log("Blue Army after the battle: \n{}", blueArmy);
        log.atDebug().log("Red Army after the battle: \n{}", redArmy);
        return flag;
    }


}
