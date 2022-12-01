package org.example.charchters;

public class Warrior implements IWarrior {
    private static int idSequence;
    private final int id = ++idSequence;

    private static int ATTACK = 5;
    private int health;
    private final int initialHealth;

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
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    protected void healBy(int healPoint) {
        setHealth(getHealth() + healPoint);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "#%02d".formatted(id) +
                "{hp=" +getHealth() + "}";
    }
}
