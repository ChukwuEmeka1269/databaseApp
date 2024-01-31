//package com.js9.databaseapp.repositories;
//
//
//import com.js9.databaseapp.TestDataUtil;
//import com.js9.databaseapp.domain.Book;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class BookDaoImplTest {
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    BookDaoImpl bookDaoImplUnderTest;
//
//    AuthorDaoImpl authorDao;
//
//    @Test
//    void testCreateBookGeneratesTheCorrectSQL(){
//
//        Book book = TestDataUtil.createTestBookA();
//
//        bookDaoImplUnderTest.create(book);
//
//        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"),
//                eq("abcd123"), eq("Learning ABCD"),  eq(1L));
//    }
//
//
//    @Test
//    void testFindOneBookGeneratesTheCorrectSQL(){
//        bookDaoImplUnderTest.find("abcd123");
//
//        verify(jdbcTemplate).query(
//                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
//                ArgumentMatchers.<BookDaoImpl.BookDaoRowMapper>any(),
//                eq("abcd123")
//        );
//
//    }
//
//
//    @Test
//    public void testFindBooksGeneratesCorrectSQL(){
//        List<Book> books =  bookDaoImplUnderTest.findAll();
//
//        verify(jdbcTemplate).query(
//                eq("SELECT isbn, title, author_id FROM books"),
//                ArgumentMatchers.<BookDaoImpl.BookDaoRowMapper>any()
//        );
//    }
//
//    @Test
//    public void testThatUpdateGeneratesCorrectSQL(){
////        var author = TestDataUtil.createTestAuthorA();
//
//        var book = TestDataUtil.createTestBookA();
//        book.setTitle("UPDATED BOOK TITLE");
//
//        bookDaoImplUnderTest.update(book.getIsbn(), book);
//
//        verify(jdbcTemplate).update(
//                "UPDATE books SET isbn = ? , title = ? , author_id = ? WHERE isbn = ?",
//                "abcd123", "UPDATED BOOK TITLE", 1L, "abcd123"
//        );
//    }
//
//    @Test
//    public void testThatDeleteGeneratesCorrectSQL(){
//        var book = TestDataUtil.createTestBookA();
//
//        bookDaoImplUnderTest.delete(book.getIsbn());
//
//        verify(jdbcTemplate).update(
//                "DELETE FROM books WHERE isbn = ?",
//                book.getIsbn()
//        );
//    }
//}
