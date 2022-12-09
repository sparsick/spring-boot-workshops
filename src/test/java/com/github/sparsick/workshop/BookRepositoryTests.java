package com.github.sparsick.workshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository repositoryUnderTest;
    @Test
    void testFindAllBooks() {
        var books = repositoryUnderTest.findAllBooks();
        
        assertEquals(3, books.size());
    }
}
