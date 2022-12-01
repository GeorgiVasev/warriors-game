package org.example.Armies;

public interface CanProcessCommand {
    default void processCommand(Command command, Army.WarriorInArmy sender) {
    }
}
