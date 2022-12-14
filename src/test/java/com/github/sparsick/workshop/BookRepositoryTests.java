package com.github.sparsick.workshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository repositoryUnderTest;
    
    
    @Test
    void testFindAllBooks() {
        var books = (List<Book> )repositoryUnderTest.findAll();
        
        assertEquals(3, books.size());
    }
}
