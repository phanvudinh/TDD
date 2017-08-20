package com.axonactive.kiss.TDD.service;

import com.axonactive.kiss.TDD.model.Book;

public interface BookService {
    public Book saveBook(Book book);
    public int countByUserId(long id);
}
