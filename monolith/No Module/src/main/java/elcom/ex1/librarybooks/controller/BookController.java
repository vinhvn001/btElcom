package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.entity.elastic.BookEs;
import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.entity.library.Book;
import elcom.ex1.librarybooks.entity.library.Category;
import elcom.ex1.librarybooks.service.AuthorService;
import elcom.ex1.librarybooks.service.BookEsService;
import elcom.ex1.librarybooks.service.BookService;
import elcom.ex1.librarybooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService,AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        if(bookService.findById(id) == null)
            throw new ValidationException("Id không hợp lệ");
        return bookService.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        if(book.getBookName() == null || book.getFirstLetter() ==null || book.getBookAmount() == null || book.getAuthorId() == null || book.getCategoryId() == null)
            throw new ValidationException("Các field không được để trống");
        if(authorService.findById(book.getAuthorId().getId()) == null)
            throw new ValidationException("Author Id không tồn tại");
        if(categoryService.findById(book.getCategoryId().getId()) == null)
            throw new ValidationException("Category Id không tồn tại");
        return bookService.create(book);
    }

    @PutMapping
    public Book update( @RequestBody Book book){

        if(book.getId() == null || book.getBookName() == null || book.getBookAmount() == null || book.getFirstLetter() == null || book.getAuthorId() == null || book.getCategoryId() == null)
            throw new ValidationException("Các field không được để trống");
        if(bookService.findById(book.getId()) == null)
            throw new ValidationException("Id không hợp lệ");

        if(authorService.findById(book.getAuthorId().getId()) == null)
            throw new ValidationException("Author Id không tồn tại");
        if(categoryService.findById(book.getCategoryId().getId()) == null)
            throw new ValidationException("Category Id không tồn tại");
        return bookService.update( book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if(bookService.findById(id) == null)
            throw new ValidationException("Id không hợp lệ");
         bookService.delete(id);
    }
    @GetMapping
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/statistic_by_author")
    public List<Object[]> findBookAmountByAuthorId(@RequestParam(name = "authorId" , required = false ) @RequestBody Author id){

        return bookService.findBookAmountByAuthorId(id);
    }

    @GetMapping("/statistic_by_category")
    public Integer findBookAmountByCategoryId(@RequestParam(name = "categoryId", required = false) @RequestBody Category id){
        return bookService.findBookAmountByCategoryId(id);
    }

    @GetMapping("/statistic_by_firstletter")
    public List<Object[]> findBookAmountByFirstLetter(@RequestParam(name = "firstLetter", required = false) @RequestBody String firstLetter){
        return bookService.findBookAmountByFirstLetter(firstLetter);
    }
    @GetMapping("/statistic_book_list")
    public List<Object[]>findBookList(){
        return bookService.findBookList();
    }

}


