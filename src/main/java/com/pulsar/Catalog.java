package com.pulsar;

import com.pulsar.model.LibraryItem;
import com.pulsar.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private final List<LibraryItem> catalog;

    public Catalog() {
        this(new ArrayList<>());
    }

    public Catalog(List<LibraryItem> catalog) {
        this.catalog = catalog;
    }

    public void print() {
        if (catalog.isEmpty()) {
            Printer.error("Каталог пуст!");
        }else {
            Printer.success("Каталог:");
            catalog.forEach(System.out::println);
        }
    }
}
