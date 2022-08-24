package elcom.ex1.LibraryBooks.controller;

import elcom.ex1.LibraryBooks.entity.Category;
import elcom.ex1.LibraryBooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{ID}")
    public Category findById(@PathVariable Long ID){
        return categoryService.findById(ID);
    }

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("/{ID}")
    public Category update(@PathVariable Long ID, @RequestBody Category category){
        return categoryService.update(ID, category);
    }

    @DeleteMapping("/{ID}")
    public void delete(@PathVariable Long ID){
        categoryService.delete(ID);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }
}
