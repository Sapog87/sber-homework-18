package org.sber.sberhomework18;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.commands.ConsoleCommand;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static org.sber.sberhomework18.utils.ReadUnit.readInt;

@Component
@RequiredArgsConstructor
public class ConsoleApplicationRunner implements ApplicationRunner {
    private final List<ConsoleCommand> commands;
    private final Scanner scanner;

    @Override
    public void run(ApplicationArguments args) {
        while (true) {
            System.out.println("0 | Выйти");
            for (int i = 0; i < commands.size(); i++) {
                System.out.printf("%d | %s%n", i + 1, commands.get(i).getText());
            }

            int command = readInt(scanner, "Введите номер команды: ");
            if (command == 0) {
                break;
            } else if (command > 0 && command <= commands.size()) {
                commands.get(command - 1).execute();
            } else {
                System.out.println("Неверная команда");
            }
        }
    }
}
