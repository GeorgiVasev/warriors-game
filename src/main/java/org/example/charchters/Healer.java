package org.example.charchters;

import org.example.Armies.CanProcessCommand;
import org.example.Armies.ChampionHitCommand;
import org.example.Armies.Command;
import org.example.Armies.WholeArmy;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Healer
        extends Warrior
        implements IWarrior, CanProcessCommand {

    private static final int HEAL_POWER = 2;

    public Healer() {
        super(60);
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public void processCommand(Command command, WholeArmy.WarriorInArmy sender) {
        if (command instanceof ChampionHitCommand) {
            heal(sender);
        }
    }

    public void heal(IWarrior patient) {
        if (patient instanceof WholeArmy.WarriorInArmy p) {
            p.unwrap(patient).healBy(getHealPower());
        } else {
            unwrapIWarrior(patient).healBy(getHealPower());
        }
    }

    public Warrior unwrapIWarrior(IWarrior patient) {
        return (Warrior) patient;
    }

    @Override
    public void hit(IWarrior opponent) {
        // do nothing!
    }

    public static int getHealPower() {
        return HEAL_POWER;
    }
}