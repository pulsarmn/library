package com.pulsar;

import com.pulsar.model.LibraryItem;

public interface ItemFactory {

    LibraryItem createBook(String title, String author, int copies);
}
