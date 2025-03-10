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

    private static final String INPUT_NAME = "Введите название:";
    private static final String INPUT_AUTHOR = "Введите автора:";
    private static final String INPUT_NUMBER_OF_COPIES = "Введите кол-во копий:";

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
        Printer.println(INPUT_NAME);
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println(INPUT_AUTHOR);
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        Printer.println(INPUT_NUMBER_OF_COPIES);
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
        Printer.println(INPUT_NAME);
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println(INPUT_AUTHOR);
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        try {
            libraryService.takeItem(title, author);
        } catch (Exception e) {
            Printer.displayError(e.getMessage());
        }
    }

    private void returnItem() {
        Printer.println(INPUT_NAME);
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println(INPUT_AUTHOR);
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
