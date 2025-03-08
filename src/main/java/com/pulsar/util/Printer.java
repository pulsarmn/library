package com.pulsar.util;

import java.io.PrintStream;

public final class Printer {

    private static final PrintStream OUTPUT = System.out;

    private Printer() {
    }

    public static void displayMainMenu() {
        OUTPUT.println("1. Посмотреть каталог");
        OUTPUT.println("2. Добавить объект");
        OUTPUT.println("3. Выдать объект");
        OUTPUT.println("4. Вернуть объект");
        OUTPUT.println("0. Выйти");
    }

    public static void inputRequest() {
        System.out.print("> ");
    }

    public static void inputError() {
        displayColoredMessage("Некорректный ввод! Попробуйте ещё раз!", ColorCode.BOLD_RED);
    }

    public static void displayError(String message) {
        displayColoredMessage(message, ColorCode.BOLD_RED);
    }

    public static void displaySuccess(String message) {
        displayColoredMessage(message, ColorCode.BOLD_GREEN);
    }

    public static void displayColoredMessage(String message, ColorCode color) {
        OUTPUT.println(color + message + ColorCode.RESET);
    }

    public static void println(String message) {
        OUTPUT.println(message);
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
