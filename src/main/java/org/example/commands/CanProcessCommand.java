package org.example.commands;

import org.example.Armies.Army;

public interface CanProcessCommand {
    default void processCommand(Command command, Army.WarriorInArmy sender) {
    }
}
