package org.example.Armies;

public interface CanProcessCommand {
    default void processCommand(Command command, WholeArmy.WarriorInArmy sender) {
    }
}
