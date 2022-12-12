package com.github.sparsick.workshop;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    public Book findBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> findAllBooksByAuthorOrIsbn(String author, String isbn) {
        return bookRepository.findAllBooksByAuthorOrIsbn(author, isbn);
    }

    public Book createBook(Book book) {
        return bookRepository.addBook(book);
    }


    
}
