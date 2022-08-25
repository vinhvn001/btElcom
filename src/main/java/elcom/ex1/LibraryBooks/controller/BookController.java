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

    @GetMapping("/{ID}")
    public Book findById(@PathVariable Long ID){

        return bookService.findById(ID);
    }

    @PostMapping
    public Book create(@RequestBody Book book){

        return bookService.create(book);
    }

    @PutMapping("/{ID}")
    public Book update(@PathVariable Long ID, @RequestBody Book book){
        return bookService.update(ID, book);
    }

    @GetMapping
    public Iterable<Book> findAll(Long ID){
        return bookService.findAll();
    }
}
