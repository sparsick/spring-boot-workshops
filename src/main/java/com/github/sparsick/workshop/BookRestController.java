package com.github.sparsick.workshop;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/book")
public class BookRestController {
    private final ObjectMapper mapper;

    private final ResourceLoader resourceLoader;

    private List<Book> books;
    
    public BookRestController(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }
    
    @PostConstruct
    public void init() throws Exception {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    // Map a method returning books to HTTP GET requests for this controller's URL. 
    // ...

    @GetMapping
    public List<Book> findAllBooks(){
        return this.books;
    }
}
