package com.pulsar.util;

public final class Printer {

    private static final String BOLD_RED = "\033[1;31m";
    private static final String BOLD_GREEN = "\033[1;32m";
    private static final String RESET = "\033[0m";

    private Printer() {
    }

    public static void mainMenu() {
        System.out.println("1. Посмотреть каталог");
        System.out.println("2. Добавить объект");
        System.out.println("3. Выдать объект");
        System.out.println("4. Вернуть объект");
        System.out.println("0. Выйти");
    }

    public static void inputRequest() {
        System.out.print("> ");
    }

    public static void inputError() {
        String message = "Некорректный ввод! Попробуйте ещё раз!";
        error(message);
    }

    public static void error(String message) {
        System.out.println(BOLD_RED + message + RESET);
    }

    public static void success(String message) {
        System.out.println(BOLD_GREEN + message + RESET);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
