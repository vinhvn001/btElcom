package com.elcom.elastic.controller;



import com.elcom.elastic.model.BookEs;
import com.elcom.elastic.service.BookEsService;
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
    public List<Object[]> findByName(@RequestBody BookEs name){
        if(name == null)
            throw new ValidationException("Tên sách không được để trống");
        if(bookEsService.findByName(name.getName()) != null)
            return bookEsService.findByName(name.getName());
        else
            throw new ValidationException("Sách không tồn tại trong hệ thống");
    }

    @GetMapping
    public Iterable<BookEs> findAll(){
        return bookEsService.findAll();
    }
}
