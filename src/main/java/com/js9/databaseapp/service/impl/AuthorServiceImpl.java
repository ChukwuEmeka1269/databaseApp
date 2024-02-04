package com.js9.databaseapp.service.impl;

import com.js9.databaseapp.domain.entities.Author;
import com.js9.databaseapp.repositories.AuthorRepository;
import com.js9.databaseapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
