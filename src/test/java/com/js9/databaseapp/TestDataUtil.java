package com.js9.databaseapp;

import com.js9.databaseapp.domain.Author;
import com.js9.databaseapp.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("John Doe")
                .age(90)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Jane Doe")
                .age(40)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Jack Doe")
                .age(49)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("abcd123")
                .title("Learning ABCD")
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("bcde1234")
                .title("TEST 2")
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("efgh1234")
                .title("TEST 3")
                .build();
    }

    public static Book createTestBookD(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("ijkl1234")
                .title("TEST 4")
                .build();
    }
}
