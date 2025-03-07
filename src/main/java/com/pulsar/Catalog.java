package com.pulsar;

import com.pulsar.exception.ItemNotFoundException;
import com.pulsar.model.LibraryItem;
import com.pulsar.util.Printer;

import java.util.ArrayList;
import java.util.Comparator;
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
        } else {
            Printer.success("Каталог:");
            catalog.forEach(System.out::println);
        }
    }

    public void add(LibraryItem libraryItem) {
        if (libraryItem == null) {
            throw new IllegalArgumentException("Добавляемый объект не должен быть пустым!");
        }

        if (catalog.contains(libraryItem)) {
            catalog.stream()
                    .filter(item -> item.getTitle().equalsIgnoreCase(libraryItem.getTitle()))
                    .filter(item -> item.getAuthor().equalsIgnoreCase(libraryItem.getAuthor()))
                    .peek(item -> {
                        int totalCopies = item.getAvailableCopies() + libraryItem.getAvailableCopies();
                        item.setAvailableCopies(totalCopies);
                    })
                    .findFirst();
        }else {
            catalog.add(libraryItem);
        }
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

    public void returnLibraryItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Возвращаемый элемент не должен быть пустым!");
        }

        if (catalog.contains(item)) {
            catalog.stream()
                    .peek(LibraryItem::incrementCopies)
                    .findFirst();
        } else {
            Printer.error("Невозможно вернуть данный объект!");
        }
    }
}
