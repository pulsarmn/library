package com.pulsar;

import com.pulsar.exception.InvalidLibraryItemException;
import com.pulsar.model.LibraryItem;
import com.pulsar.service.LibraryService;
import com.pulsar.util.Printer;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Library {

    private final Scanner terminal;
    private final LibraryService libraryService;

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

    public void open() {
        while (true) {
            Printer.mainMenu();
            Printer.inputRequest();

            String menuItem = terminal.nextLine();

            switch (menuItem) {
                case PRINT_CATALOG -> libraryService.printCatalog();
                case ADD_ITEM -> {
                    addItem();
                    terminal.nextLine();
                }
                case TAKE_ITEM -> takeItem();
                case RETURN_ITEM -> returnItem();
                case EXIT -> System.exit(0);
                default -> Printer.inputError();
            }
        }
    }

    private void addItem() {
        try {
            LibraryItem libraryItem = createLibraryItem();
            libraryService.addLibraryItem(libraryItem);
        } catch (InputMismatchException e) {
            Printer.inputError();
        } catch (Exception e) {
            Printer.error("Невозможно создать объект с такими данными!");
        }
    }

    private LibraryItem createLibraryItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        Printer.println("Введите кол-во копий:");
        Printer.inputRequest();
        int copies = terminal.nextInt();

        return libraryService.createLibraryItem(title, author, copies);
    }

    private Optional<LibraryItem> takeItem() {
        try {
            LibraryItem libraryItem = createSingleLibraryItem();
            return Optional.ofNullable(libraryService.takeLibraryItem(libraryItem));
        } catch (Exception e) {
            Printer.error(e.getMessage());
            return Optional.empty();
        }
    }

    private void returnItem() {
        try {
            LibraryItem libraryItem = createSingleLibraryItem();
            libraryService.returnLibraryItem(libraryItem);
        } catch (InvalidLibraryItemException e) {
            Printer.error(e.getMessage());
        }
    }

    private LibraryItem createSingleLibraryItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine().trim();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine().trim();

        return libraryService.createLibraryItem(title, author, 1);
    }
}
