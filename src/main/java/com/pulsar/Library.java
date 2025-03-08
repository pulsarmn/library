package com.pulsar;

import com.pulsar.service.LibraryService;
import com.pulsar.util.Printer;

import java.util.Scanner;

public class Library {

    private final Scanner terminal;
    private final LibraryService libraryService;
    private boolean isRunning = false;

    private static final String PRINT_CATALOG = "1";
    private static final String ADD_ITEM = "2";
    private static final String TAKE_ITEM = "3";
    private static final String RETURN_ITEM = "4";
    private static final String EXIT = "0";

    public Library() {
        this(new LibraryService());
    }

    public Library(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.terminal = new Scanner(System.in);
    }

    public void start() {
        isRunning = true;

        while (isRunning) {
            Printer.displayMainMenu();
            Printer.inputRequest();
            processCommand(terminal.nextLine());
        }
    }

    private void processCommand(String command) {
        switch (command) {
            case PRINT_CATALOG -> libraryService.printCatalog();
            case ADD_ITEM -> addItem();
            case TAKE_ITEM -> takeItem();
            case RETURN_ITEM -> returnItem();
            case EXIT -> shutdown();
            default -> Printer.inputError();
        }
    }

    private void addItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        Printer.println("Введите кол-во копий:");
        Printer.inputRequest();
        int copies = terminal.nextInt();

        try {
            libraryService.registerNewItem(title, author, copies);
        } catch (Exception e) {
            Printer.displayError(e.getMessage());
            terminal.nextLine();
        }
    }

    private void takeItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        try {
            libraryService.takeItem(title, author);
        } catch (Exception e) {
            Printer.displayError(e.getMessage());
        }
    }

    private void returnItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        try {
            libraryService.returnItem(title, author);
        } catch (Exception e) {
            Printer.displayError(e.getMessage());
        }
    }

    private void shutdown() {
        isRunning = false;
        terminal.close();
    }
}
