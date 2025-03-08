package com.pulsar;

import com.pulsar.model.Book;
import com.pulsar.model.LibraryItem;

public class DefaultItemFactory implements ItemFactory {

    @Override
    public LibraryItem createBook(String title, String author, int copies) {
        return new Book(title, author, copies);
    }
}
