package com.github.sparsick.workshop;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookRepository {

    private final ObjectMapper mapper;

    private final ResourceLoader resourceLoader;

    private List<Book> books;
    
    public BookRepository(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    public List<Book> findAllBooks(){
        return this.books;
    }

    public Book findBookByIsbn(String isbn) {
        return books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow(() -> new BookException());
    }

    public Book findBookByAuthor(String author) {
        return books.stream().filter(book -> hasAuthor(book, author)).findFirst().orElseThrow(() -> new BookException());
    }

    public List<Book> findAllBooksByAuthorOrIsbn(String author, String isbn) {
        return books.stream().filter(book -> hasAuthor(book, author) || hasIsbn(book, isbn) ).toList();
    }

    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }

    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }

}
