package com.elcom.library.controller;


import com.elcom.library.model.Author;
import com.elcom.library.model.Book;
import com.elcom.library.model.Category;
import com.elcom.library.model.dto.AuthorizationResponseDTO;
import com.elcom.library.service.AuthorService;
import com.elcom.library.service.BookService;
import com.elcom.library.service.CategoryService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;


@RestController
public class BookController extends BaseController{


    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/library/book/findAll")
    public ResponseMessage findAllBook(@RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<Book> allBook = bookService.findAll();
            if (allBook == null || allBook.isEmpty()) {
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        allBook));
            }
        }
        return response;
    }

    @GetMapping("/library/book/{id}")
    public ResponseMessage findBookById(@PathVariable String id, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Book book = bookService.findById(Long.parseLong(id));
            if (book == null ) {
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.NOT_FOUND.value(), "Author không tồn tại", null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                book));
            }
        }
        return response;
    }

    @PostMapping("/library/book")
    public ResponseMessage createBook(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                if (bodyParam == null || bodyParam.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid param value",
                            new MessageContent(HttpStatus.BAD_REQUEST.value(), "Invalid param value", null));
                }else {
                    String bookName = (String) bodyParam.get("bookName");
                    String amount = (String) bodyParam.get("bookAmount");
                    String firstLetter = (String) bodyParam.get("firstLetter");
                    String authorId = (String) bodyParam.get("authorId");
                    String categoryId = (String) bodyParam.get("categoryId");

                    Author author = authorService.findById(Long.parseLong(authorId));
                    Category category = categoryService.findById(Long.parseLong(categoryId));
                    if (author == null|| category == null) {
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Category hoặc author không tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Category hoặc author không tồn tại", null));


                    } else{
                        Book book = new Book();
                        book.setBookName(bookName);
                        book.setBookAmount(Integer.parseInt(amount));
                        book.setFirstLetter(firstLetter);
                        book.setAuthorId(author);
                        book.setCategoryId(category);

                        Book existBook = null;
                        if (bookName != null) {
                            existBook = bookService.findByBookName(bookName);
                        }
                        if (existBook != null) {
                            response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Book đã tồn tại",
                                    new MessageContent(HttpStatus.CONFLICT.value(), "Book đã tồn tại", null));
                        } else {
                            try {
                                bookService.create(book);
                                response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                                        new MessageContent(book));
                            } catch (Exception ex) {
                                response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                        new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                            }
                        }
                    }
                }
            }else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo book mới",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo book mới", null));
            }
        }
        return response;
    }

    @PutMapping("/library/book")
    public ResponseMessage updateBook( @RequestBody Map<String, Object> bodyParam, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){

                String id = (String)bodyParam.get("id");

                Book currentBook = bookService.findById(Long.parseLong(id));
                if(currentBook == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Sách cần sửa không tồn tại",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Sách cần sửa không tồn tại", null));
                }else{
                    String bookName = (String)bodyParam.get("bookName");
                    String amount = (String)bodyParam.get("amount");
                    String firstLetter = (String)bodyParam.get("firstLetter");
                    String authorId = (String)bodyParam.get("authorId");
                    String categoryId = (String)bodyParam.get("categoryId");

                    Author author = authorService.findById(Long.parseLong(authorId));
                    Category category = categoryService.findById(Long.parseLong(categoryId));

                    currentBook.setBookName(bookName);
                    currentBook.setBookAmount(Integer.parseInt(amount));
                    currentBook.setFirstLetter(firstLetter);
                    currentBook.setAuthorId(author);
                    currentBook.setCategoryId(category);

                    Book existBook = null;
                    if(bookName != null){
                        existBook = bookService.findByBookName(bookName);
                    }
                    if(existBook != null){
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Book đã tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Book đã tồn tại", null));
                    }else{
                        try {
                            bookService.update(currentBook);
                            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                                    new MessageContent(currentBook));
                        } catch (Exception ex){
                            response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                        }
                    }
                }
            }else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin sách",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin sách", null));
            }
        }
        return response;
    }

    @DeleteMapping("/library/book/{id}")
    public ResponseMessage deleteBook(@PathVariable String id,  @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                Long Id = Long.parseLong(id);
                Book currentBook = bookService.findById(Id);
                if(currentBook == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Invalid param value",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Invalid param value", null));
                }else{
                    categoryService.delete(Id);
                    response =  new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                            new MessageContent(null));
                }
            }else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xóa sách ",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xóa sách", null));
            }
        }
        return response;
    }

}


