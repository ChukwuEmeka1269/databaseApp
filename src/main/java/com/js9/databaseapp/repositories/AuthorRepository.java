package com.js9.databaseapp.repositories;

import com.js9.databaseapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
