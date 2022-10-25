package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.library.Category;
import elcom.ex1.librarybooks.repository.library.CategoryRepository;
import elcom.ex1.librarybooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Cacheable(value="category", key="#p0")
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        return  categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, String categoryName) {
        Category fromDB = categoryRepository.findById(id).orElse(null);
        if (fromDB == null){
            return null;
        }
        else{
            fromDB.setCategoryName(categoryName);
            return categoryRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
