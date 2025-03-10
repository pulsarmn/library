package com.pulsar.service;

import com.pulsar.Catalog;
import com.pulsar.LibraryItemKey;
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

    public LibraryItem registerNewItem(String title, String author, int copies) {
        LibraryItem item = new Book(title, author, copies);
        validate(item);
        catalog.add(item);
        return item;
    }

    public LibraryItem takeItem(String title, String author) {
        LibraryItemKey key = new LibraryItemKey(title, author);
        return catalog.take(key);
    }

    public void returnItem(String title, String author) {
        LibraryItemKey key = new LibraryItemKey(title, author);
        catalog.returnLibraryItem(key);
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
}
