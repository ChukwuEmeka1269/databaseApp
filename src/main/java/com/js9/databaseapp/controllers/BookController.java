package com.js9.databaseapp.controllers;

import com.js9.databaseapp.domain.dto.BookDto;
import com.js9.databaseapp.domain.entities.Book;
import com.js9.databaseapp.mappers.Mapper;
import com.js9.databaseapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final Mapper<Book, BookDto> bookMapper;


    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable String isbn,
                                              @RequestBody BookDto bookDto){

        bookDto.setIsbn(isbn);
        Book book = bookMapper.mapFrom(bookDto);
        Book savedBook = bookService.create(book);
        var savedBookDto = bookMapper.mapTo(savedBook);

        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
}
