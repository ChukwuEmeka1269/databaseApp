package com.js9.databaseapp.repositories;

import com.js9.databaseapp.TestDataUtil;
import com.js9.databaseapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository authorDaoUnderTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository){
        this.authorDaoUnderTest = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndReturned(){

        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.save(author);
        Optional<Author> optionalAuthor = authorDaoUnderTest.findById(1L);
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);


    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){

        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();

        authorDaoUnderTest.save(authorA);
        authorDaoUnderTest.save(authorB);
        authorDaoUnderTest.save(authorC);

        assertThat(authorDaoUnderTest.findAll())
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }


    @Test
    public void testThatAuthorWithValidAuthorIdCanBeUpdated(){
        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.save(author);

        author.setName("UPDATED NAME");
        author.setAge(102);

        authorDaoUnderTest.save(author);

        Optional<Author> optionalAuthor = authorDaoUnderTest.findById(author.getId());

        assertThat(optionalAuthor).isPresent();

        assertThat(optionalAuthor.get().getAge()).isEqualTo(102);

        assertThat(optionalAuthor.get().getName()).isEqualTo("UPDATED NAME");

    }


    @Test
    public void testThatAuthorCreatedCanBeDeleted(){
        var author = TestDataUtil.createTestAuthorA();
        authorDaoUnderTest.save(author);

        authorDaoUnderTest.deleteById(author.getId());

        Optional<Author> optionalAuthor = authorDaoUnderTest.findById(author.getId());

        assertThat(optionalAuthor).isEmpty();

    }



    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();


        authorDaoUnderTest.save(authorA);
        authorDaoUnderTest.save(authorB);
        authorDaoUnderTest.save(authorC);


        List<Author> authors =  authorDaoUnderTest.ageLessThan(50);

        assertThat(authors).containsExactly(authorB, authorC);
    }


    @Test
    public void testThatGetAuthorsGreaterThan(){
        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();


        authorDaoUnderTest.save(authorA);
        authorDaoUnderTest.save(authorB);
        authorDaoUnderTest.save(authorC);


       List<Author> authors =  authorDaoUnderTest.findAuthorWithAgeGreaterThan(50);

        assertThat(authors).containsExactly(authorA);
    }
}
