package com.pulsar.service;

import com.pulsar.Catalog;
import com.pulsar.DefaultItemFactory;
import com.pulsar.ItemFactory;
import com.pulsar.LibraryItemKey;
import com.pulsar.exception.InvalidLibraryItemException;
import com.pulsar.model.LibraryItem;

public class LibraryService {

    private final Catalog catalog;
    private final ItemFactory itemFactory;

    public LibraryService() {
        this(new Catalog(), new DefaultItemFactory());
    }

    public LibraryService(Catalog catalog, ItemFactory itemFactory) {
        this.catalog = catalog;
        this.itemFactory = itemFactory;
    }

    public void printCatalog() {
        catalog.print();
    }

    public LibraryItem registerNewItem(String title, String author, int copies) {
        LibraryItem item = itemFactory.createBook(title, author, copies);
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
