package com.github.sparsick.workshop;

import java.util.List;

import javax.validation.constraints.Size;

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


@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    
    @GetMapping
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book findSingleBook(@PathVariable @Size(min=10) String isbn){
        return  bookService.findBookByIsbn(isbn);
    }


    @GetMapping(params = "author")
    public Book searchBookbyAuthor(@RequestParam String author){
        return bookService.findBookByAuthor(author);
    }

    @PostMapping("search")
    public List<Book> searchBooks(@RequestBody BookSearchRequst booksearchRequest) {
        return bookService.findAllBooksByAuthorOrIsbn(booksearchRequest.getAuthor(), booksearchRequest.getIsbn());
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> error(BookException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }




}
