package org.example.charchters;

import lombok.extern.slf4j.Slf4j;
import weapons.Weapon;

@Slf4j
public class Warrior implements IWarrior {
    private static int idSequence;
    private final int id = ++idSequence;

    private int attack = 5;
    private int health;
    private int initialHealth;


    public Warrior() {
        this(50);
    }

    protected Warrior(int health) {
        initialHealth = this.health = health;
    }

    public void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    protected void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    protected void healBy(int healPoint) {
        setHealth(getHealth() + healPoint);
    }

    public void equipWeapons(Weapon... weapons) {
        if(isAlive()) {
            for (Weapon w : weapons) {
                setInitialHealth(getHealth() + w.getHealth());
                setHealth(health + w.getHealth());
                attack += w.getAttack();
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" + getHealth() + "}" +
                "{A=" + getAttack() + "}";
    }
}
