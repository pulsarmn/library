package com.pulsar;

import com.pulsar.model.LibraryItem;

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
}
