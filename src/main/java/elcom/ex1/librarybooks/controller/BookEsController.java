package elcom.ex1.librarybooks.controller;


import elcom.ex1.librarybooks.entity.elastic.BookEs;
import elcom.ex1.librarybooks.service.BookEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return bookEsService.findById(id);
    }

    @GetMapping("/findByName")
    public List<BookEs> findByName(@RequestParam(name="bookName",required = false) String name){
        return bookEsService.findByName(name);
    }
}
