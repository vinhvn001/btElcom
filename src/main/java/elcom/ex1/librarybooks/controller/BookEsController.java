package elcom.ex1.librarybooks.controller;


import elcom.ex1.librarybooks.entity.elastic.BookEs;
import elcom.ex1.librarybooks.service.BookEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/bookEs")
public class BookEsController {

    private final BookEsService bookEsService;
    @Autowired
    public BookEsController(BookEsService bookEsService) {
        this.bookEsService = bookEsService;
    }

    @GetMapping("/{id}")
    public BookEs findById(@PathVariable Long id){
        if(id == null)
            throw new ValidationException("id không được để trống");
        if(bookEsService.findById(id) == null)
            throw new ValidationException("id không tồn tại trong hệ thống");

        return bookEsService.findById(id);
    }

    @GetMapping("/findByName")
    public List<BookEs> findByName(@RequestParam(name="name") String name){
        if(name == null)
            throw new ValidationException("Tên sách không được để trống");
        if(bookEsService.findByName(name) == null)
            throw new ValidationException("Sách không tồn tại trong hệ thống");
        return bookEsService.findByName(name);
    }
}
