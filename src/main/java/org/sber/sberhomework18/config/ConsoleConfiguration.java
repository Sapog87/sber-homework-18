package org.sber.sberhomework18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConsoleConfiguration {
    @Bean
    public Scanner consoleScanner() {
        return new Scanner(System.in);
    }
}
