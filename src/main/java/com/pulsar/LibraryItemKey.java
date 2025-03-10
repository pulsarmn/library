package com.pulsar;

import com.pulsar.model.LibraryItem;

import java.util.Objects;

public class LibraryItemKey {

    private final String title;
    private final String author;

    public LibraryItemKey(LibraryItem libraryItem) {
        this.title = libraryItem.getTitle();
        this.author = libraryItem.getAuthor();
    }

    public LibraryItemKey(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItemKey that = (LibraryItemKey) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
