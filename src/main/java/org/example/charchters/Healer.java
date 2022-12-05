package org.example.charchters;

import org.example.Armies.Army;
import org.example.commands.CanProcessCommand;
import org.example.commands.ChampionHitCommand;
import org.example.commands.Command;
import lombok.extern.slf4j.Slf4j;
import weapons.Weapon;


@Slf4j
public class Healer
        extends Warrior
        implements IWarrior, CanProcessCommand {
    private static int idSequence;
    private final int id = ++idSequence;

    private int healPower = 2;

    public Healer() {
        super(60);
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public void processCommand(Command command, Army.WarriorInArmy sender) {
        if (command instanceof ChampionHitCommand) {
            heal(sender);
        }
    }

    public void heal(IWarrior patient) {
        if (patient instanceof Army.WarriorInArmy p) {
            p.unwrap(patient).healBy(getHealPower());
        }
    }

    @Override
    public void hit(IWarrior opponent) {
        // do nothing!
    }

    @Override
    public void equipWeapons(Weapon... weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                healPower += w.getHealPower();
            }
        }
    }

    public int getHealPower() {
        return healPower;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" + getHealth() + "}";
    }
}
