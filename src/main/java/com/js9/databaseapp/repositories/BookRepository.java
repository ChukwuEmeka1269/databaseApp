package com.js9.databaseapp.repositories;

import com.js9.databaseapp.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
