package com.pulsar.util;

public final class Printer {

    private static final String BOLD_RED = "\033[1;31m";
    private static final String BOLD_GREEN = "\033[1;32m";
    private static final String RESET = "\033[0m";

    private Printer() {}

    public static void mainMenu() {
        System.out.println("1. Посмотреть каталог");
        System.out.println("2. Добавить объект");
        System.out.println("3. Выдать объект");
        System.out.println("4. Вернуть объект");
        System.out.println("0. Выйти");
    }
}
