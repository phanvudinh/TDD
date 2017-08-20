package com.axonactive.kiss.TDD.web;

import com.axonactive.kiss.TDD.model.Book;
import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.service.BookService;
import com.axonactive.kiss.TDD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Book> saveBook(@RequestBody Book payload) {
        User user = userService.getUserById(payload.getUser().getId());
        Book book = bookService.saveBook(new Book(payload.getName(), payload.getAuthor(), user));
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }
}
