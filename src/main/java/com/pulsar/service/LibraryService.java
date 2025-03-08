package com.pulsar.service;

import com.pulsar.Catalog;
import com.pulsar.exception.InvalidLibraryItemException;
import com.pulsar.model.Book;
import com.pulsar.model.LibraryItem;

public class LibraryService {

    private final Catalog catalog;

    public LibraryService() {
        this(new Catalog());
    }

    public LibraryService(Catalog catalog) {
        this.catalog = catalog;
    }

    public void printCatalog() {
        catalog.print();
    }

    public LibraryItem createLibraryItem(String title, String author, int availableCopies) {
        LibraryItem libraryItem = new Book(title, author, availableCopies);
        validate(libraryItem);
        return libraryItem;
    }

    public void addLibraryItem(LibraryItem libraryItem) {
        validate(libraryItem);
        catalog.add(libraryItem);
    }

    private void validate(LibraryItem libraryItem) {
        if (libraryItem == null
                || libraryItem.getTitle() == null
                || libraryItem.getTitle().isBlank()
                || libraryItem.getAuthor() == null
                || libraryItem.getAuthor().isBlank()) {
            throw new InvalidLibraryItemException("Невозможно создать объект с такими данными!");
        }
    }

    public LibraryItem takeLibraryItem(LibraryItem libraryItem) {
        return catalog.take(libraryItem);
    }
}
