package com.pulsar;

import com.pulsar.service.LibraryService;
import com.pulsar.util.Printer;

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

            }else if (menuItem.equals(TAKE_ITEM)) {

            }else if (menuItem.equals(RETURN_ITEM)) {

            }else if (menuItem.equals(EXIT)) {
                break;
            }else {
                Printer.inputError();
            }
        }
    }
}
