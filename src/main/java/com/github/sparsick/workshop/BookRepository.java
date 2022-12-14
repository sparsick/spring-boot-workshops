package com.github.sparsick.workshop;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookRepository extends CrudRepository<Book, Long>{

    Book findByIsbn(String isbn);
    Book findByAuthorAndTitle(String author, String title);
    Book findByAuthor(String author);
    List<Book> findByAuthorAndIsbn(String author, String isbn);

   
}
