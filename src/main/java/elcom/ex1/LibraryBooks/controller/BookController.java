package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Books;
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
    public Books findById(@PathVariable Long ID){

        return bookService.findById(ID);
    }

    @PostMapping
    public Books create(@RequestBody Books book){

        return bookService.create(book);
    }

    @PutMapping("/{ID}")
    public Books update(@PathVariable Long ID, @RequestBody Books book){
        return bookService.update(ID, book);
    }

    @GetMapping
    public Iterable<Books> findAll(){
        return bookService.findAll();
    }
}
