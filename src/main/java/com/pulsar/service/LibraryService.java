package com.pulsar.service;

import com.pulsar.Catalog;

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
}
