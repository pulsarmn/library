package com.pulsar.model;

import com.pulsar.exception.NoAvailableCopiesException;

import java.util.Objects;

public abstract class LibraryItem {

    protected final String title;
    protected final String author;
    protected int availableCopies;

    public LibraryItem(String title, String author, int availableCopies) {
        if (availableCopies < 1) {
            throw new IllegalArgumentException("Кол-во должно быть больше 0: %s".formatted(availableCopies));
        }
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void incrementCopies() {
        availableCopies++;
    }

    public void decrementCopies() {
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

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem that = (LibraryItem) o;
        return title.equalsIgnoreCase(that.title) && author.equalsIgnoreCase(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
