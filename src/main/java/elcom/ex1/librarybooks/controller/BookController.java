package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.entity.elastic.BookEs;
import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.entity.library.Book;
import elcom.ex1.librarybooks.entity.library.Category;
import elcom.ex1.librarybooks.service.BookEsService;
import elcom.ex1.librarybooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;
    private final BookEsService bookEsService;
    @Autowired
    public BookController(BookService bookService, BookEsService bookEsService) {
        this.bookService = bookService;
        this.bookEsService = bookEsService;
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){

        return bookService.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        Long id = book.getId();
        String name = book.getBookName();
        BookEs bookEs = new BookEs(id, name);
        bookEsService.save(bookEs);
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        String name = book.getBookName();
        BookEs bookEs = new BookEs(id, name);
        bookEsService.save(bookEs);
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


