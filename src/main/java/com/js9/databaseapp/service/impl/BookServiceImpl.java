package com.js9.databaseapp.service.impl;

import com.js9.databaseapp.domain.entities.Book;
import com.js9.databaseapp.repositories.BookRepository;
import com.js9.databaseapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }
}
