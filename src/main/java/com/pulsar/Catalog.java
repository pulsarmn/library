package com.pulsar;

import com.pulsar.exception.ItemNotFoundException;
import com.pulsar.exception.NoAvailableCopiesException;
import com.pulsar.model.LibraryItem;
import com.pulsar.util.Printer;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

public class Catalog {

    private final ConcurrentMap<LibraryItemKey, LibraryItem> catalog;

    public Catalog() {
        this.catalog = new ConcurrentHashMap<>();
    }

    public void print() {
        if (catalog.isEmpty()) {
            Printer.displayError("Каталог пуст!");
            return;
        }

        Printer.displaySuccess("Каталог:");
        catalog.values().stream()
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .map(Object::toString)
                .forEach(Printer::println);
    }

    public void add(LibraryItem item) {
        Objects.requireNonNull(item);
        LibraryItemKey key = new LibraryItemKey(item);

        catalog.compute(key, (k, value) -> {
            if (value != null) {
                value.increaseCopies(item.getAvailableCopies());
                return value;
            }
            return item;
        });
    }

    public LibraryItem take(LibraryItemKey key) {
        return performItemOperation(key, item -> {
            if (item.getAvailableCopies() == 0) {
                throw new NoAvailableCopiesException("Данного объекта нет в наличии!");
            }
            item.decrementCopies();
        });
    }

    public void returnLibraryItem(LibraryItemKey key) {
        performItemOperation(key, LibraryItem::incrementCopies);
    }

    private LibraryItem performItemOperation(LibraryItemKey key, Consumer<LibraryItem> operation) {
        LibraryItem item = catalog.get(key);

        if (item == null) {
            throw new ItemNotFoundException("Объект с именем %s и автором %s не найден"
                    .formatted(key.getTitle(), key.getAuthor()));
        }

        synchronized (item) {
            operation.accept(item);
        }
        return item;
    }
}
