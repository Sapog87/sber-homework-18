package org.sber.sberhomework18.commands;

/**
 * Базовый интерфейс для консольных команд
 */
public interface ConsoleCommand {
    /**
     * @return текст команды
     */
    String getText();

    /**
     * Выполняет команду
     */
    void execute();
}
