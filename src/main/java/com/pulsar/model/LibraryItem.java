package com.pulsar.model;

import com.pulsar.exception.NoAvailableCopiesException;

import java.util.Objects;

public abstract class LibraryItem {

    private final String title;
    private final String author;
    private volatile int availableCopies;

    public LibraryItem(String title, String author, int availableCopies) {
        validate(title, author, availableCopies);
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    private void validate(String title, String author, int copies) {
        Objects.requireNonNull(title, () -> "Название не может быть пустым");
        Objects.requireNonNull(author, () -> "Автор не может быть пустым");
        if (copies < 1) {
            throw new IllegalArgumentException("Кол-во копий должно быть больше 0: %s".formatted(copies));
        }
    }

    public synchronized void incrementCopies() {
        availableCopies++;
    }

    public synchronized void increaseCopies(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Кол-во должно быть больше 0: %s".formatted(amount));
        }
        availableCopies += amount;
    }

    public synchronized void decrementCopies() {
        if (availableCopies == 0) {
            throw new NoAvailableCopiesException("%s нет в наличии!".formatted(title));
        }
        availableCopies--;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem that = (LibraryItem) o;
        return title.equals(that.title) && author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public abstract String toString();
}
