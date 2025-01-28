package org.sber.sberhomework18.utils;

import java.util.Scanner;

public class ReadUnit {
    private ReadUnit() {
    }

    public static long readLong(Scanner scanner, String text) {
        while (true) {
            try {
                System.out.print(text);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static int readInt(Scanner scanner, String text) {
        while (true) {
            try {
                System.out.print(text);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static String readString(Scanner scanner, String text) {
        System.out.print(text);
        String name;
        while ((name = scanner.nextLine()).isBlank()) {
            System.out.print(text);
        }
        return name;
    }
}
