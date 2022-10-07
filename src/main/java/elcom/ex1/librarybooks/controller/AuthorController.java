package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;


@RestController
@RequestMapping("/author")
public class AuthorController {
    private static final Logger LOGGER =  LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author create (@RequestBody String authorName){
        if(authorName == null)
            throw new ValidationException("Không được để trống field");

        return authorService.create(authorName);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody String authorName){
        if(authorService.findById(id) == null)
            throw new ValidationException("Id không tồn tại");
        return authorService.update(id, authorName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if(authorService.findById(id) == null)
            throw new ValidationException("Id không tồn tại");
        authorService.delete(id);
    }


    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id){
        LOGGER.debug("authorId[{}] from DB", id);
        if(authorService.findById(id) == null)
            throw new ValidationException("Id không hợp lệ");
        return authorService.findById(id);
    }

    @GetMapping
    public Iterable<Author> findAll(){


        return authorService.findAll();
    }
}
