package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Author;
import elcom.ex1.LibraryBooks.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author create (@RequestBody Author author){
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long ID, @RequestBody Author author){
        return authorService.update(ID, author);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long ID){
        authorService.delete(ID);
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long ID){
        return authorService.findById(ID);
    }
    @GetMapping
    public Iterable<Author> findAll(){
        return authorService.findAll();
    }
}
