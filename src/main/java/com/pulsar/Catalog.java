package com.pulsar;

import com.pulsar.exception.InvalidLibraryItemException;
import com.pulsar.exception.ItemNotFoundException;
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
            Printer.displayError("Каталог пуст!");
        } else {
            Printer.displaySuccess("Каталог:");
            catalog.forEach(System.out::println);
        }
    }

    public void add(LibraryItem libraryItem) {
        if (libraryItem == null) {
            throw new IllegalArgumentException("Добавляемый объект не должен быть пустым!");
        }

        if (catalog.contains(libraryItem)) {
            catalog.stream()
                    .filter(item -> item.equals(libraryItem))
                    .peek(item -> {
                        int totalCopies = item.getAvailableCopies() + libraryItem.getAvailableCopies();
                        item.setAvailableCopies(totalCopies);
                    })
                    .findFirst();
        }else {
            catalog.add(libraryItem);
        }
    }

    public LibraryItem take(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Объект не должен быть пустым");
        }

        return catalog.stream()
                .filter(libraryItem -> libraryItem.equals(item))
                .findFirst()
                .map(libraryItem -> {
                    libraryItem.decrementCopies();
                    return libraryItem;
                })
                .orElseThrow(() -> new ItemNotFoundException("Объект с именем %s и автором %s не найден"
                        .formatted(item.getTitle(), item.getAuthor())
                ));
    }

    public void returnLibraryItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Возвращаемый элемент не должен быть пустым!");
        }

        catalog.stream()
                .filter(libraryItem -> libraryItem.equals(item))
                .peek(LibraryItem::incrementCopies)
                .findFirst()
                .orElseThrow(() -> new InvalidLibraryItemException("Невозможно вернуть данный объект!"));
    }
}
