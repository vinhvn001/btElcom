package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private static final Logger LOGGER =  LoggerFactory.getLogger(UserController.class);
    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author create (@RequestBody Author author){

        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author author){
        return authorService.update(id, author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }


    @GetMapping("/{id}")
    @Cacheable(key = "#p0", value ="author")
    public Author findById(@PathVariable Long id){
        LOGGER.debug("authorId[{}] from DB", id);
        return authorService.findById(id);
    }

    @GetMapping
    public Iterable<Author> findAll(){
        return authorService.findAll();
    }
}
