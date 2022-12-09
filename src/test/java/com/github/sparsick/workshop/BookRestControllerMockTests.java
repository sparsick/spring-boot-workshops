package com.github.sparsick.workshop;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookServiceMock;

    @Test
    void testFindAllBooks() throws Exception {

        when(bookServiceMock.findAllBooks()).thenReturn(Collections.emptyList());

        // use static imports
        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}