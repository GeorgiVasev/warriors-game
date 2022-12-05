package org.example.charchters;

import weapons.Weapon;

import java.util.ArrayList;

public class Warlord extends Warrior {
    private static int idSequence;
    private final int id = ++idSequence;

    private int attack = 4;
    private int defense = 2;

    public Warlord() {
        super(100);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void receiveDamage(int attack) {
        super.receiveDamage(Math.max(0, attack - getDefense()));
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void equipWeapons(Weapon... weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(getHealth() + w.getHealth());
                defense += w.getDefense();
                attack += w.getAttack();
            }
        }
    }

    public Iterable<IWarrior> moveUnits(Iterable<IWarrior> army) {
        var list = new ArrayList<IWarrior>();
        for (var warrior : army) {
            if (warrior != this)
                list.add(warrior);
        }
        var lancers = list.stream()
                .filter(el -> el instanceof Lancer).toList();
        var healers = list.stream()
                .filter(el -> el instanceof Healer).toList();
        var fighters = list.stream()
                .filter(el -> !(el instanceof Lancer || el instanceof Healer)).toList();
        var res = new ArrayList<IWarrior>();

        lancers.stream().limit(1).findFirst().ifPresent(res::add);
        lancers.stream().limit(1).findFirst().ifPresent(el-> lancers.remove(el));

        if (!healers.isEmpty()) res.addAll(healers);
        if (!lancers.isEmpty()) res.addAll(lancers);
        res.addAll(fighters);

        res.add(this);
        return res;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" +getHealth() + "}";
    }
}
