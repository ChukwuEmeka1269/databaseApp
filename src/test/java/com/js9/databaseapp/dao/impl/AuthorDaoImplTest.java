package com.js9.databaseapp.dao.impl;


import com.js9.databaseapp.TestDataUtil;
import com.js9.databaseapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuthorDaoImpl authorDaoUnderTest;

    @Test
    void testCreateAuthorGeneratesCorrectQuery(){
        Author author = TestDataUtil.createTestAuthorA();

        authorDaoUnderTest.create(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors (id, name, age) VALUES (?,?,?)"),
                eq(1L), eq("John Doe"), eq(90));
    }


    @Test
    void testFindOneAuthorGeneratesCorrectQuery(){

        authorDaoUnderTest.findOne(1L);

       verify(jdbcTemplate).query(
                       eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                       ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),eq(1L)

       );
    }

    @Test
    public void testFindAuthorsGenerateCorrectQuery(){
        List<Author> authors =  authorDaoUnderTest.find();

        verify(jdbcTemplate)
                .query(
                        eq("SELECT id, name, age FROM authors"),
                        ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
                );

    }


    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
//        authorDaoUnderTest.create(authorA);

        authorDaoUnderTest.update(13L,  authorA);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L, "John Doe", 90, 13L
        );
    }


    @Test
    public void testThatDeleteAuthorGeneratesCorrectQuery(){

        Author authorA = TestDataUtil.createTestAuthorA();
//        authorDaoUnderTest.create(authorA);

        authorDaoUnderTest.delete(authorA.getId());

        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                1L
        );
    }
}
