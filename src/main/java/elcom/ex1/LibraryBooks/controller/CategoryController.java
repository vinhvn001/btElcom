package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Category;
import elcom.ex1.LibraryBooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long ID){
        return categoryService.findById(ID);
    }

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long ID, @RequestBody Category category){
        return categoryService.update(ID, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long ID){
        categoryService.delete(ID);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }
}
