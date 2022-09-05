package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.entity.Author;
import elcom.ex1.librarybooks.entity.Book;
import elcom.ex1.librarybooks.entity.Category;
import elcom.ex1.librarybooks.service.BookService;
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

    @GetMapping("/statistic_by_author")
    public Integer findBookAmountByAuthorId(@RequestParam(name = "authorId" , required = false ) @RequestBody Author id){

        return bookService.findBookAmountByAuthorId(id);
    }

    @GetMapping("/statistic_by_category")
    public Integer findBookAmountByCategoryId(@RequestParam(name = "categoryId", required = false) @RequestBody Category id){
        return bookService.findBookAmountByCategoryId(id);
    }

    @GetMapping("/statistic_by_firstletter")
    public Integer findBookAmountByFirstLetter(@RequestParam(name = "firstLetter", required = false) @RequestBody String firstLetter){
        return bookService.findBookAmountByFirstLetter(firstLetter);
    }

}


