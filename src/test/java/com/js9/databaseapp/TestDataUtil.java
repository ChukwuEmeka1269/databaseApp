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
                .age(80)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Jack Doe")
                .age(49)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .authorId(1L)
                .isbn("abcd123")
                .title("Learning ABCD")
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .authorId(2L)
                .isbn("bcde1234")
                .title("TEST 2")
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .authorId(3L)
                .isbn("efgh1234")
                .title("TEST 3")
                .build();
    }

    public static Book createTestBookD() {
        return Book.builder()
                .authorId(4L)
                .isbn("ijkl1234")
                .title("TEST 4")
                .build();
    }
}
