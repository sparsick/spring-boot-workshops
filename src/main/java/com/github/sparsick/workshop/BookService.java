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
        return (List<Book>) bookRepository.findAll();
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findAllBooksByAuthorOrIsbn(String author, String isbn) {
        return bookRepository.findByAuthorAndIsbn(author, isbn);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    
}
