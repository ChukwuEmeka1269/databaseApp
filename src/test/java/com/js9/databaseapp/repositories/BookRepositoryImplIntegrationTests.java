package com.js9.databaseapp.repositories;


import com.js9.databaseapp.TestDataUtil;

import com.js9.databaseapp.domain.Book;

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
public class BookRepositoryImplIntegrationTests {

    private AuthorRepository authorRepository;
    private BookRepository bookRepositoryUnderTest;

    @Autowired
    public BookRepositoryImplIntegrationTests(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepositoryUnderTest = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Test
    public void testThatBookIsCreatedAndReturned(){

        var author = TestDataUtil.createTestAuthorA();

        var book = TestDataUtil.createTestBookA(author);
        bookRepositoryUnderTest.save(book);
        Optional<Book> optionalBook = bookRepositoryUnderTest.findById(book.getIsbn()  );
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book);
    }


    @Test
    public void testThatCanCreateMultipleBooksAndRecallThem(){

        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();

        var bookA = TestDataUtil.createTestBookA(authorA);

        bookRepositoryUnderTest.save(bookA);

        var bookB = TestDataUtil.createTestBookB(authorB);
        bookRepositoryUnderTest.save(bookB);

        var bookC = TestDataUtil.createTestBookC(authorC);
        bookRepositoryUnderTest.save(bookC);


        var bookD = TestDataUtil.createTestBookD(authorA);
        bookRepositoryUnderTest.save(bookD);

        List<Book> results = bookRepositoryUnderTest.findAll();
        assertThat(results)
                .hasSize(4)
                .containsExactly(bookA, bookB, bookC, bookD);
    }

    @Test
    public void testThatCreatedBookCanBeUpdated(){
        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();

        var bookA = TestDataUtil.createTestBookA(authorA);

        bookRepositoryUnderTest.save(bookA);

        bookA.setTitle("UPDATED BOOK TITLE");

        bookRepositoryUnderTest.save(bookA);

        Optional<Book> optionalBook = bookRepositoryUnderTest.findById(bookA.getIsbn());

        assertThat(optionalBook).isPresent();

        assertThat(optionalBook.get().getTitle()).isEqualTo("UPDATED BOOK TITLE");

    }


    @Test
    public void testThatCreatedBookCanBeDeleted(){
        var author = TestDataUtil.createTestAuthorA();


        var book = TestDataUtil.createTestBookA(author);

        bookRepositoryUnderTest.save(book);

        bookRepositoryUnderTest.deleteById(book.getIsbn());

        Optional<Book> optionalBook = bookRepositoryUnderTest.findById(book.getIsbn());

        assertThat(optionalBook).isEmpty();

    }
}
