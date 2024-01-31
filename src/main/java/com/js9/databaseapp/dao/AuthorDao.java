package com.js9.databaseapp.dao;

import com.js9.databaseapp.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long authorId);

    List<Author> find();

    void update(Long l, Author author);

    void delete(Long id);
}
