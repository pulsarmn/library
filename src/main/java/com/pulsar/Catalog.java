package com.pulsar;

import com.pulsar.exception.ItemNotFoundException;
import com.pulsar.model.LibraryItem;
import com.pulsar.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void add(LibraryItem libraryItem) {
        if (libraryItem == null) {
            throw new IllegalArgumentException("Добавляемый объект не должен быть пустым!");
        }
        catalog.add(libraryItem);
    }

    public LibraryItem take(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Заголовок не должен быть пустым");
        }

        return catalog.stream()
                .filter(libraryItem -> libraryItem.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .map(libraryItem -> {
                    libraryItem.decrementCopies();
                    return libraryItem;
                })
                .orElseThrow(() -> new ItemNotFoundException("Объект с именем %s не найден".formatted(title)));
    }
}
