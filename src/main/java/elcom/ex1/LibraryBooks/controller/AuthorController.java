package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Author;
import elcom.ex1.LibraryBooks.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author create (@RequestBody Author author){

        return authorService.create(author);
    }

    @PutMapping("/{ID}")
    public Author update(@PathVariable Long ID, @RequestBody Author author){
        return authorService.update(ID, author);
    }
    @DeleteMapping("/{ID}")
    public void delete(@PathVariable Long ID){
        authorService.delete(ID);
    }

    @GetMapping("/{ID}")
    public Author findById(@PathVariable Long ID){
        return authorService.findById(ID);
    }
    @GetMapping
    public Iterable<Author> findAll(){
        return authorService.findAll();
    }
}
