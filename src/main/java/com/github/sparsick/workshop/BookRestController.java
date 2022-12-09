package com.github.sparsick.workshop;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/book")
@Validated
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

    @GetMapping
    public List<Book> findAllBooks(){
        return this.books;
    }

    @GetMapping("/{isbn}")
    public Book findSingleBook(@PathVariable @Size(min=10) String isbn){
        return  this.books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow(() -> new BookException());
    }


    @GetMapping(params = "author")
    public Book searchBookbyAuthor(@RequestParam String author){
        return this.books.stream().filter(book -> hasAuthor(book, author)).findFirst().orElseThrow(() -> new BookException());
    }

    @PostMapping("search")
    public List<Book> searchBooks(@RequestBody BookSearchRequst booksearchRequest) {
        return this.books.stream().filter(book -> hasAuthor(book, booksearchRequest.getAuthor()) || hasIsbn(book, booksearchRequest.getIsbn()) ).toList();
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> error(BookException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }



    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }

    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }
}
