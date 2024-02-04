package com.js9.databaseapp.repositories;

import com.js9.databaseapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> ageLessThan(int age);

    @Query("SELECT a FROM Author a WHERE a.age > ?1")
    List<Author> findAuthorWithAgeGreaterThan(int i);
}
