package com.pulsar;

import com.pulsar.exception.ItemNotFoundException;
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

            if (menuItem.equals(PRINT_CATALOG)) {
                libraryService.printCatalog();
            }else if (menuItem.equals(ADD_ITEM)) {
                addItem();
                terminal.nextLine();
            }else if (menuItem.equals(TAKE_ITEM)) {
                takeItem();
            }else if (menuItem.equals(RETURN_ITEM)) {

            }else if (menuItem.equals(EXIT)) {
                break;
            }else {
                Printer.inputError();
            }
        }
    }

    private void addItem() {
        try {
            LibraryItem libraryItem = createLibraryItem();
            libraryService.addLibraryItem(libraryItem);
        }catch (InputMismatchException e) {
            Printer.inputError();
        }catch (Exception e) {
            Printer.error("Невозможно создать объект с такими данными!");
        }
    }

    private LibraryItem createLibraryItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine();

        Printer.println("Введите кол-во копий:");
        Printer.inputRequest();
        int copies = terminal.nextInt();

        return libraryService.createLibraryItem(title, author, copies);
    }

    private Optional<LibraryItem> takeItem() {
        try {
            LibraryItem libraryItem = createSingleLibraryItem();
            return Optional.ofNullable(libraryService.takeLibraryItem(libraryItem));
        }catch (ItemNotFoundException e) {
            Printer.error(e.getMessage());
            return Optional.empty();
        }
    }

    private LibraryItem createSingleLibraryItem() {
        Printer.println("Введите название:");
        Printer.inputRequest();
        String title = terminal.nextLine();

        Printer.println("Введите автора:");
        Printer.inputRequest();
        String author = terminal.nextLine();

        return libraryService.createLibraryItem(title, author, 1);
    }
}
