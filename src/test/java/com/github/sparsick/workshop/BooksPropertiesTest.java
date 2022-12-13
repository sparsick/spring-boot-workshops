package com.github.sparsick.workshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BooksPropertiesTest {

    @Autowired
    private BooksProperties propertiesUnderTest;

    @Test
    void testConfig(){
        assertEquals(42, propertiesUnderTest.getSomeNumber());
        assertEquals("Hello World", propertiesUnderTest.getSomeText());
    }

}