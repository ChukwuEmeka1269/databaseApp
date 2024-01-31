package com.js9.databaseapp.dao.impl;

import com.js9.databaseapp.TestDataUtil;
import com.js9.databaseapp.dao.AuthorDao;
import com.js9.databaseapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {
    private AuthorDao authorDaoUnderTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDao authorDao){
        this.authorDaoUnderTest = authorDao;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndReturned(){

        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.create(author);
        Optional<Author> optionalAuthor = authorDaoUnderTest.findOne(1L);
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);


    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){

        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();

        authorDaoUnderTest.create(authorA);
        authorDaoUnderTest.create(authorB);
        authorDaoUnderTest.create(authorC);

        assertThat(authorDaoUnderTest.find())
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }


    @Test
    public void testThatAuthorWithValidAuthorIdCanBeUpdated(){
        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.create(author);

        author.setName("UPDATED NAME");
        author.setAge(102);

        authorDaoUnderTest.update(author.getId(), author);

        Optional<Author> optionalAuthor = authorDaoUnderTest.findOne(author.getId());

        assertThat(optionalAuthor).isPresent();

        assertThat(optionalAuthor.get().getAge()).isEqualTo(102);

        assertThat(optionalAuthor.get().getName()).isEqualTo("UPDATED NAME");

    }


    @Test
    public void testThatAuthorCreatedCanBeDeleted(){
        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.create(author);

        authorDaoUnderTest.delete(author.getId());

        Optional<Author> optionalAuthor = authorDaoUnderTest.findOne(author.getId());

        assertThat(optionalAuthor).isEmpty();

    }
}
