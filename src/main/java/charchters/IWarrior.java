package charchters;

interface Command {
}

enum ChampionHitCommand implements Command {
    INSTANCE
}

interface CanProcessCommand {
    default void processCommand(Command command, IWarrior sender) {
    }
}

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

}
