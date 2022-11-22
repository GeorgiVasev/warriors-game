package charchters;

public class Warrior {

    private static int ATTACK = 5;
    private int health;
    private final int initialHealth;

    private Warrior nextWarrior;

    public void setNextWarrior(Warrior nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    public Warrior getNextWarrior() {
        return nextWarrior;
    }

    public Warrior() {
        this(50);
    }

    protected Warrior(int health) {
        initialHealth = this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hit(Warrior opponent) {
        opponent.receiveDamage(getAttack());
    }

    void receiveDamage(int attack) {
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
}
