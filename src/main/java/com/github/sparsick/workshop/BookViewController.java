package com.github.sparsick.workshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookViewController {

    private BookService bookService;
    
    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    public String getAllBooks(Model model) {
    model.addAttribute("books", bookService.findAllBooks());
    return "books"; // return the view template name (i.e., 'books.html') as a string
    }
}
