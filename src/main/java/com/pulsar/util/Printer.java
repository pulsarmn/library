package com.pulsar.util;

public final class Printer {

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
        System.out.println(ColorCode.BOLD_RED + message + ColorCode.RESET);
    }

    public static void success(String message) {
        System.out.println(ColorCode.BOLD_GREEN + message + ColorCode.RESET);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    private enum ColorCode {
        BOLD_RED("\033[1;31m"),
        BOLD_GREEN("\033[1;32m"),
        RESET("\033[0m");

        private final String value;

        ColorCode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
