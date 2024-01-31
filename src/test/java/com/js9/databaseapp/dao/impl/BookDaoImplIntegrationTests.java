package com.js9.databaseapp.dao.impl;


import com.js9.databaseapp.TestDataUtil;
import com.js9.databaseapp.dao.AuthorDao;
import com.js9.databaseapp.dao.BookDao;
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
public class BookDaoImplIntegrationTests {

    private AuthorDao authorDao;
    private BookDao bookDaoUnderTest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDao bookDao, AuthorDao authorDao){
        this.bookDaoUnderTest = bookDao;
        this.authorDao = authorDao;
    }


    @Test
    public void testThatBookIsCreatedAndReturned(){

        var author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        var book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        bookDaoUnderTest.create(book);
        Optional<Book> optionalBook = bookDaoUnderTest.find(book.getIsbn()  );
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book);
    }


    @Test
    public void testThatCanCreateMultipleBooksAndRecallThem(){

        var authorA = TestDataUtil.createTestAuthorA();
        var authorB = TestDataUtil.createTestAuthorB();
        var authorC = TestDataUtil.createTestAuthorC();


        /* creating the authors in the db*/
        authorDao.create(authorA);
        authorDao.create(authorB);
        authorDao.create(authorC);


        var bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        bookDaoUnderTest.create(bookA);

        var bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(authorB.getId());
        bookDaoUnderTest.create(bookB);

        var bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(authorC.getId());
        bookDaoUnderTest.create(bookC);


        var bookD = TestDataUtil.createTestBookD();
        bookD.setAuthorId(authorA.getId());
        bookDaoUnderTest.create(bookD);

        List<Book> results = bookDaoUnderTest.findAll();
        assertThat(results)
                .hasSize(4)
                .containsExactly(bookA, bookB, bookC, bookD);
    }

    @Test
    public void testThatCreatedBookCanBeUpdated(){
        var authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        var authorB = TestDataUtil.createTestAuthorB();
        authorDao.create(authorB);

        var bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        bookDaoUnderTest.create(bookA);

        bookA.setTitle("UPDATED BOOK TITLE");
        bookA.setAuthorId(authorB.getId());

        bookDaoUnderTest.update(bookA.getIsbn(), bookA);

        Optional<Book> optionalBook = bookDaoUnderTest.find(bookA.getIsbn());

        assertThat(optionalBook).isPresent();

        assertThat(optionalBook.get().getTitle()).isEqualTo("UPDATED BOOK TITLE");
        assertThat(optionalBook.get().getAuthorId()).isEqualTo(authorB.getId());
    }


    @Test
    public void testThatCreatedBookCanBeDeleted(){
        var author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        var book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());

        bookDaoUnderTest.create(book);

        bookDaoUnderTest.delete(book.getIsbn());

        Optional<Book> optionalBook = bookDaoUnderTest.find(book.getIsbn());

        assertThat(optionalBook).isEmpty();

    }
}
