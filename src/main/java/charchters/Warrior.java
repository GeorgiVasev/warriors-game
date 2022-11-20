package charchters;

public class Warrior {

    protected int attack;
    protected int health;

    public Warrior() {
        this.attack = 5;
        this.health = 50;
    }

    protected Warrior(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void getsHit(Warrior warrior) {
        health -= warrior.getAttack();
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }
}
