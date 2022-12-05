package org.example.battles;


import lombok.extern.slf4j.Slf4j;
import org.example.Armies.Army;
import org.example.charchters.IWarrior;


@Slf4j
public class Battle {

    private Battle() {
    }

    public static boolean fight(IWarrior warriorOne, IWarrior warriorTwo) {
        log.atDebug().log("START FIGHT!");
        log.atDebug().log("ATTACKER: {}", warriorOne);
        log.atDebug().log("DEFENDER: {}", warriorTwo);
        while (warriorOne.isAlive() && warriorTwo.isAlive()) {
            warriorOne.hit(warriorTwo);
            log.atDebug().log("DEFENDER: {}",warriorOne, warriorTwo.getHealth(),warriorOne.getAttack());

            if (!warriorTwo.isAlive()) {
                log.atDebug().log("WON THE FIGHT: {}",warriorOne);
                return warriorOne.isAlive();
            }
            warriorTwo.hit(warriorOne);
            log.atDebug().log("ATTACKER: {}", warriorTwo,warriorTwo.getHealth(),warriorTwo.getAttack());
        }
        log.atDebug().log("WON THE FIGHT: {}",warriorTwo);
        return warriorOne.isAlive();
    }

    public static boolean fight(Army blueArmy, Army redArmy) {

        var it1 = blueArmy.firstAliveIterator();
        var it2 = redArmy.firstAliveIterator();
        log.atDebug().log("Blue Army before the battle: \n{}", blueArmy);
        log.atDebug().log("Red Army before the battle: \n{}", redArmy);

        while (it1.hasNext() && it2.hasNext()) {

            IWarrior blueFirst = it1.next();
            IWarrior redFirst = it2.next();

            fight(blueFirst, redFirst);

            log.atDebug().log("Blue Army after fight: \n{}", blueArmy);
            log.atDebug().log("Red Army after fight: \n{}", redArmy);
        }

        log.atDebug().log(() -> (it1.hasNext() ? "BLUE" : "RED") + " ARMY WON");
        log.atDebug().log("Blue Army after the battle: \n{}", blueArmy);
        log.atDebug().log("Red Army after the battle: \n{}", redArmy);
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
