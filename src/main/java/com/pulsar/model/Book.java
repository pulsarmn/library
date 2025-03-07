package com.pulsar.model;

public class Book extends LibraryItem {

    public Book(String title, String author, int availableCopies) {
        super(title, author, availableCopies);
    }

    @Override
    public String toString() {
        return "Название: %s | Автор: %s | В наличии: %s".formatted(title, author, availableCopies);
    }
}
