package com.axonactive.kiss.TDD.service;

import com.axonactive.kiss.TDD.model.Book;
import com.axonactive.kiss.TDD.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int countByUserId(long id) {
        return bookRepository.countByUserId(id);
    }
}
