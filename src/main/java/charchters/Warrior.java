package charchters;

public class Warrior {

    private int attack;
    private int health;

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

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
