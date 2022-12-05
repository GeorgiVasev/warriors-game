package org.example.charchters;


import weapons.Weapon;

public interface IWarrior {
    default void hit(IWarrior opponent) {
        opponent.receiveDamage(getAttack());
    }

    int getAttack();

    void receiveDamage(int attack);

    int getHealth();

    default boolean isAlive() {
        return getHealth() > 0;
    }

    void equipWeapons(Weapon...weapons);
}
