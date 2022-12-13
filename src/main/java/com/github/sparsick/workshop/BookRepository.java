package com.github.sparsick.workshop;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookRepository {

    private final ObjectMapper mapper;

    private final ResourceLoader resourceLoader;

    private JdbcTemplate jdbcTemplate;

    private List<Book> books;

    
    
    public BookRepository(ObjectMapper mapper, ResourceLoader resourceLoader, JdbcTemplate jdbcTemplate) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() throws Exception {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    public List<Book> findAllBooks(){
        String sql = "SELECT * FROM book";      
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
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

    public Book addBook(Book book) {
        String sql = "INSERT INTO book (id, title, description, author, isbn) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getIsbn());
        return book;
    }

}
