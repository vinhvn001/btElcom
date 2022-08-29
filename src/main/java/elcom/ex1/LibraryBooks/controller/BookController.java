package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Book;
import elcom.ex1.LibraryBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){

        return bookService.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book){

        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        return bookService.update(id, book);
    }

    @GetMapping
    public Iterable<Book> findAll(Long id){
        return bookService.findAll();
    }

}


