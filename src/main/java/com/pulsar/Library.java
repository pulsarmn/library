package com.pulsar;

import com.pulsar.service.LibraryService;

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
}
